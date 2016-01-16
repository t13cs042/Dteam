package signup_;


import Dataclass.LoginDB;
import Dataclass.PMF;
import java.io.IOException;
import java.io.PrintWriter;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;


@SuppressWarnings("serial")
public class Register_Administer extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String familyname = req.getParameter("familyname");
		String firstname = req.getParameter("firstname");
		String mail = req.getParameter("mail");
		String password = req.getParameter("password");
		String repassword = req.getParameter("repassword");

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
		}else if(password == repassword){
			out.println("パスワードが異なります<br>");
			error++;
		}
		
		if(password.length() > 10){
			out.println("パスワードが長すぎです<br>");
			error++;
		}
		
		if(error == 0){

			LoginDB logindb = new LoginDB(familyname, firstname, mail, password, "", "",
					"",  "",  "",  "", "", 4);
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				pm.makePersistent(logindb);
			
			} finally {
				pm.close();
			}
			out.println("送信完了<br>");
			out.println("<a href=\"/Login/login.jsp\">戻る</a>");
			//resp.sendRedirect("/Home/Comment.jsp");
		}else
			out.println("<a href=\"signup/Signup_Admini.jsp\">戻る</a>");


	}
}
