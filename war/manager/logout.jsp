<!-- ログアウトページ -->
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.io.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>

<html>
<body>

	<% // ユーザをチェック
	
	session = request.getSession(true);
	session.invalidate();
	
	
    // Login/login.jsp へリダイレクト
	response.sendRedirect("Login/login.jsp");
%>


</body>
</html>
