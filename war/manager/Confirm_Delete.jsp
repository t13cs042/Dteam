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
		String state = request.getParameter("state");
		String address = request.getParameter("mail");

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(LoginDB.class);
		List<LoginDB> users = (List<LoginDB>) pm.newQuery(query).execute();
		
		System.out.println(state);

		String str;
		if (state.equals("1"))
			str = "解除";
		else
			str = "停止";
	%>

	<center>
		<div style="font-size: 200%">確認</div>
		<br>
		<div><%=address%>を<%=str%>しますか？
		</div>
		<br>
		<div>
			<form action="/accountmanage" method="post" style="display: inline">
				<input type="hidden" name="state" value=<%=state%>> <input
					type="hidden" name="mail" value="<%=address%>"> <input
					type="submit" value="<%=str%>">
			</form>

			<form action="/manager/accountmanage.jsp" method="post" style="display: inline">
				<input type="submit" value="キャンセル">
			</form>
		</div>
	</center>

</body>
</html>