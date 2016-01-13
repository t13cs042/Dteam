package setchange;


import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

import Dataclass.LoginDB;
import Dataclass.PMF;

@SuppressWarnings("serial")
public class Change_address extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
	
	@SuppressWarnings("unchecked")
	public void doPost( HttpServletRequest req, HttpServletResponse resp ) throws IOException
	{

		// jsp からのデータ
		String	[] inputData	= new String[3]; // beforeMail と afterMail1 と afterMail2

		// 登録エラー用のフラグ
		int error = 0;
		
		// pm を用意
		PersistenceManager	pm	= PMF.get().getPersistenceManager();
		//Queryを用意
		Query query = pm.newQuery(LoginDB.class);
		
		try{
			inputData[0] = req.getParameter("before");
			inputData[1] = req.getParameter("after1");
			inputData[2] = req.getParameter("after2");

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

				

				// データ変更
				query.setFilter("mail ==" + inputData[0]);
				
				List<LoginDB> db =  (List<LoginDB>)pm.newQuery(query).execute();
				db.get(0).setMail(inputData[1]);
				

				// 登録
				//pm.makePersistent(data);
				
				
				
				// 画面を更新
				resp.sendRedirect( "/index.html" );

			}
		}finally{
			pm.close();
		}

		// 画面を更新
	//	resp.sendRedirect( "/index.html" );
	}
	
	
}
