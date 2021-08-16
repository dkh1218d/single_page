<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../header.jsp"%>

<script>
	function win_pho(){
		window.open("SYKK?command=about_introduce_photo","사진","width=250 height=250");
	}
	function win_anno(){
		window.open("SYKK?command=about_introduce_announcement","소개 문구","width=250 height=250");
	}
	function win_edu(){
		window.open("SYKK?command=about_introduce_education","학력","width=250 height=250");
	}
	function win_job(){
		window.open("SYKK?command=about_introduce_job","직무능력","width=250 height=250");
	}
	
	function about_del(idx){
		if(confirm("정말 삭제하시겠습니까") == true){
			var ab_del = "&delcode="+idx;
			$.ajax({
		        url: "SYKK?command=about_introduce_del"+ab_del,
		        type:"get",
		        success: function(result){
		        	if (result > 0) {
		                alert("삭제 되었습니다");
		                window.location.reload()
		             } else
		            	 alert("삭제에 실패했습니다");
		        }
			})
		}
		else 
			return;
	}
	
	
</script>

<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">소개</h2>
	</div>

	<div class="titleimg">
		<c:forEach var="pho" items="${list}">
		<c:if test="${pho.photo!=null}">
			<img class="idphoto" src="about/photo/${pho.photo}" alt="손권희">
		</c:if></c:forEach>
		<c:if test="${!empty sessionScope.admin && sessionScope.admin.equals('T')}"><a class="img_mo" href="javascript:win_pho()">
		<font size=2>〈사진 변경〉</font></span></a></c:if>
	</div>
	<div class="content">
		
		<h1><c:forEach var="announce" items="${list}">
			<c:if test="${announce.announcement!=null}">${announce.announcement}</c:if>
			</c:forEach>
		<c:if test="${!empty sessionScope.admin && sessionScope.admin.equals('T')}">&nbsp;&nbsp;&nbsp;<a href="javascript:win_anno()">
		<span class="fa fa-plus plus"></span></a></c:if></h1><br>
		
		<h2>학력<c:if test="${!empty sessionScope.admin && sessionScope.admin.equals('T')}">&nbsp;&nbsp;&nbsp;
		<a href="javascript:win_edu()"><span class="fa fa-plus plus"></span></a></c:if></h2>
		<ul>
			<c:forEach var="edu" items="${list}">
				<c:if test="${edu.education!=null}"><li>${edu.education}<c:if test="${!empty sessionScope.admin && sessionScope.admin.equals('T')}">
				&nbsp;&nbsp;<a href="javascript:about_del(${edu.idx})">X</a></c:if></li></c:if>
			</c:forEach>
		</ul>
		
		<h2>직무능력<c:if test="${!empty sessionScope.admin && sessionScope.admin.equals('T')}">&nbsp;&nbsp;&nbsp;
		<a href="javascript:win_job()"><span class="fa fa-plus plus"></span></a></c:if></h2>
		<ul>
			<c:forEach var="job" items="${list}">
			<c:if test="${job.job_skill!=null}"><li>${job.job_skill}<c:if test="${!empty sessionScope.admin && sessionScope.admin.equals('T')}">
			&nbsp;&nbsp;<a href="javascript:about_del(${job.idx})">X</a></c:if></li></c:if>
			</c:forEach>
		</ul>

	</div>
</div>

<%@ include file="../footer.jsp"%>