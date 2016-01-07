<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	</head>
	<body>
		<!-- ブドウ収穫量予測システムのアカウント作成2 -->
		<font size="3">作付面積(a)<br>
	 		<form action="/new" method="post">
		 	<textarea name="content" rows="1" cols="10" >
		 	</textarea>a</form>
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
			<textarea name="content" rows="1" cols="50" >
			</textarea>
			<br>秘密の質問１の答え (例)タマ<br>
			<textarea name="content" rows="1" cols="50" >
			</textarea>
			<br>秘密の質問２ (例)旧姓は？<br>
			<textarea name="content" rows="1" cols="50" >
			</textarea>
			<br>秘密の質問２の答え (例)山梨 花子<br>
			<textarea name="content" rows="1" cols="50" >
			</textarea><br><br><center>
				<form action="/signup"><input type="submit" value="登録" ></form>
			</center>
		</font>
	</body>
</html>