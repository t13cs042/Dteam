package Calc;

import java.io.IOException;
import java.util.Calendar;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dataclass.Climate;
import Dataclass.PMF;

public class Candi_get  extends HttpServlet{

	@Override
	public void doPost( HttpServletRequest req, HttpServletResponse resp ) throws IOException
	{

		//各年毎の距離を保存する配列
		double distances[] = new double[10];

		// calendarを作成
		Calendar calendar = Calendar.getInstance();

		//比較する年
		int compYear = 2003;
		//比較する月
		int compMon = calendar.get(Calendar.MONTH) + 1;

		// pm を用意
		PersistenceManager	pm	= PMF.get().getPersistenceManager();
		//Queryを用意
		Query query = pm.newQuery(Climate.class);

		for( int i = 0; i < 10; i++ ){

			query.setFilter("date ==" + String.valueOf(compYear) +"/" + String.valueOf(compMon) );
			

		}

	}


}
