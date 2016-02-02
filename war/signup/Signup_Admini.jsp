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
	
	PersistenceManager pm = PMF.get().getPersistenceManager();
	// クエリを作成
	String query = "select from " + LoginDB.class.getName();
	// ユーザデータを取得
	@SuppressWarnings("unchecked")
	List<LoginDB> users = (List<LoginDB>) pm.newQuery(query).execute();
	int flag = 0;
	//既に管理者が登録済みかどうかの確認
	for(LoginDB ur: users){
		if(ur.getStatus() == 4){
			flag = 1;
			break;
		}
	}
	
	if(flag == 1){//既に管理者が登録されていたら
		%>
		この画面へのアクセスは制限されています．
		<br>
		<br>
		<a href="../Login/admin_login.jsp">管理者ログイン画面へ戻る</a>
		<br>
		<br>
<% 	}else{	//管理者がまだ登録されていない　%>
	
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
		<table>
			<tr>
				<td>姓：</td>
				<td><input type = "text" name="familyname" size="20" maxlength="15" placeholder = "Your Family Name"></td>
			</tr>
			<tr>
				<td>名：</td>
				<td><input type = "text" name="firstname" size="20" maxlength="15" placeholder = "Your First Name"></td>
			</tr>
			<tr>
				<td>メールアドレス(半角英数字のみ):</td>
				<td><input type ="email" name="mail" size="30" maxlength="27" placeholder = "Your Email Address"></td>
			</tr>
			<tr>
				<td>パスワード(半角英数字のみ)</td>
				<td><input type = "password" name="password" size="20" maxlength="12" placeholder = "Your Password"></td>
			</tr>
			<tr>
				<td>パスワードの再入力</td>
				<td><input type = "password" name="repassword" size="20" maxlength="12" placeholder = "Confirm Your Password"></td>
			</tr>
		</table>
			<center><input type="submit" value="登録" ></center>
		</form>
	</body>
	<%} %>
</html>