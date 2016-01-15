<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>設定変更画面</title>
</head>
<body>

	<%
		String address = (String) session.getAttribute("mail");
		String area = (String) session.getAttribute("area");
		String start = (String) session.getAttribute("start_month");
		String finish = (String) session.getAttribute("finish_month");
	%>

	<Div Align="center">
		<br> <a href="/settingchange/change_address.jsp">メールアドレスを変更したい方はこちら</a><br>
		<br> <a href="/settingchange/change_pass.jsp">パスワードを変更したい方はこちら</a><br>
		<br> <a href="/settingchange/change_area.jsp">作付面積を変更したい方はこちら</a><br>
		<br> <a href="/settingchange/change_month.jsp">収穫月を変更したい方はこちら</a><br>
		<br> <a href="/settingchange/set_yield.jsp">収穫量を登録したい方はこちら</a><br>
		<br> <a href="../Home/Home_temp.jsp">ホーム画面へ戻る</a><br> <br>
		<br> <br> <br> <br>

		<form action="/candi_get" method="post">
			<input value="予測テスト" type="submit">
		</form>

		<table>
			<tr>
				<td>現在の設定</td>
			<tr>
				<td>メールアドレス</td>
				<td><%= address %></td>
				<td><a href="/settingchange/change_address.jsp">変更</a></td>
			</tr>
			<tr>
				<td>作付面積</td>
				<td><%=area%></td>
				<td><a href="/settingchange/change_area.jsp">変更</a></td>
			</tr>
			<tr>
				<td>収穫月</td>
				<td><%=start%>月から<%=finish %>月</td>
				<td><a href="/settingchange/change_month.jsp">変更</a></td>
			</tr>
			<tr>
				<td>収穫量</td>
				<td>あとで</td>
				<td><a href="/settingchange/set_yield.jsp">変更</a></td>
			</tr>
			<tr>
				<td><a href="/settingchange/change_pass.jsp">パスワードの変更</a></td>
			</tr>
		</table>

	</Div>


</body>

</html>