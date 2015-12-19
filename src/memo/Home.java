package memo;

import memo.PMF;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.text.MessageFormat;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Home extends HttpServlet {

	// ページの先頭部分
	private static final String head =     
			"<html>\n" 
					+ "<title>home</title>"
					+ "<body>\n"
					+ " <span style=\"font-size: 200%\">ようこそXXX</span>\n"
					//+ "  </form>\n<br>"
					;

	private static final String gragh =
			"  <div>"
					+ "<table border=\"1\"> "
					+ "<tr><td>気温</td><td>降水量</td><td>日照時間</td>"
					+ "</table>"
					+ "<a href=\"Graph.html\">グラフ</a> "
					+ "  </div> \n"
					+ "  <head> <script type= \"text/javascript \""
					+ "src= \"https://www.google.com/jsapi?autoload={  "
					+ " 'modules':[{ "
					+ " 'name':'visualization', "
					+  " 'version':'1', "
					+ " 'packages':['corechart'] }] "
					+ " }\" ></script> "

	+ "<script type=\"text/javascript\"> "
	+ "google.setOnLoadCallback(drawChart); "

	+ "function drawChart() { "
	+ "var data = google.visualization.arrayToDataTable([ "
	+ "['Year', 'Sales', 'Expenses'], "
	+ "['2004', 1000, 400], ['2005', 1170, 460], ['2006', 660, 1120], ['2007', 1030, 540] ]); "

    + "var options = { "
    + "title: 'Company Performance', "
    + "curveType: 'function', "
    + "legend: { position: 'bottom' } }; "

			+ "var chart = new google.visualization.LineChart(document.getElementById('curve_chart')); "

			+ "chart.draw(data, options); } "
			+ "</script> "
			+ "</head> "
			+ "<body> "
			+ "<div id=\"curve_chart\" style=\"width: 900px; height: 500px\"></div> "
			+ "</body> " ;

	private static final String gragh2 =
			//		"<head>"
			"<table border=\"1\"> "
			+ "<tr><td>気温</td><td>降水量</td><td>日照時間</td>"
			+ "</table>"
			+ "<script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script>"
			+ "<script type=\"text/javascript\"> "
			+  "google.load('visualization', '1.1', {packages: ['line']});"
			+  "google.setOnLoadCallback(drawChart);"

   + "function drawChart() {"

    +  "var data = new google.visualization.DataTable();"
    + "data.addColumn('number', 'Month');"
    +"data.addColumn('number', '気温');"
    //+"data.addColumn('number', 'The Avengers');"
    //+"data.addColumn('number', 'Transformers: Age of Extinction');"

    + "data.addRows([ "
    + "[1,  37.8],[2,  30.9],[3,  25.4],[4,  11.7],[5,  20.9], ]);"

     + " var options = {"
     +  "chart: {"
     +  " title: '気温'},"

     //+  " subtitle: 'in millions of dollars (USD)'},"
     +  "width: 800,"
     + "height: 500"
     +"};"

     + "var chart = new google.charts.Line(document.getElementById('linechart_material'));"

     + "chart.draw(data, options);"
     + "}"
     + "</script>"
     //  +"</head>"
    // +"<body>"
     +  "<div id=\"linechart_material\"></div>"
    // +"</body>"
     ;

	private static final String syuukaku =
			"  <div id = \"left\">"
					+ "<table border=\"3\"> "
					+ "<tr><td>予測収穫量</td><td>xxxx</td></tr>"
					+ "<tr><td>最大収穫量</td><td>xxxx</td></tr>"
					+ "<tr><td>最小収穫量</td><td>xxxx</td></tr>"
					+ "</table>"
					+ "  </div> \n";

	private static final String input_temp =
			//	"    <div id = \"right\">"
			 "  <form action=\"/temp\" method=\"post\">"
			+ "    <div><br>本日の農場の気温入力\n"
			+ "    </div>気温"
			+ "      <textarea name=\"temp\" rows=\"1\" cols=\"10\" ></textarea>"
			+ "    <input type=\"submit\" value=\"Submit\" />"
			//+ "  </form>\n"
					+ "</div>"
			;

	private static final String link =

			//"    <div>"
			"<table> "
			+ "        <tr><td>メニュー</td></td> "
			+ "        <tr><td><a href=\"Graph.html\">設定</a></td></tr> "
			+ "        <tr><td><a href=\"SubPage\">ご要望</a></td></tr> "
			+ "        <tr><td><a href=\"\">ログアウト</a></td></tr> "
			+ "</table>"
			//	+ "</div>";
			;
	// ページの最後
	private static final String tail =      
			"  </body> \n" + "</html>\n";

	// タイムゾーンを JST に設定
	static {
		TimeZone.setDefault(TimeZone.getTimeZone("JST"));
	}

	private String render(List<Memo> memos) {
		StringBuffer sb = new StringBuffer();
		sb.append(head);
		//sb.append(gragh);
		sb.append(gragh2);
		sb.append(input_temp);
		sb.append(link);
		sb.append(syuukaku);
		sb.append(tail);
		return sb.toString();
	}


	// doGetメソッド
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		PersistenceManager pm = null;
		try {

			pm = PMF.get().getPersistenceManager();
			Query query = pm.newQuery(Memo.class);
			query.setOrdering("date desc");

			List<Memo> memos = (List<Memo>) query.execute();

			resp.setContentType("text/html");
			resp.setCharacterEncoding("utf-8");
			resp.getWriter().print(render(memos));
		} finally {
			if (pm != null && !pm.isClosed())
				pm.close();
		}
	}
}
