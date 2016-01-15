package NewData;

import Dataclass.Comment;
import Dataclass.PMF;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

public class NewComment extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		HttpSession session = req.getSession(false);
		
		String address = (String)session.getAttribute("mail");
		String sub = req.getParameter("sub");
		String content = req.getParameter("content");

		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		int error = 0;
		if(sub == ""){
			out.println("件名が入力されていません<br>");
			error++;
		}
		if(content == ""){
			out.println("本文が入力されていません<br>");
			error++;
		}
		if(sub.length() > 50){
			out.println("件名が長すぎです<br>");
			error++;
		}
		if(content.length() > 500){
			out.println("本文が長すぎです<br>");
			error++;
		}
		if(error == 0){
			Comment comment = new Comment(address, sub, content);

			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				pm.makePersistent(comment);
			} finally {
				pm.close();
			}
			out.println("送信完了<br>");
			out.println("<a href=\"Home/Home_temp.jsp\">戻る</a>");
			//resp.sendRedirect("/Home/Comment.jsp");
		}else
			out.println("<a href=\"Home/Comment.jsp\">戻る</a>");
	}
}
