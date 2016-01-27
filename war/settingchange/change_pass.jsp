<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>パスワード変更</title>
  </head>

  <body>
  
  
<%
		if (session.getAttribute("status") == null) {
	%>
	この画面にアクセスできるのは承認されたユーザのみです
	<br>
	<br>
	<a href="../Login/login.jsp">ログイン画面へ戻る</a>
	<br>
	<br>
	<%
		}

		else {
			int status = (Integer) session.getAttribute("status");
			if ( status != 1 ) {
	%>

	この画面にアクセスできるのは承認されたユーザのみです
	<br>
	<br>
	<a href="../Login/login.jsp">ログイン画面へ戻る</a>
	<br>
	<br>
<% }else{%>
  
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
  
  
  パスワードの変更をします<br>
  
  <form action = "/change_pass" method = "post">
  <font color="red">
  <%			if((error & 16) == 16){ %>   ※現在のパスワードが未入力です。<br> 
  <%			}if((error & 2) == 2){ %>	※半角小文字,12文字以内で再入力して下さい。<br> 
  <%			}if((error & 4) == 4){ %> ※パスワードが間違っています。<br> 
  <%		   }if((error & 32) == 32){ %> ※新しいパスワードが未入力です。<br>
  <%		   }if((error & 64) == 64){ %> ※新しいパスワード(確認用)が未入力です。<br>
  <%		   }if((error & 1) == 1){ %> ※新しいパスワードが一致していません。 もう一度入力してください。<br>
   <%			} %>
  </font>
  
  <p>現在のパスワード<br>
  <input type="text" name="before"></p>
  
   <p>新しいパスワード(半角英数記号,12文字以内)<br>
  <input type="text" name="after1"></p>
   
   <p>新しいパスワード(再入力)<br>
   <input type="text"name="after2"></p>
   
   
   <br>
   <br>
   
   
	<input value="変更" type="submit" >
   
   </form>
   
   
  <br>
   <br>
   
 
    <a href="/settingchange/setting.jsp">設定変更画面へ戻る</a><br><br>
   <a href="../Home/Home_temp.jsp">ホーム画面へ戻る</a><br><br>
 
 <%}} %>
   
  </body>

</html>