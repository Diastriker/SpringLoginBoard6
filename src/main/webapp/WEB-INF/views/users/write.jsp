<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert</title>
<link rel="icon" type="image/ico" href="/img/favicon.ico" />
<link rel="stylesheet" href="/css/common2.css" />

<style>
	td { 
	 	padding : 10px;
	 	width : 700px;
	 	text-align: center;
	 }
	 
  td:nth-of-type(1) {
	 	width : 200px;
	 } 
	
	input { width : 100%; }
	input[type=submit] { width : 100px; }	
	

</style>

</head>
<body>
	<main>
		<h2>새 유저 추가</h2>
		<form action="/Users/Write" method="POST">
			<table>
				<tr>
					<td>유저 아이디(6~12)</td>
					<td><input type="text" name="userid" maxlength="12"/></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="passwd"/></td>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td><input type="password" name="passwd2"/></td>
				</tr>
				<tr>
					<td>유저 이름</td>
					<td><input type="text" name="username"/></td>
				</tr>
				<tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" name="email"/></td>
				</tr>
				<tr>
				<tr>
				<tr>
					<td colspan="2">
					<input type="submit" value="등록"/>
					</td>
				</tr>
			</table>
		</form>
	</main>
	
	<script>
		const formEl = document.querySelector('form');
		
		formEl.addEventListener('submit', function(e) {
		//	alert('ok')
			const inputEl1 = document.querySelector('[name="userid"]')
			if(inputEl1.value.trim().length < 6 || inputEl1.value.trim().length > 12 ) {
				alert("유저아이디는 6~12자 사이입니다")
				e.stopPropagation(); // 이벤트 버블링 방지
				e.preventDefault();  // 이벤트 취소
				inputEl1.focus();
				return false;
			}
			
			// 비번
			const inputEl2 = document.querySelector('[name="passwd"]')
			if(inputEl2.value.trim() == '') {
				alert("비밀번호가 입력 되지 않았습니다")
				e.stopPropagation();
				e.preventDefault();
				inputEl2.focus();
				return false;
			}
			
			// 비번확인
			const inputEl3 = document.querySelector('[name="passwd"]')
			if(inputEl3.value.trim() == '') {
				alert("비밀번호확인이 입력 되지 않았습니다")
				e.stopPropagation();
				e.preventDefault();
				inputEl3.focus();
				return false;
			}
			
			// 비번 == 비번확인
			if( inputEl2.value != inputEl3.value ) {
				alert("암호가 일치 하지 않습니다")
				e.stopPropagation();
				e.preventDefault();
				inputEl3.focus();
				return false;
			}
			
			// 유저 이름
			const inputEl4 = document.querySelector('[name="username"]')
			if(inputEl4.value.trim() == '') {
				alert("유저 이름이 입력 되지 않았습니다")
				e.stopPropagation();
				e.preventDefault();
				inputEl4.focus();
				return false;
			}
			
			// 이메일
			const inputEl5 = document.querySelector('[name="email"]')
			if(inputEl5.value.trim() == '') {
				alert("이메일이 입력 되지 않았습니다")
				e.stopPropagation();
				e.preventDefault();
				inputEl5.focus();
				return false;
			}
			const inputEl6 = document.querySelector('[name="upoint"]')
			if(inputEl6.value.trim() == '') {
				alert("포인트가 입력 되지 않았습니다")
				e.stopPropagation();
				e.preventDefault();
				inputEl6.focus();
				return false;
			}
			const inputEl7 = document.querySelector('[name="indate"]')
			if(inputEl7.value.trim() == '') {
				alert("가입 날짜가 입력 되지 않았습니다")
				e.stopPropagation();
				e.preventDefault();
				inputEl7.focus();
				return false;
			}
		})
	</script>
	
</body>
</html>





