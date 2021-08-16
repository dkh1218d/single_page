<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<h2 class="sub-title">공지 사항</h2>
	</div>
	
	<div class="write-form">
		<table summery="갤러리 글쓰기 테이블 입니다">
			<caption class="readonly">공지 사항 수정</caption>			
			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tbody>
			<form name="my" method="post" enctype="multipart/form-data">
			<input type="hidden" name="idx" value="${vo.idx}">
					<tr>
						<th>중요 공지</th>
						<td><input type="checkbox" id="icheck" name="important" value="" onclick="impCheck()"<c:if test="${vo.important=='T'}"> checked</c:if>><font size=2> &nbsp;중요</font></td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input type="text" name="subject" value="${vo.subject}" maxlength="30">
						</td>
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
						<td><input type="text" name="name" value="${vo.name}" readonly></td>
					</tr>
					<tr>
						<th>첨부</th>
						<td><input type="file" name="photo" accept="image/*" Id="f_file" multiple="multiple"></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" value="등록" class="btn-write" onclick="fileCheck()">
							<input type="button" value="목록"  class="btn-reset" onclick="go_list()">
						</td>
					</tr>
				</form>
			</tbody>
		</table>
	</div>
		
</div>

<script>
	window.onload = bodyOnload();
	
	$(document).ready(function() {
		var imp = $("input:checkbox[id='icheck']").is(":checked");
		if(imp){
			$('#icheck').val('T');
		}
	});
	
	function impCheck(){
		var imp = document.my.important.checked; 
		if(imp){
			document.my.important.value="T";
		}
	}
	
	function go_list(){
		location.href="SYKK?command=notice_apprise";
	}
	
	function fileCheck() {
		if(my.subject.value=="") {
			alert("제목입력하세요");
			my.title.focus();
			return;
		}
		var contents = tinymce["get"]("elm1")["getContent"](); 
		if(contents.value=="") {
			alert("내용입력하세요");
			return;
		}
		my.action="SYKK?command=notice_apprise_modify_pro&page="+${page};
		my.submit();
	}
</script>

<%@ include file="../footer.jsp"%>















