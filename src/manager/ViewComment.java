package manager;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

import Dataclass.Comment;
import Dataclass.PMF;

@SuppressWarnings("serial")
public class ViewComment extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}

	public void doPost( HttpServletRequest req, HttpServletResponse resp ) throws IOException
	{
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		//session用意
		Long id = Long.parseLong(req.getParameter("id"));
	
		// pm を用意
		PersistenceManager	pm	= PMF.get().getPersistenceManager();
		
		//try{
		
		Comment comment;
		
		try{
			comment = pm.getObjectById(Comment.class, id);
		}catch(Exception e){
			comment = null;
			//return;
		}
		out.print("件名:" + comment.getSub() + "<br>");
		out.print("From:"+comment.getAddress() + "<br><br>");
		out.print("本文: <br>" + comment.getComment() + "<br>");
		
	}


}
