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
        Tempdata tempdata = new Tempdata(new Date(), Long.valueOf(temp));

        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(tempdata);
        } finally {
            pm.close();
        }
        resp.sendRedirect("/Home");
    }
}
