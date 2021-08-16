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

public class MemberSignUpProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		memberDAO dao = memberDAO.getinstance();
		memberVO vo = new memberVO();
		SHA256 sha = new SHA256(request.getParameter("passwd").getBytes());
		String passwd = Base64.getEncoder().encodeToString(sha.GetHashCode());
		String email = request.getParameter("email1") +"@"+ request.getParameter("email2");
		
		vo.setUserid(request.getParameter("userid"));
		vo.setPasswd(passwd);
		vo.setName(request.getParameter("name"));
		
		String Securitynumber = request.getParameter("SecurityNumber1") + "-" + request.getParameter("SecurityNumber2"); 
		SHA256 sha2 = new SHA256(Securitynumber.getBytes());
		Securitynumber = Base64.getEncoder().encodeToString(sha2.GetHashCode()); 
		vo.setSecuritynumber(Securitynumber);
		vo.setNick_name(request.getParameter("nick_name"));
		vo.setEmail(email);
		int row = dao.member_Insert(vo);

		response.setContentType("text/html; charset=UTF-8");
		if(row>0) {
			response.getWriter().print("<script>alert('가입 성공'); location.href='index.jsp';</script>");
		}else {
			response.getWriter().print("<script>alert('가입 실패'); history.back();</script>");
		}
	}

}
