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
	<title>新規登録画面</title>
	</head>
	<body>
		<!-- ブドウ収穫量予測システムのアカウント作成1 -->
		<font size="3">名前</font><br>
		<span style="font-size: 80%">姓</span>
		<input type="text" name="firstname" id="firstname">
		<span style="font-size: 80%">名</span>
		<input type="text" name="lastname" id="lastname">
		<br>
		<font size="3">メールアドレス(半角英数字のみ)</font><br>
		<form action="/c_initial" method="post">
			<input type="text" name="mail" id="mail">
			<br>
			<font size="3">パスワード(半角英数字のみ)</font><br>
			<input type="password" name="pass" id="pass">
			<br>
			<font size="3">パスワードの再入力</font><br>
			<input type="password" name="repass" id="repass">
			<br>
		
		
			<!-- ブドウ収穫量予測システムのアカウント作成2 -->
	
		<font size="3">作付面積(a)<br>
		 	<input type="number" name="area" id="area">
			<br>収穫期間(月)<br>
			<select name="month">
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
			<select name="month">
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
			
			<br>秘密の質問１ (例)最初に飼ったペットの名前は？<br>
			<input type="text" name="question1" id="question1">
			<br>秘密の質問１の答え (例)タマ<br>
			<input type="text" name="answer1" id="answer1">
			<br>秘密の質問２ (例)旧姓は？<br>
			<input type="text" name="question2" id="question2">
			<br>秘密の質問２の答え (例)山梨 花子<br>
			<input type="text" name="answer2" id="answer2">
			<br><br>
			
			<input type="submit" value="登録" >

			</font>		
			
		</form>
		 
		 
		 
	</body>
</html>