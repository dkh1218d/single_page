package action.board.free;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.board.free.freeDAO;
import model.board.free.freeVO;

public class BoardFreeWriteProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		freeDAO dao = freeDAO.getinstance();
		freeVO vo = new freeVO();
		
		vo.setName(request.getParameter("name"));
		vo.setSubject(request.getParameter("subject"));
		vo.setContents(request.getParameter("contents"));
		vo.setId_no((String)request.getSession().getAttribute("id_no"));
		int row = dao.free_Insert(vo);
				
		response.setContentType("text/html; charset=UTF-8");
		if(row>0) {
			response.getWriter().print("<script>alert('등록되었습니다'); location.href='SYKK?command=board_free'; </script>");
		}else {
			response.getWriter().print("<script>alert('등록 실패'); history.back();</script>");
		}
		
	}

}
