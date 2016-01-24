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

		String q1 = req.getParameter("q1");
		String q2 = req.getParameter("q2");
		
		String mailFormat = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*" +                                   
                "@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)*$";
		
		String address = (String)req.getParameter("address");
		
		PrintWriter out = resp.getWriter();
		int error = 0;
		
		//if(adr.equals("")){
			//メールアドレスが入力されていない
			//error += 1;
		//}
		//if(pass.equals("")){
			//パスワードが入力されていない
			//error += 2;
		//}
		//if(adr.matches(mailFormat)){
			//入力されたメールアドレスが正規表現にあっていないとき
			//error += 64;
		//}
		
		//String encryptedpass = Encryption.getSaltedPassword(pass, adr);
		
		String ans1="",ans2="";
		
		for(LoginDB ur : users){
			//if( adr.equals( ur.getMail() ) && encryptedpass.equals(ur.getPassword()) ){
			if( address.equals( ur.getMail()) ){
				ans1=ur.getAnswer1();
				ans2=ur.getAnswer2();
				req.setAttribute("address", address);//add
				//System.out.println("ans1=" + ans1);
				//System.out.println("ans2=" + ans2);
				//System.out.println("q1=" + q1);
				//System.out.println("q2=" + q2);
				break;
			}
			if( ur.equals(users.get( users.size()-1 )) ){
				//データベースに登録されていない
				error += 32;
				
			}
		}
		
		if(q1.equals(ans1) && q2.equals(ans2)){
			resp.sendRedirect("/passforget/passreset.jsp");
		}
		
		if(error == 0){
			
			//resp.sendRedirect("/passforget/secretq.jsp");
			//add
			// passreset.jspへ遷移
			RequestDispatcher dispatcher = req.getRequestDispatcher("/passforget/passreset.jsp");
            dispatcher.forward(req, resp);
		}
		else {
			resp.sendRedirect("/passforget/passforget.jsp?Error=" + String.valueOf(error));
		}

		if (pm != null && !pm.isClosed())
			pm.close();
	}

}