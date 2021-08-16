package action.board.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.board.qna.qnaDAO;
import model.board.qna.qnaVO;

public class BoardQnAModifyProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		qnaDAO dao = qnaDAO.getinstance();
		qnaVO vo = new qnaVO();
		
		String idx = request.getParameter("idx");
		String page = request.getParameter("page");
		String id_no = (String)request.getSession().getAttribute("id_no");
		
		vo.setIdx(idx);
		vo.setId_no(id_no);
		vo.setQuestions(request.getParameter("questions"));
		vo.setName(request.getParameter("name"));
		vo.setContents(request.getParameter("contents"));
		vo.setSubject(request.getParameter("subject"));
		
		int row = dao.qna_Modify(vo);
		
		response.setContentType("text/html; charset=UTF-8");
		if(row>0) {
			response.getWriter().print("<script>alert('수정되었습니다'); location.href='SYKK?command=board_qna_view&idx="+idx+"&page="+page+"'; </script>");
		}else {
			response.getWriter().print("<script>alert('수정 실패'); history.back(); </script>");
		}
	}
}
