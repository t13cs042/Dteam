<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
 <title>秘密の質問</title>
</head>

 <body>
<%
String address = (String)request.getAttribute("address");
%>
<center>
<form method="POST" action="/secretq">
  <p><%=(String)request.getAttribute("q1")%><br>
  <input type="text" name="q1"></p>
  
    <p><%=(String)request.getAttribute("q2")%><br>
  <input type="text" name="q2"></p>
  <input type = "hidden" name = "address" value = <%=address%> >

<br><br> <input type="submit" value="送信" />
  </form>
  
<br><a href="http://sample-115509.appspot.com/">ホーム画面へ戻る</a><br><br>
</center>

  </body> 
</html>