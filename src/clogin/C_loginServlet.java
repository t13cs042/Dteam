package clogin;

import javax.jdo.PersistenceManager;
import Dataclass.LoginDB;
import Dataclass.PMF;

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
		List<LoginDB> users = (List<LoginDB>) pm.newQuery(query).execute();

		HttpSession session = req.getSession(true);

		String adr = req.getParameter("address");
		String pass = req.getParameter("password");
		String msg = "";
		if(adr == ""){
			msg = "メールアドレスが入力されていません．\n";
		}
		if(pass == ""){
			msg += "パスワードが入力されていません．\n";
		}	
		int flag = 0;
		         
		for(LoginDB ur : users){
			if( adr.equals( ur.getMail() ) && pass.equals(ur.getPassword()) ){
				
				flag = 1;
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
				break;
			}
		}
		if(flag == 1){
			resp.sendRedirect("/Home/Home_temp.jsp");		   
		}
		else {
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("/Login/Login_error.jsp").forward(req, resp);
		}

		if (pm != null && !pm.isClosed())
			pm.close();
	}

}
