package setchange;


import java.io.IOException;
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

		String address = "b";//後でsessionに書き換える
		
		// 登録エラー用のフラグ
		int error = 0;

		// pm を用意
		PersistenceManager	pm	= PMF.get().getPersistenceManager();
		//Queryを用意
		Query query = pm.newQuery(LoginDB.class);

		try{
			String start = req.getParameter("start");
			String finish = req.getParameter("finish");

			/*
			// ID欄が入力されているかチェック
			if(inputData[0].equals("")){
				error += 16;
			}
			//else if(!inputData[0].matches("[a-zA-Z0-9@.]+")){
			else if(!inputData[0].matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")){
				error += 2;
			}
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
			}

			// IDが使用済なら登録不可
			if(error != 0){
				resp.sendRedirect("/settingchange/change_address.jsp?Error=" + String.valueOf(error)
						+ "&Before=" + inputData[0] + "&After=" + inputData[1]);
			}else{

			 */

			// データ変更

			query.setFilter("mail == " + "'" + address + "'");

			List<LoginDB> db = (List<LoginDB>) query.execute();

			//LoginDB db =  (LoginDB)pm.newQuery(query).execute();
			
			db.get(0).setStart_month(start);
			db.get(0).setFinish_month(finish);

			// 登録
			//pm.makePersistent(data);
			resp.setContentType("text/html");
			resp.setCharacterEncoding("utf-8");
			resp.getWriter().print("成功");

			// 画面を更新
			//resp.sendRedirect( "/index.html" );


		}finally{
			pm.close();
		}

		// 画面を更新
		//	resp.sendRedirect( "/index.html" );
	}


}
