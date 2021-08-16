package action.hobit.game;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.hobit.game.gameDAO;
import model.hobit.game.gameVO;

public class HobitGameModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		gameDAO dao = gameDAO.getinstance();
		String idx = request.getParameter("idx");
		gameVO vo = dao.game_View(idx);
		request.setAttribute("vo", vo);
		request.setAttribute("page", request.getParameter("page"));
		request.getRequestDispatcher("hobit/hobit_game_modify.jsp").forward(request, response);
	}

}
