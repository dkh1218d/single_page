package action.gallery.f;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.gallery.f.fDAO;
import model.gallery.f.fVO;

public class GalleryFModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		fDAO dao = fDAO.getinstance();
		String idx = request.getParameter("idx");
		fVO vo = dao.f_View(idx);
		request.setAttribute("vo", vo);
		request.setAttribute("page", request.getParameter("page"));
		request.getRequestDispatcher("gallery/gallery_f_modify.jsp").forward(request, response);
	}

}
