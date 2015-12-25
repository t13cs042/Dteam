//メモを登録（書き込み）
package signup_;

import java.io.IOException;
import java.util.Date;
import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

import signup_.PMF;


public class New extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
    	
        String mail = req.getParameter("mail");
        String password = req.getParameter("password");
        String area = req.getParameter("area");
        String start_month = req.getParameter("start_month");
		 String finish_month = req.getParameter("finish_month");
		 String question1 = req.getParameter("question1");
		 String answer1 = req.getParameter("answer1");
		 String question2 = req.getParameter("question2");
		 String answer2 = req.getParameter("answer2");
		 
        LoginDB memo = new LoginDB(mail, password, area, start_month,
        		finish_month, question1, answer1, question2, answer2);

        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(memo);
        } finally {
            pm.close();
        }
        resp.sendRedirect("/");
    }
}
