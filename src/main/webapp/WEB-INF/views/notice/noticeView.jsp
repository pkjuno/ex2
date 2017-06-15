<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>NOTICE VIEW</h1>
	
	<a href="noticeDelete?num=${dto.num}">삭제</a>
	<a href="noticeUpdate?num=${dto.num}">수정</a>
	<a href="noticeList">리스트</a>
	
	<hr>
	
	<h3>작성자 : ${dto.writer}</h3>
	<h3>제목 : ${dto.title}</h3>
	<h3>내용 : ${dto.contents}</h3>
	<h3>번호 : ${dto.num}</h3>
	<h3>조회수 : ${dto.hit}</h3>
	
</body>
</html>