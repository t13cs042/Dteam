package clogin;


import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import Dataclass.LoginDB;
import Dataclass.PMF;

//import com.google.appengine.api.users.User;
//import com.google.appengine.api.users.UserService;
//import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;


@SuppressWarnings("serial")
public class C_loginServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException,ServletException {
		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("utf-8");
		PersistenceManager pm = null;

		pm = PMF.get().getPersistenceManager();

		// クエリを作成
		//String query = "select from " + LoginDB.class.getName();

		String address = req.getParameter("address");
		String pass = req.getParameter("password");

		Query query = pm.newQuery(LoginDB.class);
		query.setFilter("mail == " + "'" + address + "'");
		query.setFilter("password == " + "'" + pass + "'");

		// ユーザデータを取得
		List<LoginDB> users = (List<LoginDB>) pm.newQuery(query).execute();

		if(users.size() == 1){
			LoginDB db = users.get(0); 

			HttpSession session = req.getSession(true);
			
			session.setAttribute("id", db.getId());
			session.setAttribute("familyname", db.getFamilyname());
			session.setAttribute("firstname", 	db.getfirstname());
			session.setAttribute("mail", db.getMail());
			session.setAttribute("password", db.getPassword());
			session.setAttribute("area",  db.getArea());
			session.setAttribute("start_month", db.getStart_month());
			session.setAttribute("finish_month", 	db.getFinish_month());
			session.setAttribute("question1", 	db.getQuestion1());
			session.setAttribute("question2", 	db.getQuestion2());
			session.setAttribute("answer1", db.getAnswer1());
			session.setAttribute("answer2", db.getAnswer2());
			resp.sendRedirect("/Home/Home_temp.jsp");
		}

		//UserService userService = UserServiceFactory.getUserService();
		//User user = userService.getCurrentUser();

		//   if(user == null)
		//	resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));

		// mailアドレスを取得
		/*
    	    int i = 0;

    	   HttpSession session = req.getSession(true);

    	   for(LoginDB ur : users){
    		 //  if( id.equals( ur.getMail() ) ){
    		   session.setAttribute("familyname", 			ur.getFamilyname());
			   session.setAttribute("firstname", 		ur.getfirstname());
    		   session.setAttribute("mail", 			ur.getMail());
			   session.setAttribute("password", 		ur.getPassword());
			   session.setAttribute("area",           ur.getArea());
			   session.setAttribute("start_month",    ur.getStart_month());
			   session.setAttribute("finish_month", 	ur.getFinish_month());
			   session.setAttribute("question1", 		ur.getQuestion1());
			   session.setAttribute("question2", 		ur.getQuestion2());
			   session.setAttribute("answer1", 		ur.getAnswer1());
			   session.setAttribute("answer2",        ur.getAnswer2());
			   break;
    		  // }
    		  // else{
    			//   ;
    		  // }
    	   }

        } finally {
            if (pm != null && !pm.isClosed())
                pm.close();
        }

		 */        


		resp.sendRedirect("login.html");

	}

}