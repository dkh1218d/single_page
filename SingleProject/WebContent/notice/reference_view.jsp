<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../header.jsp"%>

<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-view-title"><c:if test="${vo.important.equals('T')}"><img class="imp" src="images/important1.jpg">&nbsp;</c:if>
		${vo.subject}</h2>
		<p class="sub-view-wd">${vo.name} | ${vo.regdate.substring(0,10)}</p>
	</div>
	<div class="reference">
		<c:if test="${list!=null}">
			<c:forEach items="${list}" var="down_file">
				<a href="SYKK?command=notice_reference_download&idx=${down_file.idx}"><img class="down" src="images/download01.jpg">&nbsp;${down_file.file_name}</a>
				<br>
			</c:forEach>
		</c:if>
	</div>
	<div class="sub-view-contnet">
		<c:forEach items="${list}">
			<br>
		</c:forEach>
		<br>
		<p>${vo.contents.replaceAll("\\n","<br>")}</p>
	</div>
	
	<div class="sub-view-bottom">
		<c:if test="${!empty sessionScope.admin && sessionScope.admin.equals('T')}">
		<a href="SYKK?command=notice_reference_modify&idx=${vo.idx}&page=${page}" class="btn-modify">수정</a>&nbsp;&nbsp;
		<a href="javascript:avent()" class="btn-delete">삭제</a>&nbsp;&nbsp;
		</c:if>
		<a href="SYKK?command=notice_reference&page=${page}" class="btn-list">목록</a>&nbsp;&nbsp;
	</div>
		
</div>
<script>
function avent(){
	if(confirm("삭제하시겠습니까") == true) {
		location.href="SYKK?command=notice_reference_delete&idx=${vo.idx}";
	}else {
		return;
	}
}
</script>


<%@ include file="../footer.jsp"%>















