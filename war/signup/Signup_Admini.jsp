<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Dataclass.PMF" %>
<%@ page import="Dataclass.LoginDB" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="javax.jdo.Query" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>管理者登録画面</title>
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
		<!-- ブドウ収穫量予測システムの管理者アカウント作成 -->
		<form action="/register_administer" method="post">
		
		<font color = "red">
	  	<%			if((error & 1) == 1){ %>   ※メールアドレスが既に登録されています<br> 
  		<%			}if((error & 2) == 2){ %> ※メールアドレスが未入力です<br> 
  		<%			}if((error & 4) == 4){ %> ※パスワードを入力してください<br> 
  		<%		   }if((error & 8) == 8){ %> ※パスワードが違います<br>
  		<%		   }if((error & 16) == 16){ %> ※パスワードが長すぎます<br>
  		<%			} %> 
    	</font>
		
					名前<br>姓：
			<input type = "text" name="familyname" size="30" maxlength="27" >
					名：
			<input type = "text" name="firstname" size="30" maxlength="27" ><br>
		
			<font size="3">メールアドレス(半角英数字のみ)
			<input type ="text" name="mail" size="30" maxlength="27" ><br>
			<br>パスワード(半角英数字のみ)<br>
			<input type = "password" name="password" size="10" maxlength="10" ></input>
			<br>パスワードの再入力<br>
			<input type = "password" name="repassword" size="10" maxlength="10" ></input>
			
			<center><input type="submit" value="登録" ></center>
		</form>
	</body>
</html>