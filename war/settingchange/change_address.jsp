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
  // エラーの種類
	// 0 : エラーなし
	// 1 : 同じIDの担任が既に登録されている
	// 2 : 同じIDの学生が既に登録されている
	// 4 : ID欄が未入力
	// 8 : 仮パスワード欄が未入力
	//16 : 半角小文字で入力されていない 
    int error;
	String str, id, pass;
	try{
		str = request.getParameter("Error");
		error = Integer.valueOf(str);
		id = request.getParameter("Id");
		pass = request.getParameter("Pass");
	}catch(Exception e){
		error = 0;
		id = "";
		pass = "";
	}
  %>
  
  
  <!--  
  <BODY leftmargin="500" topmargin="50" rightmargin="400 "bottommargin="0">
  -->
  <!-- 
  <script type="text/javascript">
      function handan(before, after1, after2){
        if(before != 0){
           document.getElementById("kekka").innerHTML = "変更前のメールアドレスが存在しません。<br> 確認の上、もう一度入力してください。";
        } else if( after1 != after2 ){
           document.getElementById("kekka").innerHTML = "新しいメールアドレスが一致していません。<br> もう一度入力してください";
        }
        else{
        	document.getElementById("kekka").innerHTML = " <br> ";
        }
      }
   </script>
  
   -->
  メールアドレスの変更をします<br>
  
  <form action = "/changeAddress" method = "post">
  <font color="red">
  <%			if((error & 16) == 16){ %>   ※ 変更前のメールアドレスが未入力です。<br> 
  <%			}if((error & 2) == 2){ %>	※ 半角小文字で再入力して下さい。<br> 
  <%			}if((error & 4) == 4){ %> ※このアドレスは登録されていません。<br> 
  <%		   }if((error & 32) == 32){ %> ※新しいメールアドレスが未入力です。<br>
  <%		   }if((error & 64) == 64){ %> ※新しいメールアドレス(確認用)が未入力です。<br>
  <%		   }if((error & 1) == 1){ %> ※新しいメールアドレスが一致していません。 もう一度入力してください。<br>
   <%			} %>
  </font>
  
  <p>変更前のメールアドレス<br>
  <input type="text" name="before"></p>
  
   <p>新しいメールアドレス<br>
  <input type="text" name="after1"></p>
   
   <p>新しいメールアドレス(再入力)<br>
   <input type="text"name="after2"></p>
   
   
   <br>
   
   <Div Align="center">
	<input value="登録" type="submit" >
   </Div>
   </form>
   
   
  
   <br>
   
   <Div Align="right">
   <a href="http://sample-115509.appspot.com/">ホーム画面へ戻る</a><br><br>
   </Div>
   
  </body>

</html>