package NewData;

import Dataclass.PMF;
import Dataclass.Yielddata;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

public class NewYield extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		String yield = req.getParameter("yield");
		String year = req.getParameter("year");
/*
		int error = 0;
		if(temp == ""){
			out.println("値が入力されていません<br>");
			error++;
		}else if(isNumber(temp)){
			out.println("数値を入力してください<br>");
			error++;
		
	  	}else if(Double.parseDouble(temp) < -30 || Double.parseDouble(temp) > 50){
			out.println("無効な数値です<br>");
			error++;
			 	
		}
		if(error == 0){	
		*/
			Yielddata data = new Yielddata(year, yield);
			PersistenceManager pm = PMF.get().getPersistenceManager();

			try {
				pm.makePersistent(data);
			} finally {
				pm.close();
			}
			//resp.sendRedirect("/Home/Home_temp.jsp");
			out.println("入力成功<br>");
			out.println("<a href=\"index.html\">戻る</a>");
	}
			/*
		}else
			out.println("<a href=\"Home/Home_temp.jsp\">戻る</a>");
	}
*/
	public boolean isNumber(String num) {
		try {
			Double.parseDouble(num);
			return false;
		} catch (NumberFormatException e) {
			return true;
		}
	}
}
