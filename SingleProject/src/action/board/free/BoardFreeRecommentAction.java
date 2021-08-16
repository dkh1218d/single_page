package action.board.free;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.board.free.freeDAO;
import model.board.free.freeVO;

public class BoardFreeRecommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		freeVO vo = new freeVO();
		String idx = request.getParameter("parent_no");
		
		String reply = request.getParameter("reply");
		
		reply = URLDecoder.decode(reply,"UTF-8");
		vo.setReply(reply);
		vo.setName(request.getParameter("name"));
		vo.setId_no(request.getParameter("id_no"));
		vo.setParent_no(idx);
		vo.setRefer_id(request.getParameter("refer_id"));
		int depth = Integer.parseInt(request.getParameter("depth"));
		vo.setParent_re(request.getParameter("parent_re"));
		freeDAO dao = freeDAO.getinstance();
		int row = dao.free_ReReply(vo, depth);
		dao.free_reply_cnt_up(vo.getParent_re());

		response.getWriter().write(row+ "");

	}

}
