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
  margin-bottom : 150px; 
  td {
     text-align : center;
     padding    : 10px;
     border     : 1px solid silver;
     
     &:nth-of-type(1) {
         width             :  150px;
         background-color  :  #444;
         color             :  white;
     }
     &:nth-of-type(2) { width : 250px; }
     &:nth-of-type(3) {
         width             :  150px;
         background-color  :  #444;
         color             :  white;
     }
     &:nth-of-type(4) { width : 250px;  }
  }  
  
  tr:nth-of-type(3) td:nth-of-type(2) {
     text-align : left;
  }
  
  tr:nth-of-type(4) td:nth-of-type(2) {
     height     : 250px;
     width      : 600px; 
     text-align : left;
     vertical-align: baseline;      
  }
  
  tr:last-of-type  td {
     background: white;
     color :     black; 
  }
   
 }
 
 /* class="btn btn-dark btn-sm" */
 a.btn.btn-dark.btn-sm:hover {
    text-decoration: none;    
 }

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
	 z-index: 1000;
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

	<%@include file="/WEB-INF/include/menuspaging.jsp" %>
	
	<h2>${ menuList[0].menu_name } 게시글 내용</h2>
	
		<table id="table">
			<tr>
				<td>글번호</td>
				<td>${ board.idx }</td>
				<td>조회수</td>
				<td>${ board.hit }</td>
			</tr>
			<tr>
				<td name="writer">작성자</td>
				<td>${ board.writer }</td>
				<td>게시 날짜</td>
				<td>${ board.regdate }</td>
			</tr>
			<tr>
				<td>제목</td>
				<td colspan="3" name="title">${ board.title }</td>
			</tr>
			<tr>
				<td>내용</td>
				<td colspan="3" >${ board.content }</td>
			</tr>
			<tr>
				<td colspan="4">
				<a href="/BoardPaging/WriteForm?menu_id=${ board.menu_id }&nowpage=${nowpage}">[새글 쓰기]</a>&nbsp;&nbsp;
				
			  <c:if test="${board.writer eq login.userid}">
				<a href="/BoardPaging/UpdateForm?idx=${ board.idx }&menu_id=${ board.menu_id }&nowpage=${nowpage}">[게시글 수정하기]</a>&nbsp;&nbsp;
				<a href="/BoardPaging/DelView?idx=${ board.idx }&menu_id=${ board.menu_id }&nowpage=${nowpage}" class="delete">[게시글 삭제하기]</a>&nbsp;&nbsp;
				</c:if>
				
				<a href="/BoardPaging/List?menu_id=${ board.menu_id }&nowpage=${nowpage}" id="goList">[목록]</a>&nbsp;&nbsp;
				<a href="/">Home</a>
				</td>
			</tr>
		</table>
	</main>
	
			<script>
			
			
			const delBtns = document.querySelectorAll('.delete');
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
						window.location.href = btns.href; // btns 의 href 내용을 다시 window.location 에 넣는다
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