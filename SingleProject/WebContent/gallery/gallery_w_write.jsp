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
		<h2 class="sub-title">개인 작품 사진첩</h2>
	</div>
	
	<div class="write-form">
		<table summery="갤러리 글쓰기 테이블 입니다">
			<caption class="readonly">갤러리 입력폼</caption>			
			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tbody>
			<form name="my" method="post" enctype="multipart/form-data">
					<tr>
						<th>제목</th>
						<td><input type="text" name="subject" maxlength="30"></td>
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
						<th>첨부</th>
						<td><input type="file" name="photo" accept="image/*" Id="w_file" multiple="multiple"></td>
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
	
	function go_list(){
		location.href="SYKK?command=gallery_w";
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
		if(my.photo.value==""){
			alert("파일 첨부하세요");
			return;
		}
		var fc = my.photo.value.split('.');
		if(fc[1].toUpperCase()=='JPG' || fc[1]=='JPEG' || fc[1]=='GIF' || fc[1]=='PNG'){
			my.action="SYKK?command=gallery_w_write_pro";
			my.submit();
		}else
			alert('올바르지 않은 파일양식입니다');
		return;
	}
</script>

<%@ include file="../footer.jsp"%>















