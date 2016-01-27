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
public class C_loginServlet extends HttpServlet {
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
		//login.jspからのデータ
		String adr = req.getParameter("address");
		String pass = req.getParameter("password");
		//メールアドレスの正規表現
		String mailFormat = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$";
		
		//エラー登録用フラグ
		int error = 0;
		
		if(adr.equals("")){
			//メールアドレスが入力されていない
			error += 1;
		}
		if(pass.equals("")){
			//パスワードが入力されていない
			error += 2;
		}
		if(!adr.matches(mailFormat)){
			//入力されたメールアドレスが正規表現にあっていないとき
			error += 64;
		}
		
		String fn = ""; 
		for(LoginDB getkey: users){
			if(adr.equals( getkey.getMail() ) ){
				fn = getkey.getFamilyname();//パスワードの暗号化に用いるキーの取得
			}
		}
		
		
		//暗号化されたパスワード
		String encryptedpass = Encryption.getSaltedPassword(pass, fn);
		for(LoginDB ur : users){			
			if( adr.equals(ur.getMail()) && encryptedpass.equals(ur.getPassword()) ){
				int status = ur.getStatus();//ユーザ状態
				if(status == 0){
					//アカウントが未認証の場合
					error += 4;
				}
				else if(status == 1){//ユーザが認証されているなら
					//ログインしたユーザの内容をセッションに登録
					session.setAttribute("id",             ur.getId() );
					session.setAttribute("familyname",     ur.getFamilyname());
					session.setAttribute("firstname",      ur.getfirstname());
					session.setAttribute("mail",           ur.getMail());
					session.setAttribute("password",       ur.getPassword());
					session.setAttribute("area",           ur.getArea());
					session.setAttribute("start_month",    ur.getStart_month());
					session.setAttribute("finish_month",   ur.getFinish_month());
					session.setAttribute("question1",      ur.getQuestion1());
					session.setAttribute("question2",      ur.getQuestion2());
					session.setAttribute("answer1",        ur.getAnswer1());
					session.setAttribute("answer2",        ur.getAnswer2());
					session.setAttribute("status",         ur.getStatus());
					
				}
				else if(status == 2){
					//アカウントが停止されている
					error += 8;
				}
				else if(status == 4){
					//管理者の場合
					error += 16;
				}
				break;
			}
			if( ur.equals(users.get( users.size()-1 )) ){//usersリストの終わり
				//データベースに登録されていない
				error += 32;
			}
		}
		if(error == 0){//エラーなしの遷移先
			resp.sendRedirect("/Home/Home_temp.jsp");	
		}
		else {//エラーがあったときの遷移先
			resp.sendRedirect("/Login/login.jsp?Error=" + String.valueOf(error));
		}

		if (pm != null && !pm.isClosed())
			pm.close();
	}

}
