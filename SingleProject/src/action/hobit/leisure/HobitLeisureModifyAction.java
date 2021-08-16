package action.hobit.leisure;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.hobit.leisure.leisureDAO;
import model.hobit.leisure.leisureVO;

public class HobitLeisureModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		leisureDAO dao = leisureDAO.getinstance();
		String idx = request.getParameter("idx");
		leisureVO vo = dao.leisure_View(idx);
		request.setAttribute("vo", vo);
		request.setAttribute("page", request.getParameter("page"));
		request.getRequestDispatcher("hobit/hobit_leisure_modify.jsp").forward(request, response);
	}

}
