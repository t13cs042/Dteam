package NewData;

import Dataclass.Comment;
import Dataclass.PMF;

import java.io.IOException;
import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

public class NewComment extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String address = "address";
		String sub = req.getParameter("sub");
		String content = req.getParameter("content");
		Comment comment = new Comment(address, sub, content);

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(comment);
		} finally {
			pm.close();
		}
		resp.sendRedirect("/Home/Comment.jsp");
	}
}
