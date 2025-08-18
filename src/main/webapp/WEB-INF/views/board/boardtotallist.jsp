<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" type="image/ico" href="/img/favicon.ico" />
<link rel="stylesheet" href="/css/common2.css" />

<style>
	#table {
		td {
			padding : 10px;
			text-align : center;
		}
		td:nth-of-type(1) { width : 100px; }
		td:nth-of-type(2) { width : 440px; text-align : left;}
		td:nth-of-type(3) { width : 100px; }
		td:nth-of-type(4) { width : 100px; }
		td:nth-of-type(5) { width : 60px; }
		
		tr:first-child {
			background : #333;
			color : white;
			font-weight : 700;
			td {
				border-color : silver;
			}
			& > td:nth-of-type(2) { text-align : center; }
		}
		
		tr:nth-of-type(2) td {
			text-align : right;
		}
		
	}
</style>

</head>
<body>
	<main>
	<!-- 메뉴 리스트 -->
	<%@include file="/WEB-INF/include/menus.jsp" %>
	
	<%-- <h2>${menu_id} 게시물 목록</h2> --%>
	<h2>${menudto.menu_name} 게시물 목록</h2>
	<%-- <h2>${param.menu_id} 게시물 목록</h2> --%>
	
  <!-- 
  <table id="menu">
  	<tr>
  	   <c:forEach var="menu" items="${ menuList }">
  			<td>
  				<a href="/Board/TotalList?menu_id=${menu.menu_id}">${ menu.menu_name }</a>
  			</td>	
  			</c:forEach>
  	</tr>
  	
  </table>
  
    <h2>${ menu_id }게시물 목록</h2>
   -->
  <hr>
  <!-- 게시물 목록 -->
		<table id="table">
			<tr>
			 	<td>게시판 번호</td>
			 	<td>제목</td>
			 	<td>작성자</td>
			 	<td>작성 날짜</td>
			 	<td>조회수</td>
			</tr>
			
			<tr>					
				<td colspan="5">
					<a href="/Board/WriteForm?menu_id=${ menudto.menu_id }">새 게시물 추가</a>
				</td>
			</tr>
							
			<c:forEach var="board" items="${ boardList }">
			<tr>
				<td>${ board.idx }</td>
				<td>
					<a href="/Board/View?idx=${ board.idx }&menu_id=${menudto.menu_id}">${ board.title }</a>
				</td>
				<td>${ board.writer }</td>
				<td>${ board.regdate }</td>
				<td>${ board.hit }</td>
			</tr>
			</c:forEach> 
		</table>
		<a href="/Menus/List">메뉴목록으로 돌아가기</a>
	</main>
</body>
</html>