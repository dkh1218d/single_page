package action.about.introduce;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.about.introduce.introduceDAO;
import model.about.introduce.introduceVO;

public class AboutIntroduceDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		introduceDAO dao = introduceDAO.getinstance();
		int row = dao.About_del(request.getParameter("delcode"));
		
		response.getWriter().write(row+"");
	}

}
