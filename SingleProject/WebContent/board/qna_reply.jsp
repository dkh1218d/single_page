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
		<p>${vo.contents}</p>
	</div>
	
	
	<div class="qareply" style="padding-bottom:30px;">
		<h2 style="padding-bottom:16px;">답변</h2>
			<form name="my" method="post">
				<input type="hidden" name="idx" value="${vo.idx}">
				<table>
					<tr>
						<td width="15%"><B><c:if test="${!empty sessionScope.id_no}">${sessionScope.nick_name}
						<input type="hidden" name="answer_name" value="${sessionScope.nick_name}">
						</c:if>
						<c:if test="${empty sessionScope.id_no}"><font color=red>로그인이 필요합니다</font></c:if>
						</B></td>
						<td width="70%"><textarea name="answer" placeholder="답글을 작성하세요" style="width:100%; height:200px;" maxlength="650">${vo.answer}</textarea></td>
					</tr>
				</table>
			</form>
		</div>
	
	<div class="sub-view-bottom">
		<a href="javascript:qna_answer()" class="btn-modify">답변저장</a>&nbsp;&nbsp;
		<a href="SYKK?command=board_qna&page=${page}" class="btn-list">목록</a>&nbsp;&nbsp;
	</div>
		
</div>

<script>
	function qna_answer(){
		if(my.answer.value==""){
			alert('답글을 입력하세요');
			my.answer.focus();
			return;
		}
		my.action="SYKK?command=board_qna_reply_pro";
		my.submit();
	}
</script>


<%@ include file="../footer.jsp"%>















