<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<HTML>
<HEAD>
<TITLE>이메일을 통해 인증합니다.</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<STYLE TYPE="text/css">
<!--
body { font-family: 돋움, Verdana; font-size: 9pt}
td   { font-family: 돋움, Verdana; font-size: 9pt; text-decoration: none; color: #000000} 
--->
</STYLE>
<script>
	function win_close(){
		self.close();
	}

</script>
</HEAD>
<BODY bgcolor="#FFFFFF">
<TABLE CELLPADDING=0 CELLSPACING=0 BORDER=0 WIDTH=330>
  <TR BGCOLOR=#7AAAD5>
    <td align=left><img src="images/u_b02.gif"></td>
    <td align=center><FONT COLOR="#FFFFFF"><b>이메일 인증</FONT></td>
    <td align=right><img src="images/u_b03.gif"></td>
  </tr>
</table>



<c:if test="${sessionScope.tc==true}">
<script> opener.email_ch=true;</script>
<form name="e_check" method="post" >
<TABLE CELLPADDING=0 CELLSPACING=0 BORDER=0 WIDTH=330>
<TR BGCOLOR=#948DCF>
  <TD>
    <TABLE CELLPADDING=4 CELLSPACING=1 BORDER=0 WIDTH=330>
  	  <TR BGCOLOR="#FFFFFF">
        <TD ALIGN="center">
         <br><FONT FACE="굴림"><B>인증 성공</B></FONT><br>
      	   <input type=image src="images/u_bt08.gif" border=0 vspace=0 onclick="win_close()">
        </TD>
      </TR>
    </TABLE>
 </TD>
</TR>
</TABLE>
</form>
</c:if>

<c:if test="${sessionScope.tc==false}">
<form name="e_receive" method="post">
<TABLE CELLPADDING=0 CELLSPACING=0 BORDER=0 WIDTH=330>
<TR BGCOLOR=#948DCF>
  <TD>
    <TABLE CELLPADDING=4 CELLSPACING=1 BORDER=0 WIDTH=330>
  	  <TR BGCOLOR="#FFFFFF">
        <TD ALIGN="center">
         <br><font face="굴림"><b>인증 실패</b></font>
      	   <input type=image src="images/u_bt08.gif" border=0 vspace=0 onclick="history.back()">
        </TD>
      </TR>
    </TABLE>
 </TD>
</TR>
</TABLE>
</form>
</c:if>




<TABLE CELLPADDING=0 CELLSPACING=0 BORDER=0 WIDTH=330>
  <TR BGCOLOR=#7AAAD5>
    <td align=left><img src="images/u_b04.gif"></td>
    <td align=right><img src="images/u_b05.gif"></td>
  </tr>
  <tr>
    <td colspan=3 align=center>
      <a href="#" onclick="win_close()"><img src="images/u_bt13.gif" border=0 vspace=5></a>
    </td>
  </tr>
</table>
</BODY>
</HTML>