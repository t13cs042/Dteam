package NewData;


import Dataclass.Comment;
import Dataclass.PMF;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class NewComment extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		HttpSession session = req.getSession(false);
		
		String address = (String)session.getAttribute("mail");
		String sub = req.getParameter("sub");
		String content = req.getParameter("content");

		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		// 日本時間で日時を取得する
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
		// 07月29日(金)の形でフォーマットする
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss", Locale.JAPAN);//"MM月dd日(E) HH:mm:ss"
		// フォーマット側のTimeZoneも日本にしておく
		format.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
		// dateString = "07月29日(金) 時刻"になっている
		String dateString = format.format(calendar.getTime());
		
		int error = 0;
		if(sub == ""){
			out.println("件名が入力されていません<br>");
			error += 1;
		}
		if(content == ""){
			out.println("本文が入力されていません<br>");
			error += 2;
		}
		if(sub.length() > 50){
			out.println("件名が長すぎです<br>");
			error += 4;
		}
		if(content.length() > 500){
			out.println("本文が長すぎです<br>");
			error += 8;
		}
		if(error == 0){
			Comment comment = new Comment(address, sub, content, dateString);

			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				pm.makePersistent(comment);
			} finally {
				pm.close();
			}
			out.println("送信完了<br>");
			out.println("<a href=\"Home/Home_temp.jsp\">戻る</a>");
			//resp.sendRedirect("/Home/Comment.jsp");
		}else
			out.println("<a href=\"Home/Comment.jsp\">戻る</a>");
	}
	
	public int checkerror(String sub, String content){
		int error = 0;
		if(sub == ""){
			//out.println("件名が入力されていません<br>");
			error += 1;
		}
		if(content == ""){
			//out.println("本文が入力されていません<br>");
			error += 2;
		}
		if(sub.length() > 50){
			//out.println("件名が長すぎです<br>");
			error += 4;
		}
		if(content.length() > 500){
			//out.println("本文が長すぎです<br>");
			error += 8;
		}	
		return error;
		
	}
	
	public boolean test(){
		try{

		return true;
		}catch(Exception e){
		return false;
		}
	}
}
