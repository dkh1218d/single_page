<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script>
	function check(){
		if(pho.photo.value==""){
			alert("이미지 파일을 첨부하세요");
			return false;
		}
		return true;
	}
</script>
<center>
<br><br>
소개 사진을 변경합니다
<br><br><br>
<form name="pho" method="post" action="SYKK?command=about_introduce_photo_pro" enctype="multipart/form-data" onsubmit="return check()">

<input type="file" name="photo">
<br><br>
<input type="submit" value="변경">&nbsp;&nbsp;&nbsp;
<input type="button" value="취소" onclick="self.close();">
</form>
</center>