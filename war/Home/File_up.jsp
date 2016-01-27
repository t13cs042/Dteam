<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>気候データのアップロード</title>
</head>
<body>

<%
		if (session.getAttribute("status") == null) {
	%>
	管理者以外はこの画面にアクセスできません
	<br>
	<br>
	<a href="../Login/login.jsp">ログイン画面へ戻る</a>
	<br>
	<br>
	<%
		}

		else {
			int status2 = (Integer) session.getAttribute("status");
			if ( status2 != 4 ) {
	%>

	管理者以外はこの画面にアクセスできません
	<br>
	<br>
	<a href="../Home/Home_temp.jsp">ホーム画面へ戻る</a>
	<br>
	<br>


	
	<% }else{%>

			<form action="/File_up" method="post" enctype="multipart/form-data">

				<input type="file" name="file" /><br/>

				<input type ="submit" value="アップロード" />
			</form>

<%}} %>

</body>
</html>