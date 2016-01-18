<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- いろいろとダメなばあい -->
<% 
String msg = (String)request.getAttribute("msg");


%>
<html>
<head>
<title>ログインエラー</title>
</head>
<body>
<center>
ログインできません。
<br><%=msg %>
<br> もしかして。。
<br><a href = "/Login/login.jsp">戻る</a>
<br> ・新規登録しましたか？→
<a href = "../signup/newsignup">新規登録</a>
<br> ・管理者に承認されていないかもしれません.
<br> ・管理者にアカウントを停止さてれいるかもしれません．
<br> 
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br> システム障害がある場合はチームDにご連絡ください。
</center>
</body>
</html>