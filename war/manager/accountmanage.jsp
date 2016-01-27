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
<title>アカウント管理</title>
</head>
<body>

	<%
		PersistenceManager pm = PMF.get().getPersistenceManager();
			Query query = pm.newQuery(LoginDB.class);
			query.setOrdering("firstname asc");
			List<LoginDB> users = (List<LoginDB>) pm.newQuery(query).execute();
	%>

	<div style="font-size: 200%">アカウント管理</div>
	<br>

	<table>

		<%
			for (LoginDB ur : users) {
				int status = ur.getStatus();//ユーザ状態
				if (status == 1) {

		%>
		<tr>
			<td><%=ur.getMail()%></td>

		</tr>
		<tr>
			<td>名前:<%=ur.getFamilyname() + ur.getfirstname()%></td>
			<td>
				<form
					action="/manager/Confirm_Delete.jsp?state=2&mail=<%=ur.getMail()%>"
					method="post" style="display: inline">
					<input type="hidden" name="state" value=2> <input
						type="hidden" name="mail" value="<%=ur.getMail()%>"> <input
						type="submit" value="停止">
				</form>
			</td>
		</tr>
		<tr>
			<td>状態:通常</td>
		</tr>
		<%
			}

			}
			for (LoginDB ur : users) {
				int status = ur.getStatus();//ユーザ状態
				if (status == 2 ) {
		%>

		<tr>
			<td><%=ur.getMail()%></td>

		</tr>
		<tr>
			<td>名前:<%=ur.getFamilyname() + ur.getfirstname()%></td>
			<td>
				<form
					action="/manager/Confirm_Delete.jsp?state=1&mail=<%=ur.getMail()%>"
					method="post" style="display: inline">
					<input type="hidden" name="state" value=1> <input
						type="hidden" name="mail" value="<%=ur.getMail()%>"> <input
						type="submit" value="解除">
				</form>
			</td>
		</tr>
		<tr>
			<td>状態:停止</td>
		</tr>
		<%
			}

		%>

		<%
			}
		%>
	</table>

	<a href=/manager/managerwindow.jsp>戻る</a>

</body>
</html>