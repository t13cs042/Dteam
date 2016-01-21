<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="Dataclass.Predict"%>
<%@ page import="Dataclass.PMF"%>

<%@ page import="javax.jdo.PersistenceManager"%>
<%@ page import="javax.jdo.Query"%>
<%@ page import="java.text.MessageFormat"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>home2</title>
<body>

	<%
		String name = (String) session.getAttribute("familyname")
		+ (String) session.getAttribute("firstname");
			
			
			PersistenceManager pm = null;

		    pm = PMF.get().getPersistenceManager();
		    Query query = pm.newQuery(Predict.class);
		Long id = (Long)session.getAttribute("id");
		    
		    query.setFilter("id ==" + id );
		    List<Predict> pre = (List<Predict>) query.execute(); 
		String year = "2015";
		ArrayList<Double> prec = pre.get(0).getPrec();
	%>

	<span style="font-size: 200%">ようこそ<%=name%>さん
	</span>
	<table border="1">
		<tr>
			<td><a href="Home_temp.jsp">気温</a></td>
			<td><a href="Home_lay.jsp">日照量</a></td>
			<td><a href="">降水量</a></td>
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
			data.addColumn('number', '降水量');

			//data.addRows([ [ 1, 37.8 ], [ 2, 30.9 ], [ 3, 25.4 ], [ 4, 11.7 ],
				//			[ 5, 20.9 ], ]);
			
<%for(int i = 1; i < 13; i++){%>
			data.addRow(['<%=String.valueOf(i)%>',<%=prec.get(i-1)%>]);
	<%}%>
		var options = {
				chart : {
					title : '降水量'
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
							<td><%=pre.get(0).getYield()%></td>
						</tr>
						<tr>
							<td>最大収穫量</td>
							<td><%=pre.get(0).getMaxYield()%></td>
						</tr>
						<tr>
							<td>最小収穫量</td>
							<td><%=pre.get(0).getMinYield()%></td>
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
							<td><a href="/logout">ログアウト</a></td>
						</tr>
					</table>
				</td>
		</table>

	</div>

</body>
</html>
