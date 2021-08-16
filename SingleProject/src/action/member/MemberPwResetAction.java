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

public class MemberPwResetAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = "";
		if(request.getParameter("userid")!=null) {
			userid = request.getParameter("userid");
		}else
			userid = null;

		request.setAttribute("userid", userid);
		request.getRequestDispatcher("member/reset_pw.jsp").forward(request, response);
	}

}
