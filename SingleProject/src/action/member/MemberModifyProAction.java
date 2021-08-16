package action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import KISA.SHA256;
import action.Action;
import model.member.memberDAO;
import model.member.memberVO;
import java.util.Base64;

public class MemberModifyProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		memberDAO dao = memberDAO.getinstance();
		memberVO vo = new memberVO();
		HttpSession oldsession = request.getSession();
		
		String passwd = (String)oldsession.getAttribute("passwd");
		if(request.getParameter("passwd")!=null) {
		SHA256 sha = new SHA256(request.getParameter("passwd").getBytes());
		passwd = Base64.getEncoder().encodeToString(sha.GetHashCode());
		}
		
		String email = request.getParameter("email1") +"@"+ request.getParameter("email2");
		vo.setAdmin((String)oldsession.getAttribute("admin"));
		vo.setUserid(request.getParameter("userid"));
		vo.setPasswd(passwd);
		vo.setName(request.getParameter("name"));
		vo.setNick_name(request.getParameter("nick_name"));
		vo.setEmail(email);
		vo.setIdx((String)oldsession.getAttribute("id_no"));
		
		int row = dao.member_Modify(vo);
		System.out.println(row);
		response.setContentType("text/html; charset=UTF-8");
		if(row>0) {
			oldsession.invalidate();
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(60*60);
			session.setAttribute("userid", vo.getUserid());
			session.setAttribute("passwd", vo.getPasswd());
			session.setAttribute("email", vo.getEmail());
			session.setAttribute("nick_name", vo.getNick_name());
			session.setAttribute("name", vo.getName());
			session.setAttribute("id_no", vo.getIdx());
			session.setAttribute("admin", vo.getAdmin());
			response.getWriter().print("<script>alert('수정 성공'); location.href='index.jsp';</script>");
		}else {
			response.getWriter().print("<script>alert('수정 실패'); history.back();</script>");
		}
	}

}
