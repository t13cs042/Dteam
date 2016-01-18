<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>home2</title>
<body>

	<%
		String name = (String) session.getAttribute("familyname")
				+ (String) session.getAttribute("firstname");
	%>


	<span style="font-size: 200%">ようこそ<%=name%>さん
	</span>
	<table border="1">
		<tr>
			<td><a href="Home_temp.jsp">気温</a></td>
			<td><a href="">日照量</a></td>
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
			data.addColumn('number', 'Month');
			data.addColumn('number', '日射量');
			data.addRows([ [ 1, 37.8 ], [ 2, 30.9 ], [ 3, 25.4 ], [ 4, 11.7 ],
					[ 5, 20.9 ], ]);
			var options = {
				chart : {
					title : '日射量'
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
							<td><a href="/logout">ログアウト</a></td>
						</tr>
					</table>
				</td>
		</table>

	</div>

</body>
</html>
