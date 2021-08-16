package action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import KISA.SHA256;
import action.Action;
import model.member.memberDAO;
import java.util.Base64;


public class MemberSeqAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		memberDAO dao = memberDAO.getinstance();
		SHA256 sha = new SHA256(request.getParameter("securitynumber").getBytes());
		String Securitynumber = Base64.getEncoder().encodeToString(sha.GetHashCode());
		int row = dao.SocialSecSurityNumber(Securitynumber);
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(row+"");;
	}

}
