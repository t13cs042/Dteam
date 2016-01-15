<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="Dataclass.Climate"%>
<%@ page import="Dataclass.PMF"%>

<%@ page import="javax.jdo.PersistenceManager"%>
<%@ page import="javax.jdo.Query"%>
<%@ page import="java.text.MessageFormat"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>home2</title>
<body>

	<%
		String name = (String) session.getAttribute("familyname")
				+ (String) session.getAttribute("firstname");
	%>

	<span style="font-size: 200%">ようこそ<%=name%>さん</span>
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
			data.addColumn('number', '月');
			data.addColumn('number', '気温');
	<%PersistenceManager pm = null;
			
			    pm = PMF.get().getPersistenceManager();
			    Query query = pm.newQuery(Climate.class);
			    List<Climate> pizzas = (List<Climate>) query.execute(); 
			
			String year = "2014/";
			
			for(int i = 1; i < 13; i++){%>
		data.addRow(
	<%}%>
		);
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
							<td>xxxx</td>
						</tr>
						<tr>
							<td>最大収穫量</td>
							<td>xxxx</td>
						</tr>
						<tr>
							<td>最小収穫量</td>
							<td>xxxx</td>
						</tr>
					</table>
				</td>


				<td>
					<form action="/newtemp" method="post" style="display: inline">
						<p>本日の農場の気温入力</p>
						気温
						<textarea name="temp" rows="1" cols="10"></textarea>
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
							<td><a href="c_logout">ログアウト</a></td>
						</tr>
					</table>
				</td>
		</table>

	</div>

</body>
</html>
