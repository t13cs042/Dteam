package Calc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dataclass.Candidate;
import Dataclass.Climate;
import Dataclass.PMF;
import Dataclass.Tempdata;

public class Candi_get  extends HttpServlet{

	
	
	@SuppressWarnings("unchecked")
	@Override
	public void doPost( HttpServletRequest req, HttpServletResponse resp ) throws IOException
	{
		

		// calendarを作成
		Calendar calendar = Calendar.getInstance();
		// pm を用意
		PersistenceManager	pm	= PMF.get().getPersistenceManager();
		//Queryを用意
		Query queryX = pm.newQuery(Climate.class); /*Tempdata*/
		Query queryY = pm.newQuery(Climate.class);

		//現在と比較するの年月
		int currYear = 2014 /*calendar.get(Calendar.YEAR)*/;
		int compYear = 2003;
		int currMon = 12;//calendar.get(Calendar.MONTH);
		//X年とY年月の

		//各年毎の距離
		double distances[] = new double[12];
		//角距離をソートしたもの
		double dissort[] = new double[12];

		//List<Distances> distances;

		//比較する年のfor文
		for( int i = 0; i < 12; i++ ){

			double sumt,suml,sump;  //各気候の総和
			sumt = 0.0; suml = 0.0; sump = 0.0;

			for( int j = 3; j > 0; j-- ){
				int useMon    = currMon-j;
				int useYearX = currYear;
				int useYearY = compYear;
				if( useMon < 1 ){
					useYearX--;
					useYearY--;
				}else ;

				//現在から過去jヶ月とcompYear年の気候データを取得
				
				
				if( useMon < 1 ){
					queryX.setFilter("data ==" + String.valueOf(useYearX) +"/" + String.valueOf(useMon+12) +"" );
					queryY.setFilter("date ==" + String.valueOf(useYearY) +"/" + String.valueOf(useMon+12) +"");
				}else{
					String uyx = String.valueOf(useYearX);
					String uyy = String.valueOf(useYearY);
					String um = String.valueOf(useMon);
					
					queryX.setFilter("data ==\'" + uyx +"/" + um +"\'" );
					queryY.setFilter("date ==\'" + uyy +"/" + um +"\'");
				}

				List<Climate> cliXs = (List<Climate>) queryX.execute();
				List<Climate> cliYs = (List<Climate>) queryY.execute();
/*
				Climate cliX = cliXs.get(0);
				Climate cliY = cliYs.get(0);
				
				resp.setContentType("text/html; charset=UTF-8");
				PrintWriter out = resp.getWriter();
				//out.println("useYear");
				
				out.println(cliX.getDate());

				//(compMon-j)月の各気候の差
				double t = ( cliX.gettemp()    - cliY.gettemp()    );  
				double l = ( cliX.getlaytime() - cliY.getlaytime() );
				double p = ( cliX.getprec()    - cliY.getprec()    );

				if( t < 0 ) t *= -1;       //それぞれを絶対値にする
				if( l < 0 ) l *= -1;
				if( p < 0 ) p *= -1;

				sumt += t;                     //各総和を計算
				suml += l;
				sump += p;			

			}

			distances[i] = sumt + (1.1 * suml) + (0.8 * sump);     //距離

			compYear++;                   //比較する年を１年進める

		}

		dissort = distances;
		Arrays.sort( dissort );            //ソート

		int candi[] = new int[3];		

		//候補年検出
		for( int i = 0; i < 3; i++ ){
			for( int j = 0; j < 12; j++ ){
				if( dissort[i] == distances[j] ){
					candi[i] = 2003+j;
					break;
				}
			}
		}		

		//登録

		Candidate data = new Candidate( String.valueOf(candi[0]), String.valueOf(candi[1]), String.valueOf(candi[2]) );

		try {
			pm.makePersistent(data);
		} finally {
			pm.close();
		}
		*/
				
				
			}}
		
		PrintWriter out = resp.getWriter();
		out.println("<a href=\"Home/Home_temp.jsp\">戻る</a>");
				
			
		
		
		//resp.sendRedirect("/index.html");

	}

}




