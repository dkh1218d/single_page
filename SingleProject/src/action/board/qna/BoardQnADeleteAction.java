package action.board.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.board.qna.qnaDAO;

public class BoardQnADeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		qnaDAO dao = qnaDAO.getinstance();
		int row = dao.qna_Delete(request.getParameter("idx"),
				(String)request.getSession().getAttribute("id_no"));
	
		response.setContentType("text/html; charset=UTF-8");
		if(row>0) {
			response.getWriter().print("<script>alert('게시글이 삭제되었습니다'); location.href='SYKK?command=board_qna'; </script>");
		}else {
			response.getWriter().print("<script>alert('삭제실패'); history.back();</script>");
		}
	}

}
