<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>収穫月の変更</title>
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
  
  
  収穫月の変更をします<br>
  
  <form action = "/change_month" method = "post">
  <font color="red">
  <%			if((error & 16) == 16){ %>   ※開始月が選択されていません。<br> 
  <%			}if((error & 8) == 8){ %>	※終了月が選択されていません。<br> 
  <%			}if((error & 4) == 4){ %> ※収穫期間が長すぎます<br> 
  <%		   }if((error & 32) == 32){ %> ※新しいパスワードが未入力です。<br>
  <%		   }if((error & 64) == 64){ %> ※新しいパスワード(確認用)が未入力です。<br>
  <%		   }if((error & 1) == 1){ %> ※新しいパスワードが一致していません。 もう一度入力してください。<br>
   <%			} %>
  </font>
  
  <p>新しい収穫月<br>
<select name="start">
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
			<select name="finish">
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
   
   <br>
   <br>
   
   
	<input value="変更" type="submit" >
   
   </form>
   
   
  <br>
   <br>
   
   
    <a href="/settingchange/setting.jsp">設定変更画面へ戻る</a><br><br>
   <a href="../Home/Home_temp.jsp">ホーム画面へ戻る</a><br><br>
   
   
  </body>

</html>