<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../header.jsp"%>
<script>
	var email_ch = false;
	
	function formcheck() {
		if(my.passwd.value=="") {
			alert("패스워드입력하세요");
			my.passwd.focus();
			return false;
		}
		if(my.passwd.value != my.repasswd.value || my.passwd.value.length>12 || my.passwd.value.length<4) {
			alert("패스워드를 확인하세요");
			my.passwd.focus();
			return false;
		}
		var oldmail = document.getElementById("oldmail").value;
		var hivemail = my.email1.value +"@"+ my.email2.value;
		if(hivemail==oldmail){
			email_ch=true;
		}
		if(email_ch==false){
			alert("변경하신 이메일의 재 인증이 필요합니다");
			return false;
		}
		if(my.nick_name.value==""){
			my.nick_name.value=my.userid.value;
		}
		return true;
	}

	function authentication(){
		if(my.email1.value==""){
			alert("이메일을 입력하세요");
			my.email1.focus();
			return;
		}
		if(my.email2.value==""){
			alert("이메일을 입력하세요");
			my.email2.focus();
			return;
		}
		var hivemail = my.email1.value +"@"+ my.email2.value;
		window.open("SYKK?command=member_email&email="+hivemail,"이메일 인증","width=350 height=300");
	}
</script>

<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">회원정보 수정</h2>
	</div>
	
	<div class="write-form">
		<table summery="회원가입 글쓰기 테이블 입니다">
			<caption class="readonly">회원정보 수정폼</caption>			
			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tbody>
			<form name="my" method="post" action="SYKK" onsubmit="return formcheck();">
				<input type="hidden" name="command" value="member_modify_pro">
				<fieldset>
					<legend class="readonly">입력폼</legend>
					<tr>
						<th scope="row">
							<label for="id">아이디</label>
						</th>
						<td>
							<input type="text" name="userid" id="id" value="${sessionScope.userid}" class="minput" readonly> 
						</td>
					</tr>
					<tr>
						<th scope="row"><lavel for="pass1">새 패스워드</lavel></th>
						<td><input type="password" name="passwd" placeholder="새로운 비밀번호를 입력하세요" id="pass1" maxlength=12 class="minput"></td>
					</tr>
					<tr>
						<th scope="row">패스워드 확인</th>
						<td><input type="password" name="repasswd" maxlength=12 class="minput"></td>
					</tr>
					<tr>
						<th scope="row">이름</th>
						<td><input type="text" name="name" maxlength=5 class="minput" value="${sessionScope.name}"></td>
					</tr>
					<tr>
						<th scope="row">별명</th>
						<td><input type="text" name="nick_name" maxlength=10 class="minput" value="${sessionScope.nick_name}"></td>
					</tr>
					<tr>
						<th scope="row">이메일</th>
						<input type="hidden" id="oldmail" value="${sessionScope.email}">
						<td>
							<input type="text" name="email1" class="email" value="${sessionScope.email.split('@')[0]}"> @
							<input type="text" name="email2"  class="email" value="${sessionScope.email.split('@')[1]}"> 
							<a href="javascript:authentication()" class="btn-write">인증하기</a>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="수정" class="btn-write">
							<input type="button" value="뒤로가기"  class="btn-reset" onclick="javascript:location.href='SYKK?command=index'">
						</td>
					</tr>
					</fieldset>
				</form>
			</tbody>
		</table>
	</div>
		
</div>

<%@ include file="../footer.jsp"%>

