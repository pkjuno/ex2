<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="notice${path}" method="post">
		<p><input type="hidden" name="num" value="${dto.num}" ></p>	
		<p>작성자 : <input type="text" name="writer" value="${dto.writer}"></p>
		<p>제목 : <input type="text" name="title" value="${dto.title}"></p>
		<p>내용 : <input type="text" name="contents"  value="${dto.contents}"></p>
		<input type="submit" value="전송">
		
	</form>
</body>
</html>