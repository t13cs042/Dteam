<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
 <title>管理</title>
 </head>
 <body>

       <div Align="center"/>
       <br>
       <a href="/manager/unauthaccount.jsp">未認証アカウント</a><br><br>
       <a href="/manager/accountmanage.jsp">アカウント管理</a><br><br>
       <a href="/Home/File_up.jsp">気象情報のアップロード</a><br><br>
       <!-- <a href="/manager/runforecast.jsp">予測の実行</a><br><br> -->
       <form name = "form1" action="/candi_get" method="post">
		<a href="#" onClick="document.form1.submit();">予測の実行</a>
	  	</form><br><br>
       <a href="/logout">ログアウト</a><br><br>
       <br> <span style="font-size: 100%">予測の最終実行日時：X月X日　XX：XX</span>
      	</div>

  </body> 
</html>