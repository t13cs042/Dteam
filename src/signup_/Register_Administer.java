package signup_;


import Dataclass.LoginDB;
import Dataclass.PMF;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import signup_.Encryption;
import javax.jdo.PersistenceManager;
import javax.servlet.http.*;


@SuppressWarnings("serial")
public class Register_Administer extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		//エラー登録用フラグ
		int error = 0;
		//Signup_Admini.jspからのデータ
		String familyname = req.getParameter("familyname");
		String firstname = req.getParameter("firstname");
		String mail = req.getParameter("mail");
		String password = req.getParameter("password");
		String repassword = req.getParameter("repassword");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		// クエリを作成
		String query = "select from " + LoginDB.class.getName();

		// ユーザデータを取得
		List<LoginDB> users = (List<LoginDB>) pm.newQuery(query).execute();
		
		for(LoginDB user: users){
			if(mail.equals( user.getMail() ) ){
				//メールアドレスが既に登録されている
				error += 1;
			}
				
		}
		

		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();

		if(mail.equals("")){
			//メールアドレスが入力されていない
			error += 2;
		}
		if(password.equals("")){
			//パスワードが入力されていない
			error += 4;
		}else if(password == repassword){
			//パスワードが異なっている
			error += 8;
		}
		
		if(password.length() > 10){
			//パスワードが長すぎ;
			error += 16;
		}
		
		if(error == 0){//エラーがなかったとき
			//暗号化されたパスワード
			String encrypedpass = Encryption.getSaltedPassword(password, mail);
			
			LoginDB logindb = new LoginDB(familyname, firstname, mail, encrypedpass, "", "",
					"",  "",  "",  "", "", 4);
			try {
				pm.makePersistent(logindb);//管理者をデータベースへ登録
			
			} finally {
				pm.close();
			}
			out.println("登録されました<br>");
			out.println("<a href=\"/Login/admin_login.jsp\">管理者ログイン画面へ戻る</a>");
		}else//エラーが合ったときの遷移先
			resp.sendRedirect("signup/Signup_Admini.jsp?Error=" + String.valueOf(error));
	}
}
