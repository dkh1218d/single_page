package action.board.free;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.board.free.freeDAO;

public class BoardFreeDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		freeDAO dao = freeDAO.getinstance();
		
		int row = dao.free_Delete(request.getParameter("idx"),
				(String)request.getSession().getAttribute("id_no"));
	
		response.setContentType("text/html; charset=UTF-8");
		if(row>0) {
			dao.free_DeleteAndreply(request.getParameter("idx"));
			response.getWriter().print("<script>alert('게시글이 삭제되었습니다'); location.href='SYKK?command=board_free'; </script>");
		}else {
			response.getWriter().print("<script>alert('삭제실패'); history.back();</script>");
		}
	}

}
