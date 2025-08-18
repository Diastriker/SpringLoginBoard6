<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="icon" type="image/ico" href="/img/favicon.ico" />
<link rel="stylesheet" href="/css/common.css" />

<style>
	td { text-align: center; }
	
	tr:first-child{
		background-color : black;
		color : white;
		font-weight : bold;
		/* SCSS 문법 (sass 문법중에 하나) */
		/* SCSS 문법 : 별도 라이브러리 필요(스프링에서 라이브러리 없이 자동적용) */
		td {
			border-color : red;
			color : white;
		}
	}
	
	td[colspan="5"] {text-align : right;}
	/*
	tr:first-child td {
		border-color : white;
	}
	*/
	
</style>

</head>
<body>
	<main>
		<h2>메뉴 목록</h2>
		<table>
			<tr>
				<td>Menu_id</td>
				<td>Menu_name</td>
				<td>Menu_seq</td>
				<td colspan="2">변경사항</td>
			</tr>
			
			<tr>
				<td colspan="5">
					<a href="/Menus/WriteForm">새 메뉴 추가</a>
					<a href="/Menus/WriteForm2?menu_name=${menu_name}">새 메뉴 추가2</a>
				</td>
			</tr>
			
			<!--             변수  =  컨트롤러에서 받은 변수 -->
			<c:forEach var="menu" items="${ menuList }">
			<tr>
				<td>${ menu.menu_id }</td> 
				<td><a href="/Board?menu_name=${ menu.menu_name }">${ menu.menu_name }</a></td>
				<td>${ menu.menu_seq }</td>
				<td><a href="/Menus/Delete?menu_id=${ menu.menu_id }">삭제</a></td>
				<td><a href="/Menus/UpdateForm?menu_id=${ menu.menu_id }" >수정</a></td>
			</tr>
			</c:forEach>
		</table>
	</main>
</body>
</html>