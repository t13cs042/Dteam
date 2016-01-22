package passforget;

import javax.jdo.PersistenceManager;
import Dataclass.LoginDB;
import Dataclass.PMF;
import signup_.Encryption;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

//clogin C_loginServlet.javaから引用

@SuppressWarnings("serial")
public class secretqServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException,ServletException {
		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("utf-8");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		// クエリを作成
		String query = "select from " + LoginDB.class.getName();

		// ユーザデータを取得
		List<LoginDB> users = (List<LoginDB>) pm.newQuery(query).execute();

		HttpSession session = req.getSession(true);

		String adr = req.getParameter("address");
		//String pass = req.getParameter("password");
		
		
		String mailFormat = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*" +                                   
                "@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)*$";
		
		
		PrintWriter out = resp.getWriter();
		int error = 0;
		
		if(adr.equals("")){
			//メールアドレスが入力されていない
			error += 1;
		}
		//if(pass.equals("")){
			//パスワードが入力されていない
			//error += 2;
		//}
		if(adr.matches(mailFormat)){
			//入力されたメールアドレスが正規表現にあっていないとき
			error += 64;
		}
		
		//String encryptedpass = Encryption.getSaltedPassword(pass, adr);
		
		for(LoginDB ur : users){
			//if( adr.equals( ur.getMail() ) && encryptedpass.equals(ur.getPassword()) ){
			if( adr.equals( ur.getMail()) ){
				
				int status = ur.getStatus();//ユーザ状態
	
				if(status == 0){
					//アカウントが未認証の場合
					error += 4;
				}
				
				else if(status == 1){//ユーザが認証されているなら
					
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
			if( ur.equals(users.get( users.size()-1 )) ){
				//データベースに登録されていない
				error += 32;
				
			}
		}
		if(error == 0){
			resp.sendRedirect("/passforget/secretq.jsp");	   
		}
		else {
			resp.sendRedirect("/passforget/passforget.jsp?Error=" + String.valueOf(error));
		}

		if (pm != null && !pm.isClosed())
			pm.close();
	}

}