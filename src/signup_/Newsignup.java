package signup_;

import Dataclass.LoginDB;
import NewData.PMF;
import java.io.IOException;
import java.io.PrintWriter;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;


public class Newsignup extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
        String mail = req.getParameter("mail");
        String password = req.getParameter("password");
        String pppassword = req.getParameter("pppassword");
        String area = req.getParameter("area");
        String start_month = req.getParameter("start_month");
		 String finish_month = req.getParameter("finish_month");
		 String question1 = req.getParameter("question1");
		 String answer1 = req.getParameter("answer1");
		 String question2 = req.getParameter("question2");
		 String answer2 = req.getParameter("answer2");

		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		//out.println("aa<br>");
		
		int error = 0;
		if(mail == ""){
			out.println("メールアドレスが入力されていません<br>");
			error++;
		}
		if(password == ""){
			out.println("パスワードが入力されていません<br>");
			error++;
		}else if(password == pppassword){
			out.println("パスワードが異なります<br>");
			error++;
		}
		if(area == ""){
			out.println("作付面積が入力されていません<br>");
			error++;
		}
		if(start_month == "" || finish_month == ""){
			out.println("収穫月が入力されていません<br>");
			error++;
		}
		if(password.length() > 10){
			out.println("パスワードが長すぎです<br>");
			error++;
		}
		if(question1.length() > 500 || question1.length() > 500){
			out.println("秘密の質問が長すぎです<br>");
			error++;
		}
		
		if(error == 0){
		
			LoginDB logindb = new LoginDB(mail, password, area, start_month,
	    		 finish_month,  question1,  answer1,  question2, answer2);
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				pm.makePersistent(logindb);
			} finally {
				pm.close();
			}
			out.println("送信完了<br>");
			out.println("<a href=\"signup/signup.jsp\">戻る</a>");
			//resp.sendRedirect("/Home/Comment.jsp");
		}else
			out.println("<a href=\"signup/signup.jsp\">戻る</a>");
			 
			 
	}
}
