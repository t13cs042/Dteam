package clogin;


import javax.jdo.PersistenceManager;
import Dataclass.LoginDB;
import Dataclass.PMF;
import signup_.Encryption;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;


@SuppressWarnings("serial")
public class Admini_Login_Servlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException,ServletException {
		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("utf-8");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		// クエリを作成
		String query = "select from " + LoginDB.class.getName();

		// ユーザデータを取得
		@SuppressWarnings("unchecked")
		List<LoginDB> users = (List<LoginDB>) pm.newQuery(query).execute();

		HttpSession session = req.getSession(true);
		//admin_login.jspからのデータ
		String adr = req.getParameter("address");
		String pass = req.getParameter("password");
		
		//メールアドレスの正規表現
		String mailFormat = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$";
		
		//登録エラー用フラグ
		int error = 0;
		
		try{
			
			if(adr.equals("")){
				//メールアドレス欄が入力されているかチェック
				error += 1;
			}
			else if(!adr.matches(mailFormat)){
				//メールアドレスの正規表現にマッチしているかチェック
				error += 2;
			}
			String fn = ""; 
			for(LoginDB getkey: users){
				if(adr.equals( getkey.getMail() ) ){
					fn = getkey.getFamilyname();
				}
				else{
					fn = "";
				}
			}
			
			//暗号化されたパスワードの取得
			String encryptedpass = Encryption.getSaltedPassword(pass, fn);
			
			if(pass.equals("")){
				//パスワードが入力されているかチェック
				error += 4;
			}
			
			for(LoginDB ur : users){
				//データベースに登録されているユーザなら
				if( adr.equals( ur.getMail() ) && encryptedpass.equals(ur.getPassword()) ){
					
					int status = ur.getStatus();//ユーザ状態
					
					if(status == 4){//ユーザが管理者なら
						//ログインされた管理者情報をセッションに登録しておく.
						session.setAttribute("id",             ur.getId() );
						session.setAttribute("familyname",     ur.getFamilyname());
						session.setAttribute("firstname",      ur.getfirstname());
						session.setAttribute("mail",           ur.getMail());
						session.setAttribute("password",       ur.getPassword());
					}
					else{
						//管理者ではありません．
						error += 8;
					}
					break;
				}
				if( ur.equals(users.get( users.size()-1 )) ){//usersリストの終わり
					//登録されていません.
					error += 16;
				}
			}
			
			
			if(error == 0){//エラーがないなら管理者画面へ	
				resp.sendRedirect("/manager/managerwindow.jsp");
			}
			else{//エラーがあれば管理者ログイン画面へ
				resp.sendRedirect("/Login/admin_login.jsp?Error=" + String.valueOf(error));
			}
		}finally{
			pm.close();
		}
	}

}
