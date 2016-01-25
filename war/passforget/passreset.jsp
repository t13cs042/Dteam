<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
 <title>パスワードの再設定</title>
</head>

 <body>

<%
String address = (String)request.getAttribute("address");
%>


 <!--条件付け、不正時に表示するもの-->
  <% 
  // エラーの種類
	// 0 : エラーなし
	// 1 : 同じIDの担任が既に登録されている
	// 2 : 同じIDの学生が既に登録されている
	// 4 : ID欄が未入力
	// 8 : 仮パスワード欄が未入力
	//16 : 半角小文字で入力されていない 
    int error;
	String str, after1, after2;
	try{
		str = request.getParameter("Error");
		error = Integer.valueOf(str);
		after1 = request.getParameter("after1");
		after2 = request.getParameter("after2");
	}catch(Exception e){
		error = 0;
		after1 = "";
		after2 = "";
	}
  %>
  
 
  <!--メールアドレスの変更をします--><br>

<div Align="center"/>

パスワードの再設定をします。<br>
新しいパスワードを入力してください。<br><br><br>

  <p>新しいパスワード(半角英数字、12文字以内)<br><br>
  <form action = "/passreset" method = "post">
  <input type="text" name="after1"></p>
  
    <p>パスワードの再入力<br><br>
  <input type="text" name="after2"></p>

  
  <font color="red">
  <%			if( ((error & 16) == 16) || ((error & 2) == 2) || ((error & 32) == 32) 
              || ((error & 64) == 64) || ((error & 1) == 1) )  { %>   ※現在のパスワードが未入力です。<br> 
   <%			} %>
  </font>

  <input type = "hidden" name = "address" value = <%=address%> ><%--add--%>

<br><br> <input type="submit" value="送信" >
  </form>
  
</div>

  </body> 
</html>