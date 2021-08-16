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
		<h2 class="sub-title">여행</h2>
	</div>
	
	<div class="write-form">
	<form name="hobit" method="post" enctype="multipart/form-data">
		<table>
			<caption class="readonly">여행 다이어리</caption>			
			<colgroup>
				<col class="w20">
				<col class="w80">
			</colgroup>
			<tbody>
					<tr>
						<th><label for="title">제목</label></th>
						<td><input type="text" name="subject" placeholder="제목입력하세요" maxlength="30" id="title" value="${vo.subject}"></td>
					</tr>
					<tr>
						<th><label for="content">내용</label></th>
						<td id="contents_akscmxw">
			                    <div class="szekcio1" data-step="1" data-intro="" data-position='right'>
			                        <textarea id="elm1" name="contents">${vo.contents}</textarea>
			                   </div>
			             </td>
					</tr>
					<tr>
						<th><label for="writer">작성자</label></th>
						<td><input type="text" name="name" id="writer" value="${vo.name}" readonly></td>
					</tr>
					<tr>
						<th><label for="appfile">사진</label></th>
						<td><input type="file" name="photo" accept="image/*" id="appfile"></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" value="수정" class="btn-write" onclick="fileCheck(${vo.idx}, ${page})">
							<input type="button" value="목록"  class="btn-reset" onclick="go_list(${page})">
						</td>
					</tr>
				
			</tbody>
		</table>
		</form>
	</div>
		
</div>

<script>
	window.onload = bodyOnload();
	
	function go_list(page){
		location.href="SYKK?command=hobit_travel&page="+page;
	}
	
	function fileCheck(idx, page) {
		if(hobit.subject.value=="") {
			alert("제목입력하세요");
			hobit.title.focus();
			return;
		}
		var contents = tinymce["get"]("elm1")["getContent"](); 
		if(contents.value=="") {
			alert("내용입력하세요");
			return;
		}
		if(hobit.photo.value=="") {
			hobit.action="SYKK?command=hobit_travel_modify_pro&idx="+idx+"&page="+page;
			hobit.submit();
			return;
		}
		var fc = hobit.photo.value.split('.');
		if(fc[1].toLowerCase()=='jpg' || fc[1]=='jpeg' || fc[1]=='gif' || fc[1]=='png'){
			hobit.action="SYKK?command=hobit_travel_modify_pro&idx="+idx+"&page="+page;
			hobit.submit();
		}else
			alert('올바르지 않은 파일양식입니다');
		return;
	}
	
</script>

<%@ include file="../footer.jsp"%>















