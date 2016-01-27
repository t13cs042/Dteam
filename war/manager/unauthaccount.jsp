<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Dataclass.PMF"%>
<%@ page import="Dataclass.LoginDB"%>
<%@ page import="javax.jdo.PersistenceManager"%>
<%@ page import="javax.jdo.Query"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>未認証アカウント</title>
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


	<%
	
			}else{
	
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(LoginDB.class);
		List<LoginDB> users = (List<LoginDB>) pm.newQuery(query).execute();
	%>

	<div style="font-size: 200%">未認証アカウント</div>
<table>
	<%
		for (LoginDB ur : users) {
			int status = ur.getStatus();//ユーザ状態
			if (status == 0) {
	%>

	<tr><td><%=ur.getMail()%></td>
<td>
	<form action="/accountmanage" method="post" style="display: inline">
		<input type="hidden" name="state" value=1> <input
			type="hidden" name="mail" value="<%=ur.getMail()%>"> <input
			type="submit" value="承認">
	</form>
</td>

<td>
	<form action="/DeleteAccount" method="post" style="display: inline">
		<input type="hidden" name="mail" value="<%=ur.getMail()%>"> <input
			type="submit" value="拒否" >
	</form>
</td>

	<%
		}
		}
	%>
	</tr>
	</table>

<a href=/manager/managerwindow.jsp>戻る</a>

<%}} %>

</body>
</html>