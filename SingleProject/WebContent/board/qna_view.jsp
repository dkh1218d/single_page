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
		<p>${vo.contents.replaceAll("\\n","<br>")}</p>
	</div>
	
	<c:if test="${vo.answer!=null}">
		<table><tr><td></td></tr></table><br><br>
		<div class="qareply" style="padding-bottom:30px;">
				<table>
					<tr>
						<td width="20%"><B>${vo.answer_name}</B></td>
						<td width="80%"><p>${vo.answer.replaceAll("\\n","<br>")}</p></td>
					</tr>
				</table>
				<br>
		</div>
	</c:if>
	
	<div class="sub-view-bottom">
		<c:if test="${!empty sessionScope.admin && sessionScope.admin.equals('T')}">
		<a href="SYKK?command=board_qna_reply&idx=${vo.idx}" class="btn-modify">답변</a>&nbsp;&nbsp;
		</c:if>
		<c:if test="${!empty sessionScope.id_no && sessionScope.id_no.equals(vo.id_no)}">
		<a href="javascript:se_mod()" class="btn-modify">수정</a>&nbsp;&nbsp;
		</c:if>
		<c:if test="${!empty sessionScope.id_no && sessionScope.id_no.equals(vo.id_no)}">
		<a href="javascript:avent()" class="btn-delete">삭제</a>&nbsp;&nbsp;
		</c:if>
		<a href="SYKK?command=board_qna&page=${page}" class="btn-list">목록</a>&nbsp;&nbsp;
	</div>
		
</div>
<script>
	function se_mod(){
		if(${vo.answer!=null}){
			alert('답변이 작성된 게시글은 수정할 수 없습니다.');
			return;
		}
		location.href="SYKK?command=board_qna_modify&idx=${vo.idx}&page=${page}";
	}
	
	function avent(){
		if(confirm("삭제하시겠습니까") == true) {
			location.href="SYKK?command=board_qna_delete&idx=${vo.idx}";
		}else {
			return;
		}
	}
</script>


<%@ include file="../footer.jsp"%>















