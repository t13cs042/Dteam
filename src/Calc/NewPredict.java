package Calc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dataclass.Candidate;
import Dataclass.Climate;
import Dataclass.PMF;
import Dataclass.Tempdata;

public class NewPredict  extends HttpServlet{

	@Override
	public void doPost( HttpServletRequest req, HttpServletResponse resp ) throws IOException
	{
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();

		// pm を用意
		PersistenceManager	pm	= PMF.get().getPersistenceManager();
		//Queryを用意
		Query query = pm.newQuery(Candidate.class); 
		//query.setOrdering();
		

	}

}




