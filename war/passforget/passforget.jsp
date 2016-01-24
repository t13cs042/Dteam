<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
 <title>パスワードを忘れた方</title>
 </head>
 <body>
 
<div Align="center"/> <span style="font-size: 100%">パスワードを再設定するために下に登録メールを入力してください。</span><br>
<br>
    <form action="/passforget" method ="post">
  <input type="text" name="address" maxlength = "50" /></p>

<br><br> <input type="submit" value="次へ" /><br>
  </form>
  
  	<a href = "/Login/login.jsp">ログイン画面へ戻る</a>
  
</div>
  </body> 
</html>