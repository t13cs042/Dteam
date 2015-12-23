package clogin;


import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
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
		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        PersistenceManager pm = null;
        try {
            pm = PMF.get().getPersistenceManager();

            Query query = pm.newQuery(Memo.class);
            query.setFilter("author == user_name");
            query.setOrdering("date desc");
            query.declareParameters("com.google.appengine.api.users.User" +
                     " user_name");

            List<Memo> memos = (List<Memo>) query.execute(user);
            String signOutUrl = userService.createLogoutURL(req.getRequestURI());

            Context context = new VelocityContext();
            context.put("memos", memos);
            context.put("signOutUrl", signOutUrl);
            context.put("user", user);
       
            resp.setContentType("text/html");
            resp.setCharacterEncoding("utf-8");

            Renderer.render("WEB-INF/mainPage.vm", context, resp.getWriter());
        } finally {
            if (pm != null && !pm.isClosed())
                pm.close();
        }
    

	}
}
