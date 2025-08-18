<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" type="image/ico" href="/img/favicon.ico" />
<link rel="stylesheet"  href="/css/common2.css" />

<!-- bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/browser-scss@1.0.3/dist/browser-scss.min.js"></script>
<script src="https://code.jquery.com/jquery.min.js"></script>
<style>
 #table {
  margin-bottom : 150px; 
  td {
     text-align : center;
     padding    : 10px;
     border     : 1px solid silver; 
     
     input[type="text"]   { width : 100%;  }
     textarea             { width : 100%;  height: 250px; }
      
     &:nth-of-type(1) {
         width             :  150px;
         background-color  :  #333;
         color             :  white;
     }
     &:nth-of-type(2) { width : 250px; }
     &:nth-of-type(3) {
         width             :  150px;
         background-color  :  black;
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

</style>

</head>
<body>
	<main>
			<%@include file="/WEB-INF/include/menuspaging.jsp" %>
	
	<h2>${ menuDTO.menu_name } 게시글 수정</h2>
	<form action="/BoardPaging/Update" method="POST">
	<input type="hidden" name="idx" value="${ board.idx }"/>
	<input type="hidden" name="menu_id" value="${ board.menu_id }"/>
	<input type="hidden" name="nowpage" value="${ nowpage }"/>
		<table id="table">
			<tr>
				<td>제목</td>
				<td colspan="3"><input type="text" name="title" value="${ board.title }"/></td>
			</tr>
			<tr>
				<td>내용</td>
				<td colspan="3"><textarea name="content">${ board.content }</textarea></td>
			</tr>
			<td colspan="4">
				<input type="submit" value="등록" class="btn btn-dark btn-sm"/>&nbsp;&nbsp;
				<a href="/BoardPaging/List?menu_id=${ board.menu_id }" class="btn btn-dark btn-sm">목록</a>&nbsp;&nbsp;
				<a href="/" class="btn btn-dark btn-sm">Home</a>
			</td>
		</table>
	</form>
	</main>
	
	<script>
			
			const formEl   = document.querySelectorAll('form')[0];
			const titleEl  = document.querySelector('[name="title"]');
			const contentEl = document.querySelector('[name="content"]');
			formEl.addEventListener('submit', function(e) {
				if(titleEl.value == "" ) {
					alert("제목이 입력되지 않았습니다")
					titleEl.focus();
				e.preventDefault();
				e.stopPropagation();
				}
				
				if(contentEl.value == "" ) {
					alert("작성자가 입력되지 않았습니다")
					contentEl.focus();
					e.preventDefault();
					e.stopPropagation();
				}
				return true;
			})
	 </script>
</body>
</html>