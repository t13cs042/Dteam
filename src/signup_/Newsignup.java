package signup_;

import Dataclass.LoginDB;
import Dataclass.PMF;
import signup_.Encryption;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;


@SuppressWarnings("serial")
public class Newsignup extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String familyname = req.getParameter("familyname");
		String firstname = req.getParameter("firstname");

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
		
		int error = 0;
		// pm を用意
		PersistenceManager	pm	= PMF.get().getPersistenceManager();
		
		// クエリを作成
		String query = "select from " + LoginDB.class.getName();

		// ユーザデータを取得
		List<LoginDB> users = (List<LoginDB>) pm.newQuery(query).execute();
		
		for(LoginDB user: users){
			if(mail.equals( user.getMail() ) ){
				//メールアドレスが既に登録されている
				error += 16;
			}
				
		}
		
		if(familyname.equals("")){
			error += 1;	//未入力
		}
		if(firstname.equals("")){
			error += 2;	//未入力
		}
		
		if(mail.equals("")){
			error += 4;	//メールアドレスが未入力
		}else if(!mail.matches("^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$")){
			error += 8;	//メールアドレスの形でない
		}
		if(password.equals("")){	//パスワードが未入力
			error += 32;
		}else if(!password.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]{0,12}$")){
			error += 64;	//パスワードの型が違う
		}
		if(pppassword.equals("")){
			error += 128;	//パスワード(再入力)が未入力
		}
		if(!password.equals(pppassword)){
			error += 256;	//パスワードが異なる
		}
		
		int error_2 = 0;
		if(area.equals("")){
			error_2 += 1;//作付面積が未入力
		}else if(!isNumber(area)){
			error_2 += 2;	//作付面積が数値以外で入力
		}
		
		if(start_month.equals("") || finish_month.equals("")){
			error_2 += 4;//収穫月が未入力
		}else if(Integer.parseInt(start_month) > Integer.parseInt(finish_month)){
			if(Integer.parseInt(start_month) - Integer.parseInt(finish_month)+12 > 6)
				error_2 += 8;	//"収穫期間が長すぎます
		}else if(Integer.parseInt(start_month) < Integer.parseInt(finish_month)){
			if(Integer.parseInt(finish_month) - Integer.parseInt(start_month) > 6)
				error_2 += 8;	//収穫期間が長すぎます
		}
		
		if(question1.equals("") == false && answer1.equals("") == true){
			error_2 += 16;	//秘密の質問1が入力されていませ
		}else if(question1.equals("") == true && answer1.equals("") == false){
			error_2 += 32;	//秘密の質問1の答えが入力されていません
		}
		if(question2.equals("") == false && answer2.equals("") == true){
			error_2 += 64;	//秘密の質問2が入力されていません
		}else if(question2.equals("") == true && answer2.equals("") == false){
			error_2 += 128;	//秘密の質問2の答えが入力されていません
		}
		if(question1.length() > 500 || question2.length() > 500){
			error_2 += 256;	//秘密の質問が長すぎ
		}
		
		int allerror = error + error_2;

		// エラーがあったら登録不可
		if(allerror != 0){
			resp.sendRedirect("/signup/signup.jsp?Error=" + String.valueOf(error)
					+ "&Error_2=" + error_2);
		}else{
			String pass = Encryption.getSaltedPassword(password, mail);

			System.out.println("new mailAddress: " + mail);
			System.out.println("new Password: " + password);
			System.out.println("new hashedPassword: " + pass);


			LoginDB logindb = new LoginDB(familyname, firstname, mail, pass, area, start_month,
					finish_month,  question1,  answer1,  question2, answer2,0);
			//PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				pm.makePersistent(logindb);
			
			} finally {
				pm.close();
			}
			resp.getWriter().print("アカウント作成しました。<br><br>");
			//out.println("<a href=\"/signup/signup.jsp\">戻る</a>");
			out.println("<a href=\"/Login/login.jsp\">戻る</a>");
			//resp.sendRedirect("/Home/Comment.jsp");
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
