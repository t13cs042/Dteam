package home;

import home.PMF;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

public class Search extends HttpServlet {

	private static final String memoTmpl = 
			"  <div>\n"
					+ "    <span class=\"date\"> {0,time} {0,date} </span> \n"
					+ "    <pre>name {2}</pre> \n"
					+ "    <pre>{1}</pre> \n"
					+ "  </div> \n";

	// ページの最後
	private static final String tail =  
			"  </body> \n" + "</html>\n";

	private String render(List<Memo> memos) {
		StringBuffer sb = new StringBuffer();
		for (Memo memo : memos)
			sb.append(MessageFormat.format(memoTmpl, memo.getDate(), memo
					.getContent(), memo.getName()));
		sb.append(tail);
		return sb.toString();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String search = req.getParameter("search");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query query = pm.newQuery(Memo.class);
		query.setFilter("name == " + "'" + search + "'");

		//Query query = pm.newQuery("SELECT FROM memo.Memo " + "WHERE " + a);
		
		query.setOrdering("date desc");

		List<Memo> memos = (List<Memo>) query.execute();

		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().print(render(memos));

		StringBuffer sb = new StringBuffer();

	}

}