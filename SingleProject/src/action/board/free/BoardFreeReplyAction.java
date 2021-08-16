package action.board.free;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.board.free.freeDAO;
import model.board.free.freeVO;

public class BoardFreeReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		freeVO vo = new freeVO();
		String idx = request.getParameter("idx");
		vo.setReply(request.getParameter("reply"));
		vo.setName(request.getParameter("name"));
		vo.setId_no(request.getParameter("id_no"));
		vo.setParent_no(idx);
		freeDAO dao = freeDAO.getinstance();
		
		int row = dao.free_Reply(vo);
		
		response.getWriter().write(row+ "");
	}

}
