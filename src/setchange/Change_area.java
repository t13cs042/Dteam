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
public class Change_area extends HttpServlet {
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
		String inputData = req.getParameter("area");


		// ID欄が入力されているかチェック
		if(inputData.equals("")){
			error += 16;
		}
		//else if(!inputData[0].matches("[a-zA-Z0-9@.]+")){
		else if(!inputData.matches("[0-9]+")){
			error += 2;
		}
		if( Integer.parseInt(inputData) > 10000 )
			error += 4;
		
		/*
			else{

					// 検索、見付からなかったら例外を吐く
					//pm.getObjectById(LoginDB.class, inputData[0]);
					query.setFilter("mail == " + inputData[0]);
					List<LoginDB> db =  (List<LoginDB>)pm.newQuery(query).execute();
					if( db.isEmpty() )
						error += 4;

			}

			// afterMailが入力されているかチェック
			if(inputData[1].equals("")){
				error +=32;
			}

			if(inputData[2].equals("")){
				error += 64;
			}

			if( !inputData[1].equals(inputData[2]) ){
				error += 1;
			}*/

			// IDが使用済なら登録不可
			if(error != 0){
				resp.sendRedirect("/settingchange/change_area.jsp?Error=" + String.valueOf(error)
						+ "&Before=" + inputData );
			}else{

		 

		// データ変更

		query.setFilter("mail == " + "'" + mail + "'");

		List<LoginDB> db = (List<LoginDB>) query.execute();

		//LoginDB db =  (LoginDB)pm.newQuery(query).execute();
		if(db.size() != 0){
			db.get(0).setArea(inputData);
			session.setAttribute("area", inputData);

			resp.getWriter().print("作付面積の変更に成功しました。<br><br>");

		}else{
			resp.getWriter().print("作付面積の変更に失敗しました。<br><br>");
		}

		out.println("<a href=\"settingchange/setting.jsp\">設定画面へ戻る</a><br><br>");			
		out.println("<a href=\"Home/Home_temp.jsp\">ホーム画面へ戻る</a><br>");	


			}

		}finally{
			pm.close();
		}
		
	}
	
}
			
			



