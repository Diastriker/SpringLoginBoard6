<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UserList</title>

<link rel="icon" type="image/ico" href="/img/favicon.ico" />
<link rel="stylesheet" href="/css/common2.css" />

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
	
	td[colspan="8"] {text-align : right;}
	/*
	tr:first-child td {
		border-color : white;
	}
	*/
	
	.overlay {
	 position: fixed;
	 top: 0;
	 left: 0;
	 width: 100%;
	 height: 100%;
	 background: rgba(0, 0, 0, 0.5); /* 투명도를 더하는 스타일(아주 유용한듯) */
	 display: flex;
	 justify-content: center;
	 align-items: center;
}

	.modal {
	  background: white;
	  padding: 20px;
	  border-radius: 8px;
	  text-align: center;
	}
	
	button {
	  margin: 10px;
	  padding: 8px 16px;
	}
	
</style>

</head>
<body>
	<main>
		<h2>유저 목록</h2>
		<table>
			<tr>
				<td>User_id</td>
				<td>Passwd</td>
				<td>User_Name</td>
				<td>Email</td>
				<td>등급</td>
				<td>Indate</td>
				<td>삭제</td>
				<td>수정</td>
			</tr>
			
			<tr>
				<td colspan="8">
					<a href="/Users/WriteForm">새 유저 추가</a>
				</td>
			</tr>
			
			<!--             변수  =  컨트롤러에서 받은 변수 -->
			<c:forEach var="user" items="${ userList }">
			<tr>
				<td>${ user.userid }</td> 
				<td>${ user.passwd }</td> 
				<td>${ user.username }</td> 
				<td>${ user.email }</td> 
				<td>${ user.memlevel }</td> 
				<td>${ user.indate }</td>  

				<td><a href="/Users/Delete?userid=${ user.userid }" class="delete">삭제</a></td>
				<td><a href="/Users/UpdateForm?userid=${ user.userid }" >수정</a></td>
			</tr>
			</c:forEach>
		</table>
	</main>
		<script>
			const delBtns = document.querySelectorAll('.delete')
			delBtns.forEach(btns => {
				btns.addEventListener('click', function(e) {
					e.preventDefault(); // 기존 기능 실행취소
					
					const overlay = document.createElement('div');
					overlay.className= "overlay";
					
					const modal = document.createElement('div');
					modal.className = "modal";
					
					const message = document.createElement('p')
					message.innerHTML = "정말로 삭제 하시겠습니까?";
					
					const check = document.createElement('button')
					check.innerHTML = "확인";
					check.addEventListener('click', function() {
						alert("삭제성공")
						document.body.removeChild(overlay);
						window.location.href = btns.href; // 다시 기존기능 실행가능
					})
					
					const cancel = document.createElement('button')
					cancel.innerHTML = "취소";
					cancel.addEventListener('click', function() {
						document.body.removeChild(overlay);
					})
					
					document.body.appendChild(overlay);
					overlay.appendChild(modal);
					modal.appendChild(message);
					modal.appendChild(check);
					modal.appendChild(cancel);
				})
			})

			
		</script>
</body>
</html>





