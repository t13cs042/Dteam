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
public class Accountmanage extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
	
	@SuppressWarnings("unchecked")
	public void doPost( HttpServletRequest req, HttpServletResponse resp ) throws IOException
	{

		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		String mail = req.getParameter("mail");
		String state = req.getParameter("state");
		
		// pm を用意
		PersistenceManager	pm	= PMF.get().getPersistenceManager();
		//Queryを用意
		Query query = pm.newQuery(LoginDB.class);
		
		try{

				// データ変更
				query.setFilter("mail ==" + "'" + mail + "'");
				
				List<LoginDB> db =  (List<LoginDB>)pm.newQuery(query).execute();
				if(db.size() != 0){
					db.get(0).setStatus(Integer.parseInt(state));

					
						
				}else{
				
				}
				
				out.println("<a href=\"manager/managerwindow.jsp\">戻る</a><br><br>");		
						
			
		}finally{
			pm.close();
		}

	}
	
	
}
