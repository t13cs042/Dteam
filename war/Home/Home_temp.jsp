<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="Dataclass.Predict"%>
<%@ page import="Dataclass.PMF"%>

<%@ page import="javax.jdo.PersistenceManager"%>
<%@ page import="javax.jdo.Query"%>
<%@ page import="java.text.MessageFormat"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Locale"%>
<%@ page import="java.util.TimeZone"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>ホーム</title>
<body>
<%
		if (session.getAttribute("status") == null) {
	%>
	この画面にアクセスできるは承認されたユーザのみです
	<br>
	<br>
	<a href="../Login/login.jsp">ログイン画面へ戻る</a>
	<br>
	<br>
	<%
		}

		else {
			int status = (Integer) session.getAttribute("status");
			if ( status != 1 ) {
	%>

	この画面にアクセスできるは承認されたユーザのみです
	<br>
	<br>
	<a href="../Login/login.jsp">ログイン画面へ戻る</a>
	<br>
	<br>

<% }else{%>

	<%
		String username = (String)session.getAttribute("familyname") +  (String)session.getAttribute("firstname");
	String start_month = (String) session.getAttribute("start_month");
	String finish_month = (String) session.getAttribute("finish_month");
			PersistenceManager pm = null;
		
		    pm = PMF.get().getPersistenceManager();
		    Query query = pm.newQuery(Predict.class);
		Long id = (Long)session.getAttribute("id");
		    
		    query.setFilter("id ==" + id );
		    List<Predict> pre = (List<Predict>) query.execute(); 
		String year = "2015";
		ArrayList<Double> temp = pre.get(0).getTemp();
	
		// 日本時間で日時を取得する
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
		// 07月29日(金)の形でフォーマットする
		SimpleDateFormat format = new SimpleDateFormat("MM", Locale.JAPAN);//"MM月dd日(E) HH:mm:ss"
		// フォーマット側のTimeZoneも日本にしておく
		format.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
		// dateString = "07月29日(金) 時刻"になっている
		String dateString = format.format(calendar.getTime());
		
		int month = Integer.parseInt(dateString);
		int start = Integer.parseInt(start_month);
		int finish = Integer.parseInt(finish_month);
	%>
	<span style="font-size: 200%">ようこそ<%=username%>さん</span>

<%
		boolean flag = false;
		
		if(start == month || finish == month)
			flag = true;

		int j = start;
		
		while(j != finish){
			if(j == month){
		flag = true;
		break;
			}
			j++;
			if(j > 12)
		j = 0;
		}
		
		if(flag){
	%>
<font color="red" size="150%" >
		<div>今月は収穫月です！頑張りましょう！</div>
	</font>
	<%
		}
	%>



	<table border="1">
		<tr>
			<td><a href="">気温</a></td>
			<td><a href="Home_lay.jsp">日照量</a></td>
			<td><a href="Home_prec.jsp">降水量</a></td>
	</table>

	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript">
		google.load('visualization', '1.1', {
			packages : [ 'line' ]
		});
		google.setOnLoadCallback(drawChart);
		function drawChart() {
			var data = new google.visualization.DataTable();
			data.addColumn('string', '月');
			data.addColumn('number', '気温(℃)');

			//data.addRows([ [ 1, 37.8 ], [ 2, 30.9 ], [ 3, 25.4 ], [ 4, 11.7 ],
				//			[ 5, 20.9 ], ]);
			
<% for(int i = 1; i < 13; i++){%>
			data.addRow(['<%=String.valueOf(i)%>',<%=temp.get(i-1)%>]);

			<%}%>

			var options = {
				chart : {
					title : '気温'
				},
				width : 800,
				height : 500
			};
			var chart = new google.charts.Line(document
					.getElementById('linechart_material'));
			chart.draw(data, options);
		}
	</script>

	<div id="linechart_material"></div>

	<div>
		<table cellpadding="20">

			<tr>
				<td>
					<table border="3">
						<tr>
							<td>予測収穫量</td>
							<td><%=pre.get(0).getYield() %>(kg)</td>
						</tr>
						<tr>
							<td>最大収穫量</td>
							<td><%=pre.get(0).getMaxYield() %>(kg)</td>
						</tr>
						<tr>
							<td>最小収穫量</td>
							<td><%=pre.get(0).getMinYield() %>(kg)</td>
						</tr>
					</table>
				</td>


				<td>
					<form action="/newtemp" method="post" style="display: inline">
						<p>本日の農場の気温入力</p>
						気温
						<input type="number" name="temp" rows="1" cols="10">
						<input type="submit" value="Submit" />
					</form>
				</td>

				<td>
					<table>
						<tr>
							<td>メニュー</td>
						<tr>
							<td><a href="../settingchange/setting.jsp">設定</a></td>
						</tr>
						<tr>
							<td><a href="/Home/Comment.jsp">ご要望</a></td>
						</tr>
						<tr>

							<td><a href="/logout">ログアウト</a></td>

						</tr>
					</table>
				</td>
		</table>

	</div>
<%}} %>

</body>
</html>
