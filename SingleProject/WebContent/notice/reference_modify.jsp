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
		<h2 class="sub-title">첨부파일</h2>
	</div>
	
	<div class="write-form">
	<form name="my" method="post" enctype="multipart/form-data" id="form_id">
	<input type="hidden" value="${vo.idx}" name="idx">
		<table summery="갤러리 글쓰기 테이블 입니다">
			<caption class="readonly">첨부 파일 수정</caption>			
			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tbody>
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
						<td>
						<a href="javascript:void(0)" id="refer_add" class="gal-modify"><font size=2>추가</font></a>&nbsp;&nbsp;
						<div id="fileDiv">
						</div>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" value="수정" class="btn-write" onclick="fileCheck()">
							<input type="button" value="목록"  class="btn-reset" onclick="go_list()">
						</td>
					</tr>
			</tbody>
		</table>
		</form>
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
	
	var file_no = 1;
	$("#refer_add").click(function(){
		if(file_no<=5){
			var input = $("<p><br><input type='file' name='filename" + file_no + "'>&nbsp;&nbsp;<a href='#_' class='gal-delete' id='delete"+ file_no +"' onclick='fn_delete(event, $(this))'><font size=2>삭제</font></a><br></p>");
			$("#fileDiv").append(input);
			
			file_no=fn_index(file_no+1);
		}else
			alert("파일은 5개까지 첨부가 가능합니다");
       
	});
	
	function fn_delete(e, o){
	     e.preventDefault();
         fn_fileDelete(o);      
	}
	
	function fn_fileDelete(obj){
		obj.parent().remove();
		file_no = fn_index(1);
		alert(file_no);
    }
	
	function fn_index(start_index){
		for(var i=start_index; i<6; i++){
        	if(document.getElementsByName("filename"+i)[0] == null){
        		return i;
        	}
        }
		return;
	}
	
	function impCheck(){
		var imp = document.my.important.checked; 
		if(imp){
			document.my.important.value="T";
		}
	}
	
	function go_list(){
		location.href="SYKK?command=notice_reference";
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
		
		my.action="SYKK?command=notice_reference_modify_pro&page="+${page};
		my.submit();
	}
</script>

<%@ include file="../footer.jsp"%>















