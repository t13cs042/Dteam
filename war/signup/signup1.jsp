<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="signup_.PMF" %>
<%@ page import="signup_.LoginDB" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="javax.jdo.Query" %>
<%@ page import="java.util.List" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>新規登録画面</title>
	</head>
	<body>
		<!-- ブドウ収穫量予測システムのアカウント作成1 -->
		<font size="3">名前</font><br>
		<span style="font-size: 80%">姓</span>
		<textarea rows="1" cols="10" ></textarea>
		<span style="font-size: 80%">名</span>
		<textarea rows="1" cols="10" ></textarea>
		<br>
		<font size="3">メールアドレス(半角英数字のみ)</font><br>
		<form action="/new" method="post">
			<textarea name="mail" rows="1" cols="27" ></textarea>
			<br>
			<font size="3">パスワード(半角英数字のみ)</font><br>
			<textarea name="password" rows="1" cols="27" ></textarea>
			<br>
			<font size="3">パスワードの再入力</font><br>
			<textarea name="ppassword" rows="1" cols="27" ></textarea>
			<br>
			</form>
			<form action ="signup2.jsp">
			<div style="margin-left:120px">
			<input type="submit" name = "button" value="次へ">
			</div>
		</form>
		
				<p>
	<!-- スクリプトレット -->
			<%
			PersistenceManager pm = null;
			try {
			    pm = PMF.get().getPersistenceManager();
			    Query query = pm.newQuery(LoginDB.class);
			    List<LoginDB> date = (List<LoginDB>) query.execute();
				// すべてのエンティティの表示
				for (LoginDB ll : date) {
			%>
			
			<div>
	      <%= ll.getMail() %> が <%= ll.getPassword().toString() %> Mail-Pass
			</div>            
		    <!--Div that will hold the pie chart-->
		    <div id="chart_div"></div>
			<%
			    }
			} finally {
			    if (pm != null && !pm.isClosed())
			       pm.close();
			}
			%>
		</p>
	</body>
</html>