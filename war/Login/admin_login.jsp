<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>管理者ログイン</title>
  </head>

  <body>
  <center>
    <h1>ぶどう収穫量予測システム</h1>
    
       <%String errormsg = "";
    try{
    	errormsg = (String)request.getAttribute("msg");
    }catch(Exception e){
    	errormsg = "";
    }%>
    <font color = "red"><%=errormsg %></font><br>
    
    <form action="/admn_login" method ="post">
		メールアドレス：<input type="text" name="address" maxlength = "50" /><br>
　　		パスワード：<input type="password" name="password" maxlength = "12" /><br>
		<input type="submit" value="ログイン">
		</form>
		<br>
		<a href ="/signup/Signup_Admini.jsp">管理者登録</a><br>
		<a href="/Login/login.jsp">戻る</a><br>
  </center>
  </body>

</body>
</html>