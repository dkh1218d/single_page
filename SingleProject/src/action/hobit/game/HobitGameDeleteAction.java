package action.hobit.game;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.hobit.game.gameDAO;

public class HobitGameDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		gameDAO dao = gameDAO.getinstance();
		String idx = request.getParameter("idx");
		String id_no = (String)request.getSession().getAttribute("id_no");
		try {
			ServletContext context = request.getSession().getServletContext();
			String path = context.getRealPath("/hobit/game");
			String photo = "";
			if(dao.game_Search_Photo(idx)!=null) {
				photo = dao.game_Search_Photo(idx);
			}else 
				photo = null;
			
			int row = dao.game_Delete(idx, id_no);
			if(row>0) { 
				if(photo!=null) {
					File file = new File(path, photo);
					file.delete();
				}
			}
			response.getWriter().write(row+"");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
