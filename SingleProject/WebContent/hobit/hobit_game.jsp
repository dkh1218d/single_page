<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../header.jsp"%>

<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">게임</h2>
		<div class="sub-search">
			<form name="hobit" method="post" action="SYKK?command=hobit_game" onsubmit="return check()">
				<select name="search" class="sel">
					<option value="subject" <c:if test="${search.equals('subject')}">selected</c:if>>제목</option>
					<option value="contents" <c:if test="${search.equals('contents')}">selected</c:if>>내용</option>
				</select>
				<input type="text" name="key" class="text" value="${key}">
				<input type="submit" value="검색" class="btn">
			</form>
		</div>
	</div>
	
		<ul class="sub-port-content">
			<c:forEach var="game" items="${list}">
			<li>
				<span class="date"><em>${game.regdate.substring(8,10) }</em>${game.regdate.substring(0,7) }</span>
				<div class="text-wrap">
					<div class="img-wrap">
						<img  src="hobit/game/${game.photo}" alt="${game.photo}">
					</div>
					<div class="text-body">
						<span>No.${listcnt}</span>
							<p class="title">${game.subject}</p>
								
							<p class="text-cont">${game.contents.replaceAll("\\n","<br>")}</p>
					</div>
					<div style="float:right;">
						<c:if test="${!empty sessionScope.admin && sessionScope.admin.equals('T')}">
							<a href="SYKK?command=hobit_game_modify&idx=${game.idx}&page=${page}" class="gal-modify"><font size=2>수정</font></a>&nbsp;&nbsp;
							<a href="javascript:avent(${game.idx}, ${page})" class="gal-delete"><font size=2>삭제</font></a>&nbsp;&nbsp;
						</c:if>
					</div>
				</div>
			</li>
			<c:set value="${listcnt-1}" var="listcnt" />
			</c:forEach>
		</ul>
		
		<div class="paging">
			<ul>
				<li>${pageSkip}</li>
			</ul>
			<c:if test="${!empty sessionScope.admin && sessionScope.admin.equals('T')}"><a href="SYKK?command=hobit_game_write" class="btn-write">글쓰기</a> </c:if>
		</div>
</div>

<script>
	function check() {
		if(hobit.key.value=="") {
			alert("검색단어입력하세요");
			hobit.key.focus;
			return false;
		}
		return true;
	}
	
	function  avent(idx, page){
		if(confirm("정말 삭제하시겠습니까") == true){
			var del = "idx="+idx+"&page="+page;
			$.ajax({
		        url: "SYKK?command=hobit_game_delete",
		        type:"get",
		        data: del,
		        success: function(result){
		        	if (result > 0){
		                alert("삭제 되었습니다");
		                window.location.reload()
		            }else
		            	 alert("삭제에 실패했습니다");
		        }
			})
		}
		else 
			return;
	}
</script>

<%@ include file="../footer.jsp"%>















