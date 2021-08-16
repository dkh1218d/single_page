<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../header.jsp"%>

<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">유학 사진첩</h2>
		<div class="sub-search">
			<form name="my" method="post" action="SYKK?command=gallery_sa" onsubmit="return check()">
				<select name="search" class="sel">
					<option value="subject" <c:if test="${search.equals('subject')}">selected</c:if>>제목</option>
					<option value="contents" <c:if test="${search.equals('contents')}">selected</c:if>>내용</option>
				</select>
				<input type="text" name="key" class="text" value="${key}">
				<input type="submit" value="검색" class="btn">
			</form>
		</div>
	</div>
		<ul class="sub-content">
			<c:forEach var="sa_list" items="${list}">
			<li><a href="SYKK?command=gallery_sa_view&idx=${sa_list.idx}&page=${page}">
				<img class="gallery_photo" src="gallery/gallery_sa/${sa_list.photo}" alt="${sa_list.photo}">
				</a>
				<p class="p26">
					<a href="SYKK?command=gallery_sa_view&idx=${sa_list.idx}">
						<span class="gallery-title">${sa_list.subject}</span>
					</a>
						<span class="hit">조회수 : ${sa_list.readcnt}</span>
				</p>
			</li>
			</c:forEach>
		</ul>
		
		<div class="paging">
			<ul>
				<li>${pageSkip}</li>
			</ul>
			<c:if test="${!empty sessionScope.admin && sessionScope.admin.equals('T')}"><a href="SYKK?command=gallery_sa_write" class="btn-write">글쓰기</a> </c:if>
		</div>

</div>

<script>
	function check() {
		if(my.key.value=="") {
			alert("검색단어입력하세요");
			my.key.focus;
			return false;
		}
		return true;
	}
</script>

<%@ include file="../footer.jsp"%>















