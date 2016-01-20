package NewData;

import Dataclass.LoginDB;
import Dataclass.PMF;
import Dataclass.Yielddata;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

public class NewYield extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {


		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		
		// 日本時間で日時を取得する
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
		// 07月29日(金)の形でフォーマットする
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日", Locale.JAPAN);//"MM月dd日(E) HH:mm:ss"
		// フォーマット側のTimeZoneも日本にしておく
		format.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
		// dateString = "07月29日(金) 時刻"になっている
		String dateString = format.format(calendar.getTime());

		String Year = dateString.substring(0, 4);
		String month = dateString.substring(5, 7);
		String day = dateString.substring(8, 10);

		String temp = req.getParameter("temp");

		HttpSession session = req.getSession(false);
		String mail = (String)session.getAttribute("mail");

		// 登録エラー用のフラグ
		int error = 0;

		// pm を用意
		PersistenceManager	pm	= PMF.get().getPersistenceManager();
		//Queryを用意
		Query query = pm.newQuery(LoginDB.class);


		String yield = req.getParameter("yield");
		String year = req.getParameter("year");
		

		if(yield.equals("")){
			error += 16;
		}else if(!yield.matches("[0-9]+")){
			error += 2;
		}

		if(year.equals("")){
			error += 4;
		}
		/*if(temp == ""){
			out.println("値が入力されていません<br>");
			error++;
		}else if(isNumber(temp)){
			out.println("数値を入力してください<br>");
			error++;

	  	}else if(Double.parseDouble(temp) < -30 || Double.parseDouble(temp) > 50){
			out.println("無効な数値です<br>");
			error++;

		}
		if(error == 0){	
		 */
		if(error != 0){
			resp.sendRedirect("/settingchange/set_yield.jsp?Error=" + String.valueOf(error)
					+ "&Before=" + yield );
		}else{


			// データ登録
			int yearIn = Integer.valueOf(year);
			
			Yielddata data = new Yielddata(year + mail, yearIn, mail, dateString, Double.valueOf( yield ) );
			
			try {
				pm.makePersistent(data);
			} finally {
				pm.close();
			}

			session.setAttribute("yield", yield);			

			resp.getWriter().print("収穫量の変更に成功しました。<br><br>");



			out.println("<a href=\"settingchange/setting.jsp\">設定画面へ戻る</a><br><br>");			
			out.println("<a href=\"Home/Home_temp.jsp\">ホーム画面へ戻る</a><br>");	

		}


	}




	/*	Yielddata data = new Yielddata(year, yield);
			PersistenceManager pm = PMF.get().getPersistenceManager();

			try {
				pm.makePersistent(data);
			} finally {
				pm.close();
			}
			//resp.sendRedirect("/Home/Home_temp.jsp");
			out.println("入力成功<br>");
			out.println("<a href=\"index.html\">戻る</a>");
	}
			/*
		}else
			out.println("<a href=\"Home/Home_temp.jsp\">戻る</a>");
	}
	 */
	public boolean isNumber(String num) {
		try {
			Double.parseDouble(num);
			return false;
		} catch (NumberFormatException e) {
			return true;
		}
	}
}
