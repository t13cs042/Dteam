package memo;

import memo.PMF;
import java.io.IOException;
import java.util.Date;
import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

public class New extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String content = req.getParameter("content");
        String name = req.getParameter("name");
        Memo memo = new Memo(content, new Date(), name);

        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(memo);
        } finally {
            pm.close();
        }
        resp.sendRedirect("/SubPage");
    }
}
