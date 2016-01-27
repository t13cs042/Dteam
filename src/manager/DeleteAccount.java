package manager;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

import Dataclass.LoginDB;
import Dataclass.PMF;

@SuppressWarnings("serial")
public class DeleteAccount extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		// pm を用意
		PersistenceManager	pm	= PMF.get().getPersistenceManager();
		String mail = req.getParameter("mail");
		Query query = pm.newQuery(LoginDB.class);
		
		query.setFilter("mail ==" + "'" + mail + "'");
		List<LoginDB> db = (List<LoginDB>) pm.newQuery(query).execute();
		
		try{
			// チェックリストを回す
				try{
					// 削除
					pm.deletePersistent(db.get(0));
				}catch( Exception e ){}
				out.println("<a href=\"manager/managerwindow.jsp\">戻る</a><br><br>");		
		}finally{
			pm.close();
		}
	}
	
	
}
