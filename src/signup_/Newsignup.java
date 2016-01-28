package signup_;

import Dataclass.LoginDB;
import Dataclass.PMF;
import signup_.Encryption;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;


@SuppressWarnings("serial")
public class Newsignup extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String familyname = req.getParameter("familyname");
		String firstname = req.getParameter("firstname");

		String mail = (String)req.getParameter("mail");
		String password = (String)req.getParameter("password");
		String pppassword = (String)req.getParameter("pppassword");
		String area = (String)req.getParameter("area");
		String start_month = (String)req.getParameter("start_month");
		String finish_month = (String)req.getParameter("finish_month");
		String question1 = (String)req.getParameter("question1");
		String answer1 = (String)req.getParameter("answer1");
		String question2 = (String)req.getParameter("question2");
		String answer2 = (String)req.getParameter("answer2");

		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();

		int error = 0;
		PersistenceManager	pm	= PMF.get().getPersistenceManager();

		String query = "select from " + LoginDB.class.getName();

		@SuppressWarnings("unchecked")
		List<LoginDB> users = (List<LoginDB>) pm.newQuery(query).execute();

		for(LoginDB user: users){
			if(mail.equals( user.getMail() ) ){
				error += 16;
			}
		}

		if(familyname.equals("")){
			error += 1;
		}
		if(firstname.equals("")){
			error += 2;
		}
		if(mail.equals("")){
			error += 4;
		}else if(!mail.matches("^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$")){
			error += 8;
		}
		if(password.equals("")){
			error += 32;
		}else if(!password.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]{0,12}$")){
			error += 64;
		}
		if(pppassword.equals("")){
			error += 128;
		}
		if(!password.equals(pppassword)){
			error += 256;
		}

		int error_2 = 0;
		if(area.equals("")){
			error_2 += 1;
		}else if(Integer.parseInt(area) > 10000 || Integer.parseInt(area) < 0){
			error_2 += 8;
		}
		if(start_month.equals("") || finish_month.equals("")){
			error_2 += 2;
		}else if(Integer.parseInt(start_month) > Integer.parseInt(finish_month)){
			if(Integer.parseInt(finish_month) - Integer.parseInt(start_month)+12 > 6)
				error_2 += 4;
		}else if(Integer.parseInt(start_month) < Integer.parseInt(finish_month)){
			if(Integer.parseInt(finish_month) - Integer.parseInt(start_month) > 6)
				error_2 += 4;	
		}

		if(question1.equals("")){
			error_2 += 16;
		}	
		if(answer1.equals("")){
			error_2 += 32;
		}
		if(question2.equals("")){
			error_2 += 64;
		}
		if(answer2.equals("")){
			error_2 += 128;
		}		
		if(question1.length() > 500 || question2.length() > 500){
			error_2 += 256;
		}
		if(answer1.length() > 500 || answer2.length() > 500){
			error_2 += 256;
		}
		int allerror = error + error_2;
		if(allerror != 0){
			resp.sendRedirect("/signup/signup.jsp?Error=" + String.valueOf(error)
					+ "&Error_2=" + String.valueOf(error_2));
		}else{
			String pass = Encryption.getSaltedPassword(password, familyname);
			LoginDB logindb = new LoginDB(familyname, firstname, mail, pass, area, start_month,
					finish_month,  question1,  answer1,  question2, answer2,0);
			try {
				pm.makePersistent(logindb);

			} finally {
				pm.close();
			}
			resp.getWriter().print("アカウント作成しました。<br><br>");
			out.println("<a href=\"/Login/login.jsp\">戻る</a>");
		}
	}

	public boolean isNumber(String num) {
		try {
			Integer.parseInt(num);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
