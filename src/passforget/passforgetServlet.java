package passforget;

import javax.jdo.PersistenceManager;
import Dataclass.LoginDB;
import Dataclass.PMF;
import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

//clogin C_loginServlet.javaから引用

@SuppressWarnings("serial")
public class passforgetServlet extends HttpServlet {
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

		String address = req.getParameter("address");
		//String pass = req.getParameter("password");
		
		
		String mailFormat = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*" +                                   
                "@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)*$";
		
		
		int error = 0;
		
		if(address.equals("")){
			//メールアドレスが入力されていない
			error += 1;
		}
		//if(pass.equals("")){
			//パスワードが入力されていない
			//error += 2;
		//}
		if(!address.matches(mailFormat)){
			//入力されたメールアドレスが正規表現にあっていないとき
			error += 64;
		}
		
		//String encryptedpass = Encryption.getSaltedPassword(pass, adr);
		
		String question1="";
		String question2="";
		
		for(LoginDB ur : users){
			
			//if( adr.equals( ur.getMail() ) && encryptedpass.equals(ur.getPassword()) ){
			if( address.equals( ur.getMail()) && ur.getStatus() == 1 ){
				
				question1=ur.getQuestion1();
				question2=ur.getQuestion2();
				req.setAttribute("q1", question1);
				req.setAttribute("q2", question2);
				req.setAttribute("address", address);
				break;
			}
			if( ur.equals(users.get( users.size()-1 )) ){
				//データベースに登録されていない
				error += 32;	
			}
		}
		if(error == 0){
			// secretq.jspへ遷移
			RequestDispatcher dispatcher = req.getRequestDispatcher("/passforget/secretq.jsp");
            dispatcher.forward(req, resp);
		}
		else {
			resp.sendRedirect("/passforget/passforget.jsp?Error=" + String.valueOf(error));
		}

		if (pm != null && !pm.isClosed())
			pm.close();
	}

}
