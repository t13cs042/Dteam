<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>ログイン画面</title>
  </head>
  <link rel="stylesheet" href="/css/login.css" type="text/css">
  <body>
    <% 
  // エラーの種類
	// 0 : エラーなし
	// 1 : メールアドレスが未入力
	// 2 : 入力されたメールアドレスが間違っている
	// 4 : パスワードが未入力
	// 8 : 管理者ではない
	//16 : 登録されていない 
    int error;
	String str;
	try{
		str = request.getParameter("Error");
		error = Integer.valueOf(str);
	}catch(Exception e){
		error = 0;
	}
  %>
  	<div id = "container">
  	<div id = "contents">
  	
  	<div class="layerImage">
	<div class="layerTransparent">
	<div class="frontContents">
  
  <center>
    <h1>ぶどう収穫量予想システム</h1>
    <form action="/c_login" method ="post">
    
      <font color = "red">
  		<%			if((error & 1) == 1){ %>   ※メールアドレスが入力さてれいません。<br> 
  		<%			}if((error & 64) == 64){ %> ※入力されたメールアドレスは間違っています<br> 
  		<%			}if((error & 4) == 4){ %> ※アカウントが未認証です<br> 
  		<%		   }if((error & 8) == 8){ %> ※このアカウントは停止されています<br>
  		<%		   }if((error & 16) == 16){ %> ※管理者はこちらからログインできません<br>
    		<%		   }if((error & 32) == 32){ %> ※登録されていません<br>
  		<%			} %> 
    	</font>
		<table>
		<tr>
		<td><b>メールアドレス：</b></td>
		<td><input type="email" name="address" maxlength = "50" placeholder = "Your Email Address" /></td>
		</tr>
		<tr>
		<td><b>パスワード：</b></td>
		<td><input type="password" name="password" maxlength = "12" placeholder = "Your Password" /></td>
		</tr>
		</table>
		<input type="submit" value="ログイン">
		</form>
		<br>
		<a href = "/passforget/passforget.jsp">パスワードを忘れた方</a><br>
		<a href="/signup/signup.jsp">初めて利用する方</a><br> 
     <!--  <a href="/Login/User_list.jsp">登録者リスト(仮)</a><br> -->
       <a href="/Login/admin_login.jsp">管理者用ログイン</a><br>
  <div id = "footer">&#169; TeamC All Rights Reserved.</div>
	</center>
	</div>
	</div>
	</div>
	</div>
	</div>
	
  </body>

</html>