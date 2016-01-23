package NewData;

import Dataclass.PMF;
import Dataclass.Tempdata;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Newtemp extends HttpServlet {

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
		
		String year = dateString.substring(0, 4);
		String month = dateString.substring(5, 7);
		String day = dateString.substring(8, 10);
		
		String temp = req.getParameter("temp");

		HttpSession session = req.getSession(false);
		String mail = (String) session.getAttribute("mail");
		
		int error = 0;
		if(temp == ""){
			out.println("値が入力されていません<br>");
			error+=1;
		}else if(isNumber(temp)){
			out.println("数値を入力してください<br>");
			error+=2;
		}else if(Double.parseDouble(temp) < -30 || Double.parseDouble(temp) > 50){
			out.println("無効な数値です<br>");
			error+=4;
		}
		if(error == 0){	
			Tempdata data = new Tempdata(dateString + mail, mail, dateString, Double.parseDouble(temp),year,month,day);
			PersistenceManager pm = PMF.get().getPersistenceManager();

			try {
				pm.makePersistent(data);
			} finally {
				pm.close();
			}
			
			out.println(year+month+day);
			//resp.sendRedirect("/Home/Home_temp.jsp");
			out.println("入力成功<br>");
			out.println("<a href=\"Home/Home_temp.jsp\">戻る</a>");
		}else
			out.println("<a href=\"Home/Home_temp.jsp\">戻る</a>");
	}

	public boolean isNumber(String num) {
		try {
			Double.parseDouble(num);
			return false;
		} catch (NumberFormatException e) {
			return true;
		}
	}
	
	public int checkerror(String temp){
		int error = 0;
		
		if(temp == ""){
			error+=1;
		}else if(isNumber(temp)){
			error+=2;
		}else if(Double.parseDouble(temp) < -30 || Double.parseDouble(temp) > 50){
			error+=4;
		}
		return error;
		
	}
}
