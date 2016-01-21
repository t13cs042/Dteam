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
	<title>新規登録画面</title>
	</head>
	<body>
	  <% 
  // エラー処理
  	int error;
	int error_2;
	String str;
	String str2;
	try{
		str = request.getParameter("Error");//256
		str2 = request.getParameter("Error_2");//512
		error = Integer.valueOf(str);
		error_2 = Integer.valueOf(str2);
	}catch(Exception e){
		error = 0;
		error_2 = 0;
	}
  %>
		<!-- ブドウ収穫量予測システムのアカウント作成1 -->
		<font color="red">
  <%			if((error & 1) == 1){ %>※姓が未入力です。<br> 
  <%			}if((error & 2) == 2){ %>※名が未入力です。<br> 
  <%			}if((error & 4) == 4){ %>※メールアドレスが未入力です。<br> 
  <%		   }if((error & 8) == 8){ %>※正しいメールアドレスを入力してください。<br>
  <%		   }if((error & 16) == 16){ %>※そのメールアドレスは既に登録済みです。<br>
  <%		   }if((error & 32) == 32){ %>※パスワードが未入力です。<br>
  <%		   }if((error & 64) == 64){ %>※パスワードは半角英数字で入力してください。<br>
  <%		   }if((error & 128) == 128){ %>※パスワード(再入力)が未入力です。<br>
  <%		   }if((error & 256) == 256){ %>※パスワードが一致していません。もう一度入力してください。<br>
  <%			}%>
  <%			if((error_2 & 1) == 1){ %>※作付面積が未入力です。<br> 
  <%			}if((error_2 & 2) == 2){ %>※作付面積は数値で入力してください。<br> 
  <%			}if((error_2 & 4) == 4){ %>※収穫月が未入力です。<br> 
  <%		   }if((error_2 & 8) == 8){ %>※収穫期間が長すぎます。<br>
  <%		   }if((error_2 & 16) == 16){ %>※秘密の質問1が未入力です。<br>
  <%		   }if((error_2 & 32) == 32){ %>※秘密の質問1の答えが未入力です。<br>
  <%		   }if((error_2 & 64) == 64){ %>※秘密の質問2が未入力です。<br>
  <%		   }if((error_2 & 128) == 128){ %>※秘密の質問2の答えが未入力です。<br>
  <%		   }if((error_2 & 256) == 256){ %>※秘密の質問が長すぎます。<br>
  <%			}%>
  		</font>
		
		<form action="/newsignup" method="post">
					名前<br>姓
			<input type = "text" name="familyname" size="30" maxlength="27" >
					名
			<input type = "text" name="firstname" size="30" maxlength="27" ><br>
		
			<font size="3">メールアドレス(半角英数字のみ)
			<input type ="text" name="mail" size="30" maxlength="27" ><br>
			<br>パスワード(半角英数字のみ)<br>
			<input type = "password" name="password" size="10" maxlength="10" ></input>
			<br>パスワードの再入力<br>
			<input type = "password" name="pppassword" size="10" maxlength="10" ></input>
			<br><br>作付面積(a)<br>
		 	<input type="number" name="area">a
			<br>収穫期間(月)<br>
			<select name="start_month">
				<option value=""></option>
				<option value="1">1</option> 
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
			</select>月から
			<select name="finish_month">
				<option value=""></option>
				<option value="1">1</option> 
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>			
			</select>月まで
			
			<br>秘密の質問１ (例)最初に飼ったペットの名前は？<br>
			<textarea name="question1" rows="1" cols="50" >
			</textarea>
			<br>秘密の質問１の答え (例)タマ<br>
			<textarea name="answer1" rows="1" cols="50" >
			</textarea>
			<br>秘密の質問２ (例)旧姓は？<br>
			<textarea name="question2" rows="1" cols="50" >
			</textarea>
			<br>秘密の質問２の答え (例)山梨 花子<br></font>
			<textarea name="answer2" rows="1" cols="50" ></textarea><br><br>
			<center><input type="submit" value="登録" ></center>
		</form>
	</body>
</html>