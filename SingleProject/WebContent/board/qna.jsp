<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../header.jsp"%>

<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">QnA게시판</h2>
		<div class="sub-search">
			<form name="my" method="post" action="SYKK?command=board_qna" onsubmit="return check()">
				<select name="search" class="sel">
					<option value="subject" <c:if test="${search.equals('subject')}">selected</c:if>>제목</option>
					<option value="contents" <c:if test="${search.equals('contents')}">selected</c:if>>내용</option>
					<option value="name" <c:if test="${search.equals('name')}">selected</c:if>>작성자</option>
				</select>
				<input type="text" name="key" class="text" value="${key}">
				<input type="submit" value="검색" class="btn">
			</form>
		</div>
	</div>
	
	<div class="content-body">
		<table class="qatable">
			<caption class="readonly">질문답변</caption>
			<colgroup>
				<col width="8%">
				<col width="10%">
				<col width="36%">
				<col width="10%">
				<col width="15%">
				<col width="11%">
				<col width="10%">
			</colgroup>
			<tbody>
				<tr>
					<th>번호</th>
					<th>질문유형</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>답변상태</th>
					<th>날자</th>
					<th>조회수</th>
				</tr>
			<c:forEach var="QnA" items="${list}">
				<tr>
					<td>${listcnt}</td>
					<td>${QnA.questions}</td>
					<td class="tleft"><a href="SYKK?command=board_qna_view&idx=${QnA.idx}&page=${page}">${QnA.subject}</a></td>
					<td>${QnA.name}</td>
					<td>
						<c:if test="${QnA.answer==null}"><span class="red">답변대기</span></c:if>
						<c:if test="${QnA.answer!=null}"><span class="gray">답변완료</span></c:if>
					</td>
					<td>${QnA.regdate.substring(0,10)}</td>
					<td>${QnA.readcnt}</td>
				</tr>
				<c:set value="${listcnt-1}" var="listcnt" />
			</c:forEach>
			</tbody>
		</table>
	</div>
		
		
		<div class="paging">
			<ul>
				<li>${pageSkip}</li>
			</ul>
			<c:if test="${!empty sessionScope.userid}"><a href="SYKK?command=board_qna_write" class="btn-write">글쓰기</a></c:if>
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















