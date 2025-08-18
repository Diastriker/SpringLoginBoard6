<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardList</title>
<link rel="icon" type="image/ico" href="/img/favicon.ico" />
<link rel="stylesheet" href="/css/common2.css" />

<style>
	th { background : #888;
			 border-color: royalblue; 
			  }	
</style>

</head>
<body>
	<main>
		<table>
			<tr>
			 	<th>게시판 번호</th>
			 	<th>메뉴 이름</th>
			 	<th>제목</th>
			 	<th>내용</th>
			 	<th>글쓴이</th>
			 	<th>작성 날짜</th>
			 	<th>좋아요</th>
			</tr>
			<c:forEach var="board" items="${ boardList }">
			<tr>
				<td>${ board.idx }</td>
				<td>${ board.menu_name }</td>
				<td>${ board.title }</td>
				<td>${ board.content }</td>
				<td>${ board.writer }</td>
				<td>${ board.regdate }</td>
				<td>${ board.hit }개</td>
			</tr>
			</c:forEach> 
		</table>
	</main>
</body>
</html>





