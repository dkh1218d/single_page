package action.hobit.travel;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.hobit.travel.travelDAO;
import model.hobit.travel.travelVO;

public class HobitTravelModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		travelDAO dao = travelDAO.getinstance();
		String idx = request.getParameter("idx");
		travelVO vo = dao.travel_View(idx);
		request.setAttribute("vo", vo);
		request.setAttribute("page", request.getParameter("page"));
		request.getRequestDispatcher("hobit/hobit_travel_modify.jsp").forward(request, response);
	}

}
