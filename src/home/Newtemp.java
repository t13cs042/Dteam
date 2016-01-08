package home;

import home.PMF;

import java.io.IOException;
import java.util.Date;
import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

public class Newtemp extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String temp = req.getParameter("temp");
		Tempdata data = new Tempdata(new Date(), Double.parseDouble(temp));

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(data);
		} finally {
			pm.close();
		}
		resp.sendRedirect("/Home/Home_temp.jsp");
	}
}
