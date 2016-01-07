package signup_;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import signup_.LoginDB;


@SuppressWarnings("serial")
public class Signup_Servlet extends HttpServlet{
	
	// doGetメソッド
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		
	    // クエリを作成
		Query query = pm.newQuery("SELECT FROM getPassword()");

	    // 学生データと担任データを取得
	    List<LoginDB> password = (List<LoginDB>)query.execute();

	    pm.close();
		try {
			Query query2 = pm.newQuery(LoginDB.class);
			query2.setOrdering("date desc");

			List<LoginDB> memos = (List<LoginDB>) query.execute();

			resp.setContentType("text/html");
			resp.setCharacterEncoding("utf-8");
			//resp.getWriter().print(render(memos));
		} finally {
			if (pm != null && !pm.isClosed())
				pm.close();
		}
	}
	

}
