<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>要望の送信</title>
</head>
<body>

 <span style="font-size: 200%">ご要望</span>
  <form action="/new_comment" method="post">
    <div>件名</div>
    <div>
      <textarea name="sub" rows="1" cols="40" ></textarea>
    </div>
    <div>ご意見</div>
    <div>
      <textarea name="content" rows="5" cols="40" ></textarea>
    <input type="submit" value="Submit" />
    </div>
  </form>

</body>
</html>