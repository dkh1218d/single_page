package action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.member.memberDAO;


public class MemberId_overlapProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		memberDAO dao = memberDAO.getinstance();
		String userid = request.getParameter("userid");
		int row = dao.id_overlap(userid);
		
		request.setAttribute("row", row);
		request.setAttribute("userid", userid);
		request.getRequestDispatcher("member/id_check.jsp").forward(request, response);
	}

}
