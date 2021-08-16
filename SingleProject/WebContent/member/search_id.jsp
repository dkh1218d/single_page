<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/header.jsp" %>

<div class="contain">
	<div class="write-form" style="width:50%; margin:0 auto; border:1px solid #ccc; padding:20px;">
		<form name=my action="SYKK" method="post">
			<div class="write-form">
				<h2 align=center><strong>Search ID</strong></h2><br>
				<p align=center><strong>Please write your Social Security Number<br>
				in underline in the box below</strong></p><br>
				<div align=center>
					<span id="id_tag">
					<input type="text" name="SecurityNumber1" class="email" maxlength=6 id="seq1" placeholder="SecurityNumber"> -
					<input type="password" name="SecurityNumber2"  class="email" maxlength=7 id="seq2" placeholder="SecurityNumber">
					</span>
					<br><br><br><span id="pw_reset"><a href="javascript:seq_search()" class="seq-write">Confirm</a></span>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:history.back()" class="seq-write">Back</a><br>
				</div>
			</div> 
		</form>	
	</div>
</div>

<script>
	function seq_search(){
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
			url : 'SYKK?command=member_id_search_pro',
			data : {securitynumber : number},
			success : function(userid) {
				if(userid=="Empty Id"){
					alert("입력하신 정보와 일치하는 아이디가 존재하지 않습니다");
				}else {
					var id = "<br><br>기입하신 정보와 일치하는 아이디입니다 <br><br><strong>"+userid+"</strong>";
					$("#id_tag").html(id);
					var go_pw = "<a href='SYKK?command=member_pw_reset&userid=" + userid + "' class='seq-write'>Pw Reset</a>";
					$("#pw_reset").html(go_pw);
				}					
			}
		})
	}
</script>
<%@ include file="/footer.jsp" %>
