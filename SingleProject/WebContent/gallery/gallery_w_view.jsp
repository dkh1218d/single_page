<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../header.jsp"%>

<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-view-title">${vo.subject}</h2>
		<p class="sub-view-wd">${vo.name} | ${vo.regdate.substring(0,10)}</p>
	</div>
	<div class="sub-view-contnet">
		<br>
		<p>${vo.contents.replaceAll("\\n","<br>")}</p>
		<br><br>
		<img class="contents_photo" src="gallery/gallery_w/${vo.photo}" alt="${vo.photo}">
	</div>
	
	<div class="sub-view-bottom">
		<c:if test="${!empty sessionScope.admin && sessionScope.admin.equals('T')}">
		<a href="SYKK?command=gallery_w_modify&idx=${vo.idx}&page=${page}" class="btn-modify">수정</a>&nbsp;&nbsp;
		<a href="javascript:avent()" class="btn-delete">삭제</a>&nbsp;&nbsp;
		</c:if>
		<a href="SYKK?command=gallery_w&page=${page}" class="btn-list">목록</a>&nbsp;&nbsp;
	</div>
		
</div>
<script>
function avent(){
	if(confirm("삭제하시겠습니까") == true) {
		location.href="SYKK?command=gallery_w_delete&idx=${vo.idx}";
	}else {
		return;
	}
}
</script>


<%@ include file="../footer.jsp"%>















