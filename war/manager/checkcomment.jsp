<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Dataclass.PMF"%>
<%@ page import="Dataclass.Comment"%>
<%@ page import="javax.jdo.PersistenceManager"%>
<%@ page import="javax.jdo.Query"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>要望確認</title>
</head>
<body>
	<center>
		<div style="font-size: 200%">要望確認</div>

		<%
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Query query = pm.newQuery(Comment.class);
			query.setOrdering("time asc");

			List<Comment> comment = (List<Comment>) pm.newQuery(query)
					.execute();
					
		%>

		<%
			for (Comment co : comment) {
				String sub = "件名:" + co.getSub();
				for (int i = 0; i < 400 - sub.length(); i++)
					sub = sub + " ";

				String address = "\n" + "From:" + co.getAddress();
				for (int i = 0; i < 401 - address.length(); i++)
					address = address + " ";

				String str = sub + address;
		%>
		<div>
			<form action="/ViewComment" method="post" style="display: inline">
				<input type="hidden" name="id" value=<%=co.getId()%>>
				<input type="submit" value="<%=str%>"
					STYLE="border: 2px #85B9E9 double; width: 600px; height: 50px;">
			</form>
		</div>
		<%
			}
		%>

	</center>

</body>
</html>