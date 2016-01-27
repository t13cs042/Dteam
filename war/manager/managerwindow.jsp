<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="Dataclass.Predict"%>
<%@ page import="Dataclass.PMF"%>

<%@ page import="javax.jdo.PersistenceManager"%>
<%@ page import="javax.jdo.Query"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>管理</title>
</head>
<body>

	<%
		if (session.getAttribute("status") == null) {
	%>
	管理者以外はこの画面にアクセスできません
	<br>
	<br>
	<a href="../Login/login.jsp">ログイン画面へ戻る</a>
	<br>
	<br>
	<%
		}

		else {
			int status = (Integer) session.getAttribute("status");
			if ( status != 4 ) {
	%>

	管理者以外はこの画面にアクセスできません
	<br>
	<br>
	<a href="../Home/Home_temp.jsp">ホーム画面へ戻る</a>
	<br>
	<br>



	<%
	String date = "実行されていません";
	try{
		PersistenceManager pm = null;
		pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Predict.class);

		List<Predict> pre = (List<Predict>) query.execute();
		String year = "2015";
		date = pre.get(0).getDate();
	}catch(Exception e){
		
	}
	%>

	<div Align="center" />
	<br>
	<a href="/manager/unauthaccount.jsp">未認証アカウント</a>
	<br>
	<br>
	<a href="/manager/accountmanage.jsp">アカウント管理</a>
	<br>
	<br>
	<a href="/Home/File_up.jsp">気象情報のアップロード</a>
	<br>
	<br>
	<a href="/Home/File_up_grape.jsp">収穫量のアップロード</a>
	<br>
	<br>
	<!-- <a href="/manager/runforecast.jsp">予測の実行</a><br><br> -->
	<form name="form1" action="/candi_get" method="post">
		<a href="#" onClick="document.form1.submit();">予測の実行</a>
	</form>
	<br>
	<br>
	<a href="/manager/checkcomment.jsp">要望の確認</a>
	<br>
	<br>
	<a href="/logout">ログアウト</a>
	<br>
	<br>
	<br>
	<span style="font-size: 100%">予測の最終実行日：<%=date %></span>
	</div>

<%
			}
		}	
%>

</body>
</html>