<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
 <title>秘密の質問</title>
</head>

 <body>
<%
String address = (String)request.getAttribute("address");
%>

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
<form method="POST" action="/secretq">

  <p><%=(String)request.getAttribute("q1")%><br>
  <input type="text" name="q1"></p>
  
    <p><%=(String)request.getAttribute("q2")%><br>
  <input type="text" name="q2"></p>
  
  
      <font color = "red">
  		<%			if( ((error & 1) == 1) || ((error & 2) == 2) ){ %>   ※登録された内容が一致しません。<br>もう一度入力してください。<br> 
  <%-- 		<%			}if((error & 4) == 4){ %> ※アカウントが未認証です<br> 
  		<%		   }if((error & 8) == 8){ %> ※このアカウントは停止されています<br>
  		<%		   }if((error & 16) == 16){ %> ※管理者はこちらからログインできません<br> --%>
    		<%		   }if((error & 32) == 32){ %> ※登録されていません<br>
  		<%			} %> 
    	</font>
  
  <input type = "hidden" name = "address" value = <%=address%> >

<br><br> <input type="submit" value="送信" />
  </form>
  
<br><a href="http://sample-115509.appspot.com/">ホーム画面へ戻る</a><br><br>
</center>

  </body> 
</html>