package NewData;

import Dataclass.PMF;
import Dataclass.Tempdata;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

public class Newtemp extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		String temp = req.getParameter("temp");
		
		if(temp == "")
			out.println("値が入力されていません");
		else if(isNumber(temp))
			out.println("数値を入力してください");
		else if(Double.parseDouble(temp) < -30 || Double.parseDouble(temp) > 50)
			out.println("無効な数値です");
		else{
			
			Tempdata data = new Tempdata(new Date(), Double.parseDouble(temp));
			PersistenceManager pm = PMF.get().getPersistenceManager();

			try {
				pm.makePersistent(data);
			} finally {
				pm.close();
			}
			//resp.sendRedirect("/Home/Home_temp.jsp");
			out.println("入力成功");
		}
	}

	public boolean isNumber(String num) {
		try {
			Double.parseDouble(num);
			return false;
		} catch (NumberFormatException e) {
			return true;
		}
	}
}
