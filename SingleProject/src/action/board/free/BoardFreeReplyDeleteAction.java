package action.board.free;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.board.free.freeDAO;
import model.board.free.freeVO;

public class BoardFreeReplyDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		freeVO vo = new freeVO();
		freeDAO dao = freeDAO.getinstance();
		int row = 0;
		vo = dao.reply_Search(request.getParameter("idx"));
		if(vo.getReply_cnt()>0) {
			row = dao.free_parent_Delete(request.getParameter("idx"), (String)request.getSession().getAttribute("id_no"));
		}else
			row = dao.free_Delete(request.getParameter("idx"), (String)request.getSession().getAttribute("id_no"));
		
		response.getWriter().write(row+ "");
	}

}
