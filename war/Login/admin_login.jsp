<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>管理者ログイン</title>
  </head>

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
 
  <center>
    <h1>ぶどう収穫量予想システム</h1>    
    <form action="/admn_login" method ="post">
    <font color = "red">
  <%			if((error & 1) == 1){ %>   ※メールアドレスが未入力です。<br> 
  <%			}if((error & 2) == 2){ %> ※入力されたメールアドレスは間違っています。<br> 
  <%			}if((error & 4) == 4){ %> ※パスワードを入力してください<br> 
  <%		   }if((error & 8) == 8){ %> ※あなたは管理者ではありません<br>
  <%		   }if((error & 16) == 16){ %> ※登録されていません．<br>
  <%			} %> 
    </font>
			メールアドレス：<input type="text" name="address" maxlength = "50" ><br>
　　		パスワード：<input type="password" name="password" maxlength = "12"><br>
		<input type="submit" value="ログイン">
		</form>
		<br>
		<a href ="/signup/Signup_Admini.jsp">管理者登録</a><br>
		<a href="/Login/login.jsp">戻る</a><br>
  </center>
  </body>

</body>
</html>