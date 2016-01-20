package clogin;


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
		List<LoginDB> users = (List<LoginDB>) pm.newQuery(query).execute();

		HttpSession session = req.getSession(true);

		String adr = req.getParameter("address");
		String pass = req.getParameter("password");
		
		String mailFormat = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*" +                                   
                "@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)*$";
		
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
			
			String encryptedpass = Encryption.getSaltedPassword(pass, adr);
			
			if(pass.equals("")){
				//パスワードが入力されているかチェック
				error += 4;
			}
			
			for(LoginDB ur : users){
				//データベースに登録されているユーザなら
				if( adr.equals( ur.getMail() ) && encryptedpass.equals(ur.getPassword()) ){
					
					int status = ur.getStatus();//ユーザ状態
					
					if(status == 4){//ユーザが管理者なら
						
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
					else{
						//管理者ではありません．
						error += 8;
					}
					break;
				}
				if( ur.equals(users.get( users.size()-1 )) ){
					//登録されていません.
					error += 16;
				}
			}
			
			
			if(error == 0){//エラーがないなら	
				resp.sendRedirect("/manager/accountmanage.jsp");
			}
			else{//エラーがあれば管理者ログイン画面へ
				resp.sendRedirect("/Login/admin_login.jsp?Error=" + String.valueOf(error));
			}
			
		}finally{
			pm.close();
		}
		/*
		PrintWriter out = resp.getWriter();
		int error = 0;
		String msg = "";
		if(adr == ""){
			error++;
			msg +="メールアドレスが入力されていません．<br>";
			
		}
		if(pass == ""){
			error++;
			msg += "パスワードが入力されていません.<br>";
		}	
		
		for(LoginDB ur : users){
			if( adr.equals( ur.getMail() ) && pass.equals(ur.getPassword()) ){
				
				int status = ur.getStatus();//ユーザ状態
				
				if(status == 4){//ユーザが管理者なら
					
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
				else{
					error++;
					msg += "管理者のアカウントではありません<br>";
				}
					
				
				break;
			}
			if( ur.equals(users.get( users.size()-1 )) ){
				error++;
				msg += "登録されていません.<br>";
			}
		}
		if(error == 0){
			resp.sendRedirect("/manager/accountmanage.jsp");	   
		}
		else {
			req.setAttribute("msg", msg);
			getServletConfig().getServletContext().
			getRequestDispatcher("/Login/admin_login.jsp" ).
			forward( req, resp );
		}
			if (pm != null && !pm.isClosed())
			pm.close();
		*/
	}

}
