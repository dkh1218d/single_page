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

public class MemberIdSearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("member/search_id.jsp").forward(request, response);
	}

}
