package action.notice.reference;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.notice.reference.referenceDAO;
import model.notice.reference.referenceVO;

public class NoticeReferenceModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		referenceDAO dao = referenceDAO.getinstance();
		String idx = request.getParameter("idx");
		referenceVO vo = dao.reference_View(idx);
		request.setAttribute("vo", vo);
		request.setAttribute("page", request.getParameter("page"));
		request.getRequestDispatcher("notice/reference_modify.jsp").forward(request, response);
	}

}
