package action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;

public class MemberPwResetTempAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tempKey = (String)request.getSession().getAttribute("tempKey");
		String inputtemp = request.getParameter("temp");
		String userid = (String)request.getSession().getAttribute("saveid");
		String Securitynumber = (String)request.getSession().getAttribute("Securitynumber");
		int row = 0;
		
		if(tempKey.equals(inputtemp)) {
			row = 1;
			request.getSession().invalidate();
			
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(1200);
			session.setAttribute("saveid", userid);
			session.setAttribute("Securitynumber", Securitynumber);
		}
		response.setContentType("text/html; charset=UTF-8");
		if(row>0)
			response.getWriter().print("<script> alert('비밀번호 변경페이지로 이동합니다'); location.href='SYKK?command=member_new_pw'; </script>");
		else
			response.getWriter().print("<script> alert('인증번호가 일치하지 않습니다'); history.back(); </script>");
	}

}
