package action.gallery.w;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.gallery.w.wDAO;
import model.gallery.w.wVO;

public class GalleryWModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		wDAO dao = wDAO.getinstance();
		String idx = request.getParameter("idx");
		wVO vo = dao.w_View(idx);
		request.setAttribute("vo", vo);
		request.setAttribute("page", request.getParameter("page"));
		request.getRequestDispatcher("gallery/gallery_w_modify.jsp").forward(request, response);
	}

}
