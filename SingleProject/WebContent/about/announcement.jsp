<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script>
	function check(){
		if(announce.announcement.value==""){
			alert("문구를 입력하세요");
			return false;
		}
		return true;
	}
</script>
<center>
<br><br>
자기 소개 문구를 변경합니다
<br><br><br>
<form name="announce" method="post" action="SYKK" onsubmit="return check()">
<input type="hidden" name="command" value="about_introduce_announcement_pro">
<input type="text" name="announcement" maxlength="30" size=30>
<br><br>
<input type="submit" value="변경">&nbsp;&nbsp;&nbsp;
<input type="button" value="취소" onclick="self.close();">
</form>
</center>