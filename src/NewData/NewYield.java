package NewData;

import Dataclass.PMF;
import Dataclass.Yielddata;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

@SuppressWarnings("serial")
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

		HttpSession session = req.getSession(false);
		String mail = (String)session.getAttribute("mail");

		// 登録エラー用のフラグ
		int error = 0;

		// pm を用意
		PersistenceManager	pm	= PMF.get().getPersistenceManager();
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

}
