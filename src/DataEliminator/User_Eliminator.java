package DataEliminator;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dataclass.LoginDB;
import Dataclass.PMF;

@SuppressWarnings("serial")
public class User_Eliminator extends HttpServlet
{
	// SU用システム student_delete.jsp からデータを受け取り学生削除処理を行う
	@Override
	public void doPost( HttpServletRequest req, HttpServletResponse resp ) throws IOException
	{

		// jsp からのデータ
		String [] checklist	= req.getParameterValues("check"); // 削除するユーザのIDのリスト
		
		// pm を用意
		PersistenceManager	pm	= PMF.get().getPersistenceManager();
		try{
			LoginDB user;

			
			// チェックリストを回す
			for(int i=0;i<checklist.length;i++){
				
				try{
					Long id = Long.parseLong(checklist[i]);//stringをlongへ変換
					user = pm.getObjectById(LoginDB.class, id);
					// 削除
					pm.deletePersistent(user);
				}catch( Exception e ){}
			}
		}finally{
			pm.close();
		}

		// 画面を更新
		resp.sendRedirect( "/Login/User_list.jsp" );
	}
}