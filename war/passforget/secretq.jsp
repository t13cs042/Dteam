<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
 <title>秘密の質問</title>
</head>

 <body>

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
	String str, qone, qtwo;
	try{
		str = request.getParameter("Error");
		error = Integer.valueOf(str);
		qone = request.getParameter("Qone");
		qtwo = request.getParameter("Qtwo");
	}catch(Exception e){
		error = 0;
		qone = "";
		qtwo = "";
	}
  %>
  
 
  <!--メールアドレスの変更をします--><br>
  
  <form action = "/change_address" method = "post">
  <font color="red">
  <%			if((error & 16) == 16){ %>   ※ 変更前のメールアドレスが未入力です。<br> 
  <%			}if((error & 2) == 2){ %>	※ 半角小文字で再入力して下さい。<br> 
  <%			}if((error & 4) == 4){ %> ※このアドレスは登録されていません。<br> 
  <%		   }if((error & 32) == 32){ %> ※新しいメールアドレスが未入力です。<br>
  <%		   }if((error & 64) == 64){ %> ※新しいメールアドレス(確認用)が未入力です。<br>
  <%		   }if((error & 1) == 1){ %> ※新しいメールアドレスが一致していません。 もう一度入力してください。<br>
   <%			} %>
  </font>

<div Align="center"/>
  <p>一つ目の質問<br>
  <input type="text" id="qone"></p>
  
    <p>二つ目の質問<br>
  <input type="text" id="qtwo"></p>

<br><br> <input type="submit" value="送信" />
  </form>
  
<br><a href="http://sample-115509.appspot.com/">ホーム画面へ戻る</a><br><br>
</div>

  </body> 
</html>