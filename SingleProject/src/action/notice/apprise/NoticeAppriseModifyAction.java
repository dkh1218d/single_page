package action.notice.apprise;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.notice.apprise.appriseDAO;
import model.notice.apprise.appriseVO;

public class NoticeAppriseModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		appriseDAO dao = appriseDAO.getinstance();
		String idx = request.getParameter("idx");
		appriseVO vo = dao.apprise_View(idx);
		request.setAttribute("vo", vo);
		request.setAttribute("page", request.getParameter("page"));
		request.getRequestDispatcher("notice/apprise_modify.jsp").forward(request, response);
	}

}
