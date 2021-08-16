<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../header.jsp"%>


<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-view-title">${vo.subject}<c:if test="${reply_cnt>0}">&nbsp;<font size="4" color="#FE642E">[${reply_cnt}]</font></c:if></h2>
		<p class="sub-view-wd">${vo.name} | ${vo.regdate.substring(0,10)}</p>
	</div>
	<div class="sub-view-contnet">
		<p>${vo.contents.replaceAll("\\n","<br>")}</p>
	</div>
	
		<table><tr><td></td></tr></table><br>
		
		<c:if test="${!empty list}">
		<c:forEach var="comment" items="${list}">
		<br>
		<div class="qareply" style="padding-bottom:30px;">
			<table>
				<tr>
					<td width="15%">
						<span style="display:none;">${comment.idx}</span>
						<c:if test="${comment.depth>0}"><img src="images/comment_1.jpg" class=comment_re></c:if>
						<B>${comment.name}</B>
					</td>
					<td width="50%"><c:if test="${comment.id_no!=0}"><c:if test="${comment.depth>0}"><font size=2 color=gray>${comment.refer_id}</font><br></c:if></c:if>
					<p><c:if test="${comment.id_no==0}"><font face="MS" color="black"><B></c:if>${comment.reply.replaceAll("\\n","<br>")}<c:if test="${comment.id_no==0}"></B></font></c:if>
						
						<c:if test="${comment.id_no!=0}">
						<span class="re_write">
						<c:if test="${!empty sessionScope.id_no}">&nbsp;
						<a href="javascript:displ_(${comment.idx})">
						<font size=2 color=#FE2E64>[답글]</font></a>
						</c:if></span></c:if>
						
					</p></td>
					<td class="reg" width="15%"><p>${comment.regdate.split(" ")[0]}<br>
					${comment.regdate.split(" ")[1].substring(0,5)}<br>
					<c:if test="${comment.id_no!=0}"><c:if test="${sessionScope.nick_name.equals(comment.name)}">
					<a href="javascript:reply_del(${comment.idx})"><font size=2 color=#FE2E64>[삭제]</font></a>
					</c:if></c:if></p></td>
				</tr>
			</table>
			<div id="${comment.idx}" style="display:none">
				<form name="${comment.idx}" method="post">
				<input type="hidden" name="parent_no" value="${vo.idx}">
				<input type="hidden" name="parent_re" value="${comment.parent_re}">
				<input type="hidden" name="refer_id" value="${comment.name}">
				<input type="hidden" name="depth" value="${comment.depth}">
				<input type="hidden" name="id_no" <c:if test="${!empty sessionScope.id_no}">value="${sessionScope.id_no}"</c:if>>
				<table>
					<tr>
						<td width="250px" height="80px"><img src="images/comment_1.jpg" class=comment_re><B>${sessionScope.nick_name}</B>
						<input type="hidden" name="name" value="${sessionScope.nick_name}" id="nam"></td>
						<td width="900px" height="80px">
						<input type="text" name="reply" value="" placeholder="댓글을 작성하세요" style="width:100%; height:40px;" maxlength="650"></td>
						<td width="250px" height="80px" class="reg"><a href="javascript:re_send(${comment.idx})" class="btn-modify">등록</a></td>
					</tr>
				</table>
				</form>
			</div>
		</div>
		
			<table><tr><td></td></tr></table>
		</c:forEach>
		<br>
		</c:if>
			
	<script>
		$(".re_write").click(function(){
			var table = $(this).parent().parent().parent();
			console.log(table.text());
			var re_idx = table.children(":eq(0)").children(":eq(0)").html();
				for(var x=0; x<document.getElementsByName(re_idx).length; x++){
				var form = document.getElementsByName(re_idx).item(x);
				form.reply.value="";
				form.parent_re.value=par_re;
				form.refer_id.value=ref_id;
				form.depth.value=depth;
				form.reply.focus();
			}
		});
		
		function re_send(idx){
			var re_reply_ajax = $("form[name="+idx+"]").serialize();
			
			$.ajax({
		        url: "SYKK?command=board_free_recomment",
		        type:"post",
		        data: re_reply_ajax, 
		        success: function(result){
		        	if (result > 0) {
		                alert("댓글이 입력 되었습니다");
		                window.location.reload()
		             } else
		            	 alert("입력 실패되셨습니다");
		        }
			})
		}
		
		function displ_(idx){
			dis = document.getElementById(idx);
			if(dis.style.display=="none")
				dis.style.display="inline";
			else
				dis.style.display="none";
		}
		
		function reply_del(idx){
			if(confirm("한번 삭제한 댓글은 되돌릴 수 없습니다.\n정말 삭제하시겠습니까") == true){
				var re_del = "idx="+idx;
				$.ajax({
			        url: "SYKK?command=board_free_reply_delete",
			        type:"get",
			        data: re_del, 
			        success: function(result){
			        	if (result > 0) {
			                alert("삭제 되었습니다");
			                window.location.reload()
			             } else
			            	 alert("삭제에 실패되셨습니다");
			        }
				})
			}
			else 
				return;
		}
	</script>
			
		
				
		<div class="qareply" style="padding-bottom:30px;">
			<form name="my" method="post">
				<input type="hidden" name="idx" value="${vo.idx}">
				<input type="hidden" name="id_no" <c:if test="${!empty sessionScope.id_no}">value="${sessionScope.id_no}"</c:if>>
				<table>
					<tr>
						<td width="15%"><B><c:if test="${!empty sessionScope.id_no}">${sessionScope.nick_name}
						<input type="hidden" name="name" value="${sessionScope.nick_name}">
						</c:if>
						<c:if test="${empty sessionScope.id_no}"><font color=red id="writer">로그인이 필요합니다</font></c:if>
						</B></td>
						<td width="55%"><textarea name="reply" id="comment" placeholder="댓글을 남겨 주세요" style="width:100%; height:80px;" maxlength="650"></textarea></td>
						<td width="15%" class="reg">&nbsp;&nbsp;<a href="javascript:reply()" class="btn-modify">등록</a></td>
					</tr>
				</table>
			</form>
		</div>
		
	<div class="sub-view-bottom">
		<c:if test="${!empty sessionScope.id_no && sessionScope.id_no.equals(vo.id_no)}">
		<a href="SYKK?command=board_free_modify&idx=${vo.idx}&page=${page}" class="btn-modify">수정</a>&nbsp;&nbsp;
		</c:if>
		<c:if test="${!empty sessionScope.id_no && sessionScope.id_no.equals(vo.id_no)}">
		<a href="javascript:avent()" class="btn-delete">삭제</a>&nbsp;&nbsp;
		</c:if>
		<a href="SYKK?command=board_free&page=${page}" class="btn-list">목록</a>&nbsp;&nbsp;
	</div>
		
</div>
<script>
	function avent(){
		if(confirm("삭제하시겠습니까") == true) {
			location.href="SYKK?command=board_free_delete&idx=${vo.idx}";
		}else {
			return;
		}
	}
	
	function reply(){
		if(${empty sessionScope.id_no}){
			alert('로그인 후 이용하실 수 있습니다.');
			return;
		}
		if(my.reply.value==""){
			alert('내용을 작성하여 주세요');
			return;
		}
		
		var reply_ajax = $("form[name=my]").serialize();
		$.ajax({
	        url: "SYKK?command=board_free_reply",
	        type:"post",
	        data: reply_ajax, 
	        success: function(result){
	        	if (result > 0) {
	                alert("댓글이 입력 되었습니다");
	                window.location.reload();
	             } else
	            	 alert("입력 실패되셨습니다");
	        }
		})
	}
</script>


<%@ include file="../footer.jsp"%>

