package Calc;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dataclass.Candidate;
import Dataclass.Climate;
import Dataclass.Grape;
import Dataclass.LoginDB;
import Dataclass.PMF;
import Dataclass.Predict;
import Dataclass.Yielddata;

@SuppressWarnings("serial")
public class NewPredict  extends HttpServlet{

	@SuppressWarnings("unchecked")
	@Override
	public void doPost( HttpServletRequest req, HttpServletResponse resp ) throws IOException
	{
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();

		// calendarを作成
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
		// pm を用意
		PersistenceManager	pm0	= PMF.get().getPersistenceManager();

		// クエリを作成
		Query user = pm0.newQuery(LoginDB.class);

		user.setFilter( "status == 1" );
		// ユーザデータを取得
		List<LoginDB> users = ( List<LoginDB> )user.execute();

		for(LoginDB ur : users){

			PersistenceManager	pm	= PMF.get().getPersistenceManager();
			
			//Queryを用意
			Query queryCa = pm.newQuery(Candidate.class); 
			Query queryCl = pm.newQuery(Climate.class); 
			Query queryGr = pm.newQuery(Grape.class);
			Query queryYi = pm.newQuery(Yielddata.class);


			queryCa.setFilter("id == " + ur.getId() + "" );

			List<Candidate> candidates = (List<Candidate>) queryCa.execute();


			//候補年取得
			String candies[] = new String[3];
			candies[0] = candidates.get(0).getCandi1();
			candies[1] = candidates.get(0).getCandi2();
			candies[2] = candidates.get(0).getCandi3();
			
			//予測気候データ
			ArrayList<Double> temps = new ArrayList<Double>();
			ArrayList<Double> laytimes = new ArrayList<Double>();
			ArrayList<Double> precs = new ArrayList<Double>();


			//予測気候
			for( int i = 0; i < 12; i++ ){
				double sumt = 0.0;
				double suml = 0.0;
				double sump = 0.0;

				for( int j = 0; j < 3; j++ ){
					String candi = candies[j];
					queryCl.setFilter("date == '" + candi + "/" + String.valueOf(i+1) + "'" );
					List<Climate> climates = (List<Climate>) queryCl.execute();

					sumt += climates.get(0).gettemp();
					suml += climates.get(0).getlaytime();
					sump += climates.get(0).getprec();

				}
				temps.add(sumt/3);
				laytimes.add(suml/3);
				precs.add(sump/3);	
			}


			//予測収穫量
			double yields[] = new double[3];
			double maxyield = 0.0,minyield = 100000000000.0,sumyield = 0.0,avyield = 0.0;
			String areastr = ur.getArea();
			double areanum = Double.valueOf( areastr );
			
		


			for( int i = 0; i < 3; i++ ){
				queryGr.setFilter("date == '" + candies[i]  +"'");
				queryYi.setFilter( "mail == '" + ur.getMail() +"'  &&  year == " + Integer.valueOf( candies[i] )  );
				
				
				List<Grape> grapes = (List<Grape>) queryGr.execute(); 
				List<Yielddata> yielddatas = (List<Yielddata>) queryYi.execute(); 


				if( yielddatas.size() != 0 )
					yields[i] = yielddatas.get(0).getYield() * areanum;
				else
					yields[i] = grapes.get(0).getNum() * areanum;

			}

			for( int i = 0; i < 3; i++ ){
				if( maxyield < yields[i] ) maxyield = yields[i];
				if( minyield > yields[i] ) minyield = yields[i];
				sumyield += yields[i];
			}
			avyield = sumyield/3;


			// 07月29日(金)の形でフォーマットする
			SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日", Locale.JAPAN);//"MM月dd日(E) HH:mm:ss"
			// フォーマット側のTimeZoneも日本にしておく
			format.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
			// dateString = "07月29日(金) 時刻"になっている
			String dateString = format.format(calendar.getTime());
			
			//登録
			Predict data = new Predict( ur.getId() , dateString , temps, laytimes, precs, avyield, maxyield, minyield );


			try {
				pm.makePersistent(data);
			} finally {
				pm.close();
			}
		}

		pm0.close();
		

		
		out.println("予測データを更新しました。<br><br>");
		out.println("<a href=\"/manager/managerwindow.jsp\">管理者画面へ戻る</a>");

	}

}




