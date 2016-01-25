<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
 <title>パスワードを忘れた方</title>
 </head>
 <body>
 
     <% 
  // エラーの種類
	// 0 : エラーなし
	// 1 : メールアドレスが未入力
	// 2 : 入力されたメールアドレスが間違っている
	// no4 : パスワードが未入力
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
 
<div Align="center"/> <span style="font-size: 100%">パスワードを再設定するために下に登録メールを入力してください。</span><br>
<br>
    <form action="/passforget" method ="post">
    
           <font color = "red">
  		<%			if((error & 1) == 1){ %>   ※メールアドレスが入力されていません。<br> 
  		<%			}if((error & 64) == 64){ %> ※入力されたメールアドレスは間違っています<br> 
  	<%-- 	<%			}if((error & 4) == 4){ %> ※アカウントが未認証です<br> 
  		<%		   }if((error & 8) == 8){ %> ※このアカウントは停止されています<br>
  		<%		   }if((error & 16) == 16){ %> ※管理者はこちらからログインできません<br> --%>
    		<%		   }if((error & 32) == 32){ %> ※登録されていません<br> 
  		<%			} %>
    	</font>
    

  <input type="text" name="address" maxlength = "50" /></p>

<br><br> <input type="submit" value="次へ" /><br>
  </form>
  
  	<a href = "/Login/login.jsp">ログイン画面へ戻る</a>
  
</div>
  </body> 
</html>