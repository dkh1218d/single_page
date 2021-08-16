package action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;


public class MemberEmailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		String email = request.getParameter("email");
		request.setAttribute("email", email);
		request.getRequestDispatcher("member/emailcheck.jsp").forward(request, response);
	}

}
