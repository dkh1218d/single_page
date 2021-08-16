package action.board.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.board.qna.qnaDAO;
import model.board.qna.qnaVO;

public class BoardQnAReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		qnaDAO dao = qnaDAO.getinstance();
		qnaVO vo = dao.qna_View(request.getParameter("idx"));
		
		String idx = request.getParameter("idx");
		int page = 1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		request.setAttribute("vo", vo);
		request.setAttribute("page", page);
		request.getRequestDispatcher("board/qna_reply.jsp").forward(request, response);
	}

}
