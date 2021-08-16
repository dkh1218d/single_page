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
		<h2 class="sub-title">자유게시판</h2>
	</div>
	
	<div class="write-form">
		<table summery="자유로운 글쓰기 테이블 입니다">
			<caption class="readonly">게시글 입력폼</caption>			
			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tbody>
			<form name="my" method="post" action="SYKK?command=board_free_write_pro" onsubmit="return formcheck();">
					<tr>
						<th>제목</th>
						<td><input type="text" name="subject" placeholder="제목입력하세요" maxlength="30"></td>
					</tr>
					<tr>
						<th>내용</th>
							<td id="contents_akscmxw">
			                    <div class="szekcio1" data-step="1" data-intro="" data-position='right'>
			                        <textarea id="elm1" name="contents"></textarea>
			                    </div>
			                </td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><input type="text" name="name" value="${sessionScope.nick_name}" readonly></td>
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
		location.href="SYKK?command=board_free";
	}
	
	function formcheck() {
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















