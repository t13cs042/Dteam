package setchange;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

import Dataclass.LoginDB;
import Dataclass.PMF;

@SuppressWarnings("serial")
public class Change_Month extends HttpServlet {
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

		HttpSession session = req.getSession(false);
		String mail = (String)session.getAttribute("mail");

		// 登録エラー用のフラグ
		int error = 0;

		// pm を用意
		PersistenceManager	pm	= PMF.get().getPersistenceManager();
		//Queryを用意
		Query query = pm.newQuery(LoginDB.class);

		try{
			String start = req.getParameter("start");
			String finish = req.getParameter("finish");

			
			// ID欄が入力されているかチェック
			if(start.equals("")){
				error += 16;
			}
			if(finish.equals("")){
				error += 8;
			}
			
			
			// IDが使用済なら登録不可
			if(error != 0){
				resp.sendRedirect("/settingchange/change_month.jsp?Error=" + String.valueOf(error)
						+ "&Before=" + start + "&After=" + finish);
			}else{


			// データ変更

			query.setFilter("mail == " + "'" + mail + "'");

			List<LoginDB> db = (List<LoginDB>) query.execute();

			//LoginDB db =  (LoginDB)pm.newQuery(query).execute();
			if(db.size() != 0){
				db.get(0).setStart_month(start);
				db.get(0).setFinish_month(finish);
				session.setAttribute("start_month", start);
				session.setAttribute("finish_month", finish);
				

				resp.getWriter().print("収穫月の変更に成功しました。<br><br>");
					
			}else{
				resp.getWriter().print("収穫月の変更に失敗しました。<br><br>");
			}
			
			out.println("<a href=\"settingchange/setting.jsp\">設定画面へ戻る</a><br><br>");			
			out.println("<a href=\"Home/Home_temp.jsp\">ホーム画面へ戻る</a><br>");	
			
			
		
				
			}
		

		}finally{
			pm.close();
		}

		// 画面を更新
		//	resp.sendRedirect( "/index.html" );
	}


}
