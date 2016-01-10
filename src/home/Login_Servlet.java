// Login処理を行うサーブレット

package home;

import java.io.*;
//import java.util.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.servlet.*;
import javax.servlet.http.*;
import Dataclass.LoginDB;
import Dataclass.PMF;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class Login_Servlet extends HttpServlet {
	@Override
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws IOException,ServletException {
		resp.setContentType("text/html;charset=UTF-8");

	    PersistenceManager pm = PMF.get().getPersistenceManager();

	    // クエリを作成
	    String query = "select from " + LoginDB.class.getName();

	    // 学生データと担任データを取得
	    List<LoginDB> students = (List<LoginDB>) pm.newQuery(query).execute();;

	    UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();

	    int flag = 0;
//		flag = 3; //@ とりあえず 2013/12/5

	    if(user == null)
	    	resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));

	    // gmailアドレスを取得
	    String id = user.getEmail();

	   HttpSession session = req.getSession(true);

	   for(LoginDB stu : students){
		   // 学生IDならば
		   if(id.equals(stu.getId())){
			   flag = 1;
			   session.setAttribute("access", 			flag);
			   session.setAttribute("studentID", 		stu.getId()); // 学生ID
			   break;
		   	}
	   }

	   pm.close();
	   
//	   flag = 3;	//最初のスーパーユーザ登録の際は, この文のコメントアウトを外してください
	   
	   if(flag == 0){
		   // 初期登録へ
		   resp.sendRedirect("./discharge.jsp");
	   }else if(flag == 1){
		   resp.sendRedirect("./student/");
	   }else if(flag == 2){
		   resp.sendRedirect("./teacher/");
	   }else if(flag == 3){
		   resp.sendRedirect("./superuser/");
	   }else if(flag == 4){
		   resp.sendRedirect("./Warning.html");
	   }

	}
}