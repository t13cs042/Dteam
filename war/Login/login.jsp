<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>ログイン画面</title>
  </head>
  <body>
  <center>
    <h1>ぶどう収穫量予想システム</h1>
    <%String errormsg = "";
    try{
    	errormsg = (String)request.getAttribute("msg");
    }catch(Exception e){
    	errormsg = "";
    }%>
    <font color = "red"><%=errormsg %></font><br>

    
    <form action="/c_login" method ="post">
		メールアドレス：<input type="text" name="address" maxlength = "50" /><br>
		パスワード：<input type="password" name="password" maxlength = "12" /><br>
		<input type="submit" value="ログイン">
		</form>
		<br>
		<a href = "/passforget/passforget.jsp">パスワードを忘れた方<br>
		<a href="/signup/signup.jsp">初めて利用する方</a><br> 
       <a href="/Login/User_list.jsp">登録者リスト(仮)</a><br>
       <a href="/Login/admin_login.jsp">管理者用ログイン</a><br>
  </center>
  </body>

</html>