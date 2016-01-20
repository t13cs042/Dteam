package Calc;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.Query.FilterOperator;

import Dataclass.Candidate;
import Dataclass.Climate;
import Dataclass.LoginDB;
import Dataclass.PMF;
import Dataclass.Tempdata;

public class Candi_get  extends HttpServlet{



	@SuppressWarnings("unchecked")
	@Override
	public void doPost( HttpServletRequest req, HttpServletResponse resp ) throws IOException, ServletException
	{
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();

		//Session作成
		HttpSession session = req.getSession(true);
		// calendarを作成
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
		// pm を用意
		PersistenceManager	pm0	= PMF.get().getPersistenceManager();

		// クエリを作成
		Query user = pm0.newQuery(LoginDB.class);

		user.setFilter( "status == 1" );
		// ユーザデータを取得
		List<LoginDB> users = (List<LoginDB> )user.execute();

		for(LoginDB ur : users){

			// pm を用意
			PersistenceManager pm = PMF.get().getPersistenceManager();
			
			//Queryを用意
			Query queryX = pm.newQuery(Climate.class); /*Tempdata*/
			Query queryY = pm.newQuery(Climate.class);
			Query queryT = pm.newQuery(Tempdata.class);

			//現在と比較するの年月
			int currYear = calendar.get(Calendar.YEAR);
			int compYear = 2003;
			int currMon = calendar.get(Calendar.MONTH);


			//各年毎の距離
			double distances[] = new double[12];
			//角距離をソートしたもの
			double dissort[] = new double[12];

			//List<Distances> distances;

			//比較する年のfor文
			for( int i = 0; i < 12; i++ ){

				String uyx = null,uyy = null,um = null;
				double sumt,suml,sump;  //各気候の総和
				sumt = 0.0; suml = 0.0; sump = 0.0;
				boolean than2003 = false;

				for( int j = 3; j > 0; j-- ){
					boolean noTemp = false;
					int useMon    = currMon-j;
					int useYearX = currYear;
					int useYearY = compYear;
					if( useMon < 1 ){
						useYearX--;
						useYearY--;
					}else ;

					//現在から過去jヶ月とcompYear年の気候データを取得
					if( useYearY < 2003 )than2003 = true;
					else{
/*
						if( useMon < 1 ){
							queryX.setFilter("date =='" + String.valueOf(useYearX) +"/" + String.valueOf(useMon+12) +"'" );
							queryY.setFilter("date =='" + String.valueOf(useYearY) +"/" + String.valueOf(useMon+12) +"'");
						}else{*/
							uyx = String.valueOf(useYearX);
							uyy = String.valueOf(useYearY);
							um = String.valueOf((useMon + 11) % 12 + 2);


							queryX.setFilter("date == '" + uyx + "/" + um + "'" );
							queryY.setFilter("date == '" + uyy + "/" + um + "'" );

							
							//tempdata関連の処理
		
								queryT.setFilter( "mail == '" + ur.getMail() +"'  &&  year == " + useYearX + " &&  month == " + (((useMon + 11) % 12) + 2) );
								
							
						//}

						List<Climate> cliXs = (List<Climate>) queryX.execute();
						List<Climate> cliYs = (List<Climate>) queryY.execute();
						List<Tempdata> temps = (List<Tempdata>) queryT.execute();

						double temp = 0.0;
						double sumtemps = 0.0;

						//データが無い場合のエラー処理を追加予定
						/*
						if( cliXs.size() == 0 || cliYs.size() == 0 ) {
							out.println("気候を予測するためのデータが足りていません。");;
							;
						}
						*/


						Climate cliX = cliXs.get(0);
						Climate cliY = cliYs.get(0);
						
						
						if( temps.size() == 0 ) noTemp = true;
						else{
							for( int k = 0; k < temps.size(); k++ ){
								sumtemps += temps.get(k).gettemp();
							}
							temp = sumtemps/temps.size();
						}

						double t = 0.0;
						
						//(compMon-j)月の各気候の差
						//double t = ( cliX.gettemp()    - cliY.gettemp()    );  
						if( !noTemp )	t = ( temp              - cliY.gettemp()    ); 
						else 	       t = ( cliX.gettemp()    - cliY.gettemp()    );						
						double l = ( cliX.getlaytime() - cliY.getlaytime() );
						double p = ( cliX.getprec()    - cliY.getprec()    );

						if( t < 0 ) t *= -1;       //それぞれを絶対値にする
						if( l < 0 ) l *= -1;
						if( p < 0 ) p *= -1;

						sumt += t;                     //各総和を計算
						suml += l;
						sump += p;			
						//out.println( uyx + "/" + um + ":" + noTemp + " " );
					}
				}

				
				
				if( than2003 ) distances[i] = 10000000000.0;
				else
					distances[i] = sumt + (1.1 * suml) + (0.8 * sump);     //距離
				//out.println(i  + ":" + dissort[i] + "\n" );
				compYear++;                   //比較する年を１年進める

			}


			for( int i = 0; i < 12; i++ ){
				dissort[i] = distances[i];
			}

			Arrays.sort( dissort );            //ソート

			int candi[] = new int[3];		

			//候補年検出
			for( int i = 0; i < 3; i++ ){
				for( int j = 0; j < 12; j++ ){
					if( dissort[i] == distances[j] ){
						candi[i] = 2003+j;
						//out.println(candi[i]  + ":" + dissort[i]);
						break;
					}
				}
			}		

			
			// 07月29日(金)の形でフォーマットする
			SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日", Locale.JAPAN);//"MM月dd日(E) HH:mm:ss"
			// フォーマット側のTimeZoneも日本にしておく
			format.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
			// dateString = "07月29日(金) 時刻"になっている
			String dateString = format.format(calendar.getTime());

			
			//登録
			
			Candidate data = new Candidate( ur.getId(), dateString, String.valueOf(candi[0]), String.valueOf(candi[1]), String.valueOf(candi[2]) );

			try {
				pm.makePersistent(data);
			} finally {
				pm.close();
			}


		}

		pm0.close();

		RequestDispatcher dis = req.getRequestDispatcher("/new_predict");
		dis.forward(req, resp);


		//resp.sendRedirect("/index.html");

	}

}




