package action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import KISA.SHA256;
import action.Action;
import model.member.memberDAO;
import model.member.memberVO;
import java.util.Base64;

public class MemberNewPasswdProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession()==null || !request.isRequestedSessionIdValid()) {
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print("<script>alert('올바르지 않은 접근입니다'); location.href='index.jsp'; </script>");
		}
		
		SHA256 sha = new SHA256(request.getParameter("passwd").getBytes());
		String passwd = Base64.getEncoder().encodeToString(sha.GetHashCode());
		memberDAO dao = memberDAO.getinstance();
		memberVO vo = new memberVO();
		vo.setSecuritynumber((String)request.getSession().getAttribute("Securitynumber"));
		vo.setUserid((String)request.getSession().getAttribute("saveid"));
		vo.setPasswd(passwd);
		
		System.out.println(passwd);
		System.out.println(vo.getSecuritynumber());
		System.out.println(vo.getUserid());
		int row = dao.member_Reset_Passwd(vo);
		response.setContentType("text/html; charset=UTF-8");
		if(row>0)
			response.getWriter().print("<script>alert('성공적으로 변경되었습니다'); location.href='index.jsp'; </script>");
		else
			response.getWriter().print("<script>alert('변경 실패'); history.back();</script>");
	}

}
