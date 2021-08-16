package action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;


public class MemberTempAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tempKey = (String)request.getSession().getAttribute("tempKey");
		String temp = request.getParameter("temp");
		boolean tc = false;
		if(tempKey.equals(temp)) {
			tc = true;
			
		}
		request.getSession().setAttribute("tc", tc);
		request.getRequestDispatcher("member/tempcheck.jsp").forward(request, response);
	}

}
