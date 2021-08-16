package action.gallery.sa;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.gallery.sa.saDAO;
import model.gallery.sa.saVO;

public class GallerySaModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		saDAO dao = saDAO.getinstance();
		String idx = request.getParameter("idx");
		saVO vo = dao.sa_View(idx);
		request.setAttribute("vo", vo);
		request.setAttribute("page", request.getParameter("page"));
		request.getRequestDispatcher("gallery/gallery_sa_modify.jsp").forward(request, response);
	}

}
