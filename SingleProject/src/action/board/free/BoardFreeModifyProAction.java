package action.board.free;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.board.free.freeDAO;
import model.board.free.freeVO;

public class BoardFreeModifyProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		freeDAO dao = freeDAO.getinstance();
		freeVO vo = new freeVO();
		
		String idx = request.getParameter("idx");
		String page = request.getParameter("page");
		String id_no = (String)request.getSession().getAttribute("id_no");
		
		vo.setIdx(idx);
		vo.setId_no(id_no);
		vo.setName(request.getParameter("name"));
		vo.setContents(request.getParameter("contents"));
		vo.setSubject(request.getParameter("subject"));
		
		int row = dao.free_Modify(vo);
		
		response.setContentType("text/html; charset=UTF-8");
		if(row>0) {
			response.getWriter().print("<script>alert('수정되었습니다'); location.href='SYKK?command=board_free_view&idx="+idx+"&page="+page+"'; </script>");
		}else {
			response.getWriter().print("<script>alert('수정 실패'); history.back(); </script>");
		}
	}
}
