package Calc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import Dataclass.Candidate;
import Dataclass.Climate;
import Dataclass.Grape;
import Dataclass.LoginDB;
import Dataclass.PMF;
import Dataclass.Predict;
import Dataclass.Tempdata;

public class NewPredict  extends HttpServlet{

	@Override
	public void doPost( HttpServletRequest req, HttpServletResponse resp ) throws IOException
	{
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();

		//session用意
		HttpSession session = req.getSession(true);
		// pm を用意
		PersistenceManager	pm0	= PMF.get().getPersistenceManager();

		// クエリを作成
		Query user = pm0.newQuery(LoginDB.class);

		user.setFilter( "status == 1" );
		// ユーザデータを取得
		List<LoginDB> users = (List<LoginDB> )user.execute();

		for(LoginDB ur : users){

			PersistenceManager	pm	= PMF.get().getPersistenceManager();
			
			//Queryを用意
			Query queryCa = pm.newQuery(Candidate.class); 
			Query queryCl = pm.newQuery(Climate.class); 
			Query queryGr = pm.newQuery(Grape.class);

//			String id = (String) session.getAttribute("mail");

			queryCa.setFilter("id == " + ur.getId() + "" );

			List<Candidate> candidates = (List<Candidate>) queryCa.execute();



			//候補年取得
			String candies[] = new String[3];
			candies[0] = candidates.get(0).getCandi1();
			candies[1] = candidates.get(0).getCandi2();
			candies[2] = candidates.get(0).getCandi3();
			//candies[0] = (String) session.getAttribute("candi1");
			//candies[1] = (String) session.getAttribute("candi2");
			//candies[2] = (String) session.getAttribute("candi3");
			
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
			double yield,maxyield = 0.0,minyield = 10000.0,sumyield = 0.0,avyield = 0.0;
			String areastr = ur.getArea();
			double areanum = Double.valueOf( areastr );


			for( int i = 0; i < 3; i++ ){
				queryGr.setFilter("date == '" + candies[i]  +"'");
				List<Grape> grapes = (List<Grape>) queryGr.execute(); 

				yields[i] = grapes.get(0).getNum() * areanum;
			}

			for( int i = 0; i < 3; i++ ){
				if( maxyield < yields[i] ) maxyield = yields[i];
				if( minyield > yields[i] ) minyield = yields[i];
				sumyield += yields[i];
			}
			avyield = sumyield/3;


			//登録
			Predict data = new Predict( ur.getId() , new Date(), temps, laytimes, precs, avyield, maxyield, minyield );


			try {
				pm.makePersistent(data);
			} finally {
				pm.close();
			}
		}

		pm0.close();
		

		
		out.println("候補年計算＋予測データ登録完了");
		out.println("<a href=\"index.html\">戻る</a>");

	}

}




