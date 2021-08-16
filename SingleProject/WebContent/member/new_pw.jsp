<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/header.jsp" %>

	<div class="contain" id="modal_login">
      <div class="write-form"
         style="width:30%; margin: 0 auto; border: 1px solid #ccc; padding: 20px;">
         <form name=newP method="post">
         	<input type="hidden" name="command" value="member_login_pro">
            <fieldset>
               <legend class="readonly">Reset Password</legend>
               <div id="log_form" style="width:100%; text-align:center;">
					<br>
					<input type="password" name="passwd"  id="pw" placeholder="Password">
					<input type="password" name="repasswd"  id="repw" placeholder="RePassword" onkeypress="if(event.keyCode == 13){modal_login(); return; }">
				</div>
               <div
                  style="width: 30%; padding: 15px; height: 80px; margin-top: 3px; text-align:center; margin:auto;">
                <input type="button" value="Confirm" alt="Confirm" onclick="new_passwd()" style="background: #FF4000; color: #fff; width: 100%; height: 85%; border: 0 none; cursor: pointer;">
               </div>
            </fieldset>
         </form>
      </div>
   </div>
<script>
	function check(re, what, message) {
	    if(re.test(what.value)) {
	        return true;
	    }
	    alert(message);
	    what.value = "";
	    what.focus();
	}
	
	function new_passwd(){
		var re = /^[a-zA-Z0-9]{4,16}$/
	    var newPw = newP.passwd;
		

		if(newPw.value==""){
			alert('비밀번호를 입력하세요');
			newPw.focus();
			return;
		}
		
		if(!check(re,newPw,"패스워드는 4~12자의 영문 대소문자와 숫자로만 입력하세요")) {
			return false;
		}
		
		if(newPw.value!=newP.repasswd.value){
			alert('비밀번호가 일치하지 않습니다');
			newPw.value="";
			newP.repasswd.value="";
			newPw.focus();
			return;
		}
		newP.action="SYKK?command=member_new_pw_pro";
		newP.submit();
	}
</script>
<%@ include file="/footer.jsp" %>