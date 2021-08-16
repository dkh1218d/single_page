<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<link rel="stylesheet" href="/css/codemirror.css">
<link href="/css/jquery-ui.css" rel="stylesheet">
<script src="/js/jscolor.js"></script>
<script src="/js/tinymce.gzip.js"></script>
<script src="/js/jquery-ui.js"></script>
<script src="/js/codemirror.js"></script>
<script src="/js/xml-fold.js"></script>
<script src="/js/matchtags.js"></script>
<script src="/js/active-line.js"></script>
<script src="/js/closetag.js"></script>
<script src="/js/xml.js"></script>
<link rel="stylesheet" type="text/css" href="/css/html-editor.css" />
<script src="/js/html-editor.js"></script>
<script src="/js/linketado.js"></script>


<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">QnA게시판</h2>
	</div>
	
	<div class="write-form">
		<table summery="질문답변 글쓰기 테이블 입니다">
			<caption class="readonly">QnA수정폼</caption>			
			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tbody>
			<form name="my" method="post" action="SYKK?command=board_qna_modify_pro&page=${page}" onsubmit="return formcheck();">
					<input type="hidden" name="idx" value="${vo.idx}">
					<tr>
						<th>질문유형</th>
						<td>
							<select name="questions" class="sel">
								<option value="none_select">선택하세요</option>
								<option value="직업" <c:if test="${vo.questions.equals('직업')}">selected</c:if>>작업</option>
								<option value="취업" <c:if test="${vo.questions.equals('취업')}">selected</c:if>>취업</option>
								<option value="취미" <c:if test="${vo.questions.equals('취미')}">selected</c:if>>취미</option>
								<option value="기타" <c:if test="${vo.questions.equals('기타')}">selected</c:if>>기타</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input type="text" name="subject" placeholder="제목입력하세요" maxlength="30" value="${vo.subject}"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td id="contents_akscmxw">
			                    <div class="szekcio1" data-step="1" data-intro="" data-position='right'>
			                        <textarea id="elm1" name="contents">${vo.contents}</textarea>
			                   </div>
			             </td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><input type="text" name="name"  value="${vo.name}" readonly></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="전송" class="btn-write">
							<input type="button" value="목록" class="btn-reset" onclick="go_list()">
						</td>
					</tr>
				</form>
			</tbody>
		</table>
	</div>
		
</div>

<script>
	window.onload = bodyOnload();
	
	function go_list(){
		location.href="SYKK?command=board_qna";
	}
	
	function formcheck() {
		if(my.questions.value=="none_select"){
			alert('질문 유형을 골라 주세요');
			return false;
		}
		if(my.subject.value=="") {
			alert("제목입력하세요");
			my.subject.focus();
			return false;
		}
		var contents = tinymce["get"]("elm1")["getContent"](); 
		if(contents.value=="") {
			alert("내용입력하세요");
			return;
		}
		
		return true;
	}
</script>

<%@ include file="../footer.jsp"%>















