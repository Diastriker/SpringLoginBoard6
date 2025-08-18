<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

	<%@include file="/WEB-INF/include/menus.jsp" %>
	
	<h2>${ menuDTO.menu_name } 게시글 내용</h2>
	
		<table id="table">
			<tr>
				<td>글번호</td>
				<td>${ boardList.idx }</td>
				<td>조회수</td>
				<td>${ boardList.hit }</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${ boardList.writer }</td>
				<td>게시 날짜</td>
				<td>${ boardList.regdate }</td>
			</tr>
			<tr>
				<td>제목</td>
				<td colspan="3">${ boardList.title }</td>
			</tr>
			<tr>
				<td>내용</td>
				<td colspan="3">${ boardList.content }</td>
			</tr>
			<tr>
				<td colspan="4">
				<a href="/Board/WriteForm?menu_id=${ boardList.menu_id }">[새글 쓰기]</a>&nbsp;&nbsp;
				<a href="/Board/UpdateForm?idx=${ boardList.idx }">[게시글 수정하기]</a>&nbsp;&nbsp;
				<a href="/Board/DelView?idx=${ boardList.idx }&menu_id=${ boardList.menu_id }" class="delete">[게시글 삭제하기]</a>&nbsp;&nbsp;
				<a href="/Board/TotalList?menu_id=${ boardList.menu_id }">[목록]</a>&nbsp;&nbsp;
				<a href="/">Home</a>
				</td>
			</tr>
		</table>
	</main>
	
			<script>
			
			const goListEl = document.getElementById('goList');
			goListEl.onclick = function() {
				location.href='/BoardPaging/List?nowpage=${nowpage}&menu_id=${ menuDTO.menu_id }'
			}
			
			const formEl   = document.querySelectorAll('form')[0];
			const titleEl  = document.querySelector('[name="title"]');
			const writerEl = document.querySelector('[name="writer"]');
			formEl.addEventListener('submit', function(e) {
				if(titleEl.value == "" ) {
					alert("제목이 입력되지 않았습니다")
					titleEl.focus();
				e.preventDefault();
				e.stopPropagation();
				}
				
				if(writerEl.value == "" ) {
					alert("작성자가 입력되지 않았습니다")
					writerEl.focus();
					e.preventDefault();
					e.stopPropagation();
				}
				return true;
			})
			
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