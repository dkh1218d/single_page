<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script>
	function check(){
		if(job.job_skill.value==""){
			alert("문구를 입력하세요");
			return false;
		}
		return true;
	}
</script>
<center>
<br><br>
직무 능력 문구를 추가합니다
<br><br><br>
<form name="job" method="post" action="SYKK" onsubmit="return check()">
<input type="hidden" name="command" value="about_introduce_job_pro">
<input type="text" name="job_skill" maxlength="30" size=30>
<br><br>
<input type="submit" value="등록">&nbsp;&nbsp;&nbsp;
<input type="button" value="취소" onclick="self.close();">
</form>
</center>