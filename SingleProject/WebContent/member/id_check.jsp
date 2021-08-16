<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<HTML>
<HEAD>
<TITLE>사용자의 아이디를 체크합니다.</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<STYLE TYPE="text/css">
<!--
body { font-family: 돋움, Verdana; font-size: 9pt}
td   { font-family: 돋움, Verdana; font-size: 9pt; text-decoration: none; color: #000000} 
--->
</STYLE>
<script>
	function id_overcheck(){
		var userid = id_overlap.userid.value;
		if(userid==""){
			alert("아이디를 입력하세요");
			id_overlap.userid.focus();
			return false;
		}
		if(userid.length<5 || userid.length>16){
			alert("형식에 맞지 않는 아이디");
			id_overlap.userid.focus();
			return false;
		}
       for (var i = 0; i < userid.length; i++) {
            ch = userid.charAt(i)
            if (!(ch >= '0' && ch <= '9') && !(ch >= 'a' && ch <= 'z')&&!(ch >= 'A' && ch <= 'Z')) {
                alert("아이디는 영문 대소문자, 숫자만 입력가능합니다.")
                id_overlap.userid.focus();
                return false;
            }
        }
		return true;
	}
	
	function win_close(){
		opener.my.userid.value="${userid}";
		self.close();
	}
	
	function null_close(){
		self.close();
	}
</script>
</HEAD>
<BODY bgcolor="#FFFFFF">
<TABLE CELLPADDING=0 CELLSPACING=0 BORDER=0 WIDTH=330>
  <TR BGCOLOR=#7AAAD5>
    <td align=left><img src="images/u_b02.gif"></td>
    <td align=center><FONT COLOR="#FFFFFF"><b>아이디 중복 체크 </FONT></td>
    <td align=right><img src="images/u_b03.gif"></td>
  </tr>
</table>

<TABLE CELLPADDING=0 CELLSPACING=0 BORDER=0 WIDTH=330>
<TR BGCOLOR=#948DCF>
  <TD>
    <TABLE CELLPADDING=4 CELLSPACING=1 BORDER=0 WIDTH=330>
  	  <TR BGCOLOR="#FFFFFF">
        <TD ALIGN="center">
			<form name="id_overlap" method="post" action="SYKK?command=member_id_overlap_pro" onsubmit="return id_overcheck()">
				5자에서 16자 사이의 알파벳, 숫자로<br>
				구성된 아이디를 입력해 주세요<br>
				<INPUT NAME=userid type=text size=16 maxlength=16>
				<input type=image src="images/u_bt08.gif" border=0 vspace=0>
			</form>
		<c:if test="${row==1}">
			<BR><FONT COLOR="#483cae"><b>${userid}</b></FONT>는 이미 사용 중인 아이디입니다<br><br>
		</c:if>
		<c:if test="${row==0}">
			<br><FONT FACE="굴림"><B>사용 가능합니다.</B></FONT><br>
			<BR><FONT COLOR="#483cae"><b>${userid}</b></FONT>는 사용할 수 있는 아이디입니다.
		</c:if>
        </TD>
      </TR>
    </TABLE>
 </TD>
</TR>
</TABLE>

<TABLE CELLPADDING=0 CELLSPACING=0 BORDER=0 WIDTH=330>
  <TR BGCOLOR=#7AAAD5>
    <td align=left><img src="images/u_b04.gif"></td>
    <td align=right><img src="images/u_b05.gif"></td>
  </tr>
  <tr>
  	<c:if test="${row==0}">
    <td colspan=3 align=center>
      <a href="#" onclick="win_close()"><img src="images/u_bt13.gif" border=0 vspace=5></a>
    </td>
    </c:if>
    
    <c:if test="${row!=0}">
    <td colspan=3 align=center>
      <a href="#" onclick="null_close()"><img src="images/u_bt13.gif" border=0 vspace=5></a>
    </td>
    </c:if>
  </tr>
</table>
</BODY>
</HTML>