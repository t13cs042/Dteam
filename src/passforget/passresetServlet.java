//setchange Change_pass.javaを引用
package passforget;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import Dataclass.LoginDB;
import Dataclass.PMF;
import signup_.Encryption;

@SuppressWarnings("serial")
public class passresetServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
	
	@SuppressWarnings("unchecked")
	public void doPost( HttpServletRequest req, HttpServletResponse resp ) throws IOException, ServletException
	{

		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		HttpSession session = req.getSession(false);
		//String mail = (String)session.getAttribute("mail");
		//String mail = req.getParameter("mail");//add
		
		// jsp からのデータ
		String	[] inputData	= new String[2]; // beforepass と afterpass と afterpass2
		
		
		
		// 登録エラー用のフラグ
		int error = 0;
		
		// pm を用意
		PersistenceManager	pm	= PMF.get().getPersistenceManager();
		//Queryを用意
		Query query = pm.newQuery(LoginDB.class);
		
		
		try{
			//inputData[0] = req.getParameter("before");
			
			inputData[0] = req.getParameter("after1");
			inputData[1] = req.getParameter("after2");
			String address = req.getParameter("address");//add
			System.out.println("input[0]=" + inputData[0]);
			// ID欄が入力されているかチェック
			if(inputData[0].equals("")){
				error += 16;
			}
			//else if(!inputData[0].matches("[a-zA-Z0-9@.]+")){
			else if(!inputData[1].matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]{0,12}$")){
				error += 2;
			}
			else{
				// パスワードが正しいかチェック
				/*try{
					query.setFilter("mail == '" + mail + "'" );
					List<LoginDB> db =  (List<LoginDB>)pm.newQuery(query).execute();
					//String passBefore = Encryption.getSaltedPassword(db.get(0).getPassword(), db.get(0).getMail() );
					String passInput = Encryption.getSaltedPassword(inputData[0], db.get(0).getMail() );
					
					if( !( db.get(0).getPassword().equals(passInput) ) )
						error += 4;
				}catch(Exception e){}
			*/
			
			}
			
			// afterMailが入力されているかチェック
			if(inputData[0].equals("")){//was1
				error +=32;
			}
			
			if(inputData[1].equals("")){//was2
				error += 64;
			}

			if( !inputData[0].equals(inputData[1]) ){//was1 2
				error += 1;
			}
			
			// エラーがあったら登録不可
			if(error != 0){
				//resp.sendRedirect("/passforget/passreset.jsp?Error=" + String.valueOf(error)
					//	+ "&Before=" + inputData[0] + "&After=" + inputData[1]);
				req.setAttribute("address", address);//add
				RequestDispatcher dispatcher = req.getRequestDispatcher("/passforget/passreset.jsp?Error=" +
				String.valueOf(error) /*+  "&Before=" + inputData[0] + "&After=" + inputData[1] */);
	            dispatcher.forward(req, resp);
			}else{

				

				// データ変更
				query.setFilter("mail ==" + "'" + address + "'");
				
				List<LoginDB> db =  (List<LoginDB>)pm.newQuery(query).execute();
				if(db.size() != 0){
					String pass = Encryption.getSaltedPassword(inputData[0], db.get(0).getMail() );//was[1]
					db.get(0).setPassword(pass);
					//session.setAttribute("password", pass);
					
					req.setAttribute("address", address);//add

					resp.getWriter().print("パスワードの変更に成功しました。<br><br>");
						
				}else{
					
					resp.getWriter().print("パスワードの変更に失敗しました。<br><br>");
					resp.getWriter().print("パスワードの変更に失敗しました。<br><br>" + address);
				}
				
				out.println("<a href=\"passforget/passforget.jsp\">設定画面へ戻る</a><br><br>");			
				out.println("<a href=\"Home/Home_temp.jsp\">ホーム画面へ戻る</a><br>");	
				
				
				

				// 登録
				//pm.makePersistent(data);
				
				
				
				// 画面を更新
				//resp.sendRedirect( "/index.html" );

			}
		}finally{
			pm.close();
		}

		// 画面を更新
	//	resp.sendRedirect( "/index.html" );
	}
	
	
}
