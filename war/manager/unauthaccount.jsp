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
<title>Insert title here</title>
</head>
<body>

	<%
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(LoginDB.class);
		List<LoginDB> users = (List<LoginDB>) pm.newQuery(query).execute();
	%>

	<div style="font-size: 200%">未認証アカウント</div>

	<%
		for (LoginDB ur : users) {
			int status = ur.getStatus();//ユーザ状態
			if (status == 0) {
	%>

	<div><%=ur.getMail()%></div>

	<form action="/accountmanage" method="post" style="display: inline">
		<input type="hidden" name="state" value=1> <input
			type="hidden" name="mail" value="<%=ur.getMail()%>"> <input
			type="submit" value="承認">
	</form>

	<form action="/DeleteAccount" method="post" style="display: inline">
		<input type="hidden" name="mail" value="<%=ur.getMail()%>"> <input
			type="submit" value="拒否">
	</form>

	<%
		}
		}
	%>

</body>
</html>