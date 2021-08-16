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

public class MemberLoginProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		memberDAO dao = memberDAO.getinstance();
		SHA256 sha = new SHA256(request.getParameter("passwd").getBytes());
		String passwd = Base64.getEncoder().encodeToString(sha.GetHashCode());
		
		int row = dao.member_Login(request.getParameter("userid"), passwd);
		memberVO vo = dao.member_Search(request.getParameter("userid"), passwd);
		
		response.setContentType("text/html; charset=UTF-8");
		if(row>0) {
			session.setMaxInactiveInterval(60*60); 
			session.setAttribute("userid", request.getParameter("userid"));
			session.setAttribute("passwd", passwd);
			session.setAttribute("email", vo.getEmail());
			session.setAttribute("nick_name", vo.getNick_name());
			session.setAttribute("id_no", vo.getIdx());
			session.setAttribute("name", vo.getName());
			session.setAttribute("admin", vo.getAdmin());
		}
		response.getWriter().write(row+"");
	}

}
