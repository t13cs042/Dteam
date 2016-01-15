<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Dataclass.PMF" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="Dataclass.LoginDB" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザ一覧</title>
</head>
<body>
<% 
		PersistenceManager pm = PMF.get().getPersistenceManager();

		List<LoginDB>	users	= null;
		String			query1		= null;

		// 教員データの取得(全部取得)
		query1		= new String( "select from " + LoginDB.class.getName());
		users	= (List<LoginDB>)pm.newQuery(query1).execute();

		%><h3>ユーザ一覧</h3>
			<%

		// 教員データがなかったら
		if( users == null || users.isEmpty() ){
%>
			<p>ユーザ情報がありません。</p>
			<%
		// データがあったら、それを表にして表示
		}else{ %>
			<form action="/user_eliminator" method="post" name="check">
				<table border="1" cellpadding="5">
					<tr>
						<th>削除</th>
						<th>名字</th>
						<th>名前</th>
						<th>メールアドレス</th>
						<th>パスワード</th>
						<th>作付面積(a)</th>
						<th>収穫開始月</th>
						<th>収穫終了月</th>
						<th>秘密の質問１</th>
						<th>答え１</th>
						<th>秘密の質問２</th>
						<th>答え２</th>
						<th>ユーザの状態</th>
					</tr>

					<%					// 1人ずつ表示
					for( LoginDB user : users ){ %>
					<tr>
						<td><center>
								<input type="checkbox" name="check"
									value="<%= user.getId() %>">
							</center></td>
						<% // 名字 %>
						<td><%= user.getFamilyname() %></td>
						<% // 名前 %>
						<td><%= user.getfirstname() %></td>
						<% // メールアドレス %>
						<td><%= user.getMail() %></td>
						<% // パスワード %>
						<td><%= user.getPassword() %></td>
						<% // 作付面積 %>
						<td><%= user.getArea() %></td>
						<% // 収穫開始月 %>
						<td><%= user.getStart_month() %>月</td>
						<% // 収穫終了月 %>
						<td><%= user.getFinish_month() %>月</td>
						<% // 秘密の質問１ %>
						<td><%= user.getQuestion1() %></td>
						<% // 答え１ %>
						<td><%= user.getAnswer1() %></td>
						<% //秘密の質問２ %>
						<td><%= user.getQuestion2() %></td>
						<% // 答え２ %>
						<td><%= user.getAnswer2() %></td>
						<% // ユーザ状態 %>
						<td><%= user.getStatus() %></td>
					</tr>
					<%						
					}%>
				</table>
				<input type ="submit" value = "削除" name = "eliminate">
			</form>
			<br>
			<%	} %>
			<a href = "/Login/login.html">ログイン画面へ戻る</a>

</body>
</html>