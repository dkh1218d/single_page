<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../header.jsp"%>
<script>
	var email_ch = false;
	var seq_ch = false;
	
	 function check(re, what, message) {
	       if(re.test(what.value)) {
	           return true;
	       }
	       alert(message);
	       what.value = "";
	       what.focus();
	       return false;
	   }
	
	function formcheck() {
		var re = /^[a-zA-Z0-9]{4,16}$/
		var pw = my.passwd;
		
		if(pw.value=="") {
			alert("아이디입력하세요");
			my.userid.focus();
			return false;
		}
		if(pw.value=="") {
			alert("패스워드입력하세요");
			my.passwd.focus();
			return false;
		}
		if(!check(re,pw,"패스워드는 4~12자의 영문 대소문자와 숫자로만 입력하세요")) {
			return false;
		}
		if(pw.value != my.repasswd.value) {
			alert("패스워드가 일치하지 않습니다");
			my.passwd.focus();
			return false;
		}
		
		if(my.name.value=="") {
			alert("이름입력하세요");
			my.name.focus();
			return false;
		}
		if(seq_ch==false){
			alert("주민등록번호를 확인하세요");
			return false;
		}
		if(email_ch==false){
			alert("이메일 인증이 필요합니다");
			return false;
		}
		if(my.nick_name.value==""){
			my.nick_name.value=my.userid.value;
		}
		return true;
	}
	

	function id_check(){
		window.open("SYKK?command=member_id_overlap","id_overlap","width=350 height=250");
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
	
	function SocialSecurityNumber(){
		if($('#seq1').val()=="") {
			alert("주민등록번호 앞자리를 입력하세요");
			return;
		}
		var regexp = /^[0-9]*$/
		v = $("#seq1").val();
		if( !regexp.test(v) ) {
			alert("올바르지 않은 주민번호 형식입니다");
			$("#seq1").val()=="";
			$("#seq2").val()=="";
			return;
		}
		if($('#seq1').val().length!=6){
			alert("올바르지 않은 주민번호 형식입니다");
			$("#seq1").val()=="";
			$("#seq2").val()=="";
			return;
		}
		
		if(Number($('#seq1').val().substring(2,4))>12 || Number($('#seq1').val().substring(2,4))<1) {
			alert("올바르지 않은 주민번호 형식입니다");
			return;
		}
		if(Number($('#seq1').val().substring(4,6))>31 || Number($('#seq1').val().substring(4,6))<1) {
			alert("올바르지 않은 주민번호 형식입니다2");
			return;
		}
		
		if($('#seq2').val()=="") {
			alert("주민등록번호 뒷자리를 입력하세요");
			return;
		}
		v = $("#seq2").val();
		if( !regexp.test(v) ) {
			alert("올바르지 않은 주민번호 형식입니다");
			$("#seq1").val()=="";
			$("#seq2").val()=="";
			return;
		}
		if($('#seq2').val().length!=7){
			alert("올바르지 않은 주민번호 형식입니다");
			$("#seq1").val()=="";
			$("#seq2").val()=="";
			return;
		}
		
		var number = $('#seq1').val() + "-" + $('#seq2').val();
		$.ajax({
			type : 'POST', 
			url : 'SYKK?command=member_securitynumber',
			data : {securitynumber : number},
			success : function(result) {
				if(result>0){
					alert("가입된 아이디가 존재합니다");
					seq_ch=false;
				}else{
					alert("확인되었습니다");
					seq_ch=true;
				}
			}
		})
	}
</script>

<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">회원가입</h2>
	</div>
	
	<div class="write-form">
		<table summery="회원가입 글쓰기 테이블 입니다">
			<caption class="readonly">회원가입 입력폼</caption>			
			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tbody>
			<form name="my" method="post" action="SYKK" onsubmit="return formcheck();">
				<input type="hidden" name="command" value="member_signup_pro">
				<fieldset>
					<legend class="readonly">입력폼</legend>
					<tr>
						<th scope="row">
							<label for="id">아이디</label>
						</th>
						<td>
							<input type="text" name="userid" id="id" class="minput" readonly placeholder="Userid"> 
							<a href="javascript:id_check()" class="btn-write">중복확인</a>
						</td>
					</tr>
					<tr>
						<th scope="row"><lavel for="pass1">패스워드</lavel></th>
						<td><input type="password" name="passwd" id="pass1" maxlength=12 class="minput" placeholder="Password"></td>
					</tr>
					<tr>
						<th scope="row">패스워드 확인</th>
						<td><input type="password" name="repasswd" id="pass2" maxlength=12 class="minput" placeholder="Password"></td>
					</tr>
					<tr>
						<th scope="row">이름</th>
						<td><input type="text" name="name" maxlength=5 class="minput" placeholder="Name"></td>
					</tr>
					<tr>
						<th scope="row">주민등록번호</th>
						<td>
							<input type="text" name="SecurityNumber1" class="email" maxlength=6 id="seq1" placeholder="SecurityNumber"> -
							<input type="password" name="SecurityNumber2"  class="email" maxlength=7 id="seq2" placeholder="SecurityNumber">
							<a href="javascript:SocialSecurityNumber()" class="btn-write">확인</a> 
						</td>
					</tr>
					<tr>
						<th scope="row">별명</th>
						<td><input type="text" name="nick_name" maxlength=10 class="minput" placeholder="Nick Name"></td>
					</tr>
					<tr>
						<th scope="row">이메일</th>
						<td>
							<input type="text" name="email1" class="email" placeholder="Email"> @
							<input type="text" name="email2"  class="email" placeholder="Email"> 
							<a href="javascript:authentication()" class="btn-write">인증하기</a>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="전송" class="btn-write">
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

