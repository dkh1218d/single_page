package action.board.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.board.qna.qnaDAO;
import model.board.qna.qnaVO;

public class BoardQnAWriteProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		qnaDAO dao = qnaDAO.getinstance();
		qnaVO vo = new qnaVO();
		
		vo.setName(request.getParameter("name"));
		vo.setSubject(request.getParameter("subject"));
		vo.setContents(request.getParameter("contents"));
		vo.setId_no((String)request.getSession().getAttribute("id_no"));
		vo.setQuestions(request.getParameter("questions"));
		int row = dao.qna_Insert(vo);
				
		response.setContentType("text/html; charset=UTF-8");
		if(row>0) {
			response.getWriter().print("<script>alert('등록되었습니다'); location.href='SYKK?command=board_qna'; </script>");
		}else {
			response.getWriter().print("<script>alert('등록 실패'); history.back();</script>");
		}
		
	}

}
