<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>メールアドレス変更</title>
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
  
 
  メールアドレスの変更をします<br>
  
  <form action = "/change_address" method = "post">
  <font color="red">
  <%			if((error & 16) == 16){ %>   ※現在のメールアドレスが未入力です。<br> 
  <%			}if((error & 4) == 4){ %> ※入力されたメールアドレスは登録済みです。<br> 
   <%			}if((error & 2) == 2){ %> ※正しいメールアドレスを入力してください<br> 
  <%		   }if((error & 32) == 32){ %> ※新しいメールアドレスが未入力です。<br>
  <%		   }if((error & 64) == 64){ %> ※新しいメールアドレス(確認用)が未入力です。<br>
    <%		   }if((error & 8) == 8){ %> ※現在のメールアドレスが間違っています。<br>
  <%		   }if((error & 1) == 1){ %> ※新しいメールアドレスが一致していません。 もう一度入力してください。<br>
   <%			} %>
  </font>
  
  <p>現在のメールアドレス<br>
  <input type="email" name="before"></p>
  
   <p>新しいメールアドレス<br>
  <input type="email" name="after1"></p>
   
   <p>新しいメールアドレス(再入力)<br>
   <input type="email"name="after2"></p>
   
   
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