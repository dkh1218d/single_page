package action.board.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.board.qna.qnaDAO;
import model.board.qna.qnaVO;

public class BoardQnAReplyProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idx = request.getParameter("idx");
		qnaDAO dao = qnaDAO.getinstance();
		qnaVO vo = new qnaVO();
		
		vo.setAnswer(request.getParameter("answer"));
		vo.setAnswer_name(request.getParameter("answer_name"));
		vo.setIdx(idx);

		int row = dao.qna_Reply(vo);

		response.setContentType("text/html; charset=UTF-8");
		if(row>0) {
			response.getWriter().print("<script>alert('등록되었습니다'); location.href='SYKK?command=board_qna_view&idx="+ idx +"'; </script>");
		}else {
			response.getWriter().print("<script>alert('등록 실패'); history.back();</script>");
		}
	}

}
