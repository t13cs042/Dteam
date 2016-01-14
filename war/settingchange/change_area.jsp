<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>作付面積</title>
  </head>

  <body>
  <% 
  // エラーの種類
	// 0 : エラーなし
	// 1 : 同じIDの担任が既に登録されている
	// 2 : 同じIDの学生が既に登録されている
	// 4 : ID欄が未入力
	// 8 : 仮パスワード欄が未入力
	//16 : 半角小文字で入力されていない 
    int error;
	String str, before, after;
	try{
		str = request.getParameter("Error");
		error = Integer.valueOf(str);
		before = request.getParameter("Before");
		after = request.getParameter("After");
	}catch(Exception e){
		error = 0;
		before = "";
		after = "";
	}
  %>
  
  
  作付面積の変更をします<br>
  
  <form action = "/change_area" method = "post">
  <font color="red">
  <%			if((error & 16) == 16){ %>   ※ 変更前のパスワードが未入力です。<br> 
  <%			}if((error & 2) == 2){ %>	※ 半角小文字,12文字以内で再入力して下さい。<br> 
  <%			}if((error & 4) == 4){ %> ※パスワードが間違っています。<br> 
  <%		   }if((error & 32) == 32){ %> ※新しいパスワードが未入力です。<br>
  <%		   }if((error & 64) == 64){ %> ※新しいパスワード(確認用)が未入力です。<br>
  <%		   }if((error & 1) == 1){ %> ※新しいパスワードが一致していません。 もう一度入力してください。<br>
   <%			} %>
  </font>
  
  <p>新しい作付面積<br>
  <input type="text" name="area">a</p>
   
   <br>
   
   <Div Align="center">
	<input value="変更" type="submit" >
   </Div>
   </form>
   
   
  
   <br>
   
   <Div Align="right">
   <a href="http://sample-115509.appspot.com/">ホーム画面へ戻る</a><br><br>
   </Div>
   
  </body>

</html>