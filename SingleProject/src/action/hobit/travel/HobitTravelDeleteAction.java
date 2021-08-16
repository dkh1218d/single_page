package action.hobit.travel;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.hobit.travel.travelDAO;

public class HobitTravelDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		travelDAO dao = travelDAO.getinstance();
		String idx = request.getParameter("idx");
		String id_no = (String)request.getSession().getAttribute("id_no");
		try {
			ServletContext context = request.getSession().getServletContext();
			String path = context.getRealPath("/hobit/travel");
			String photo = "";
			if(dao.travel_Search_Photo(idx)!=null) {
				photo = dao.travel_Search_Photo(idx);
			}else 
				photo = null;
			
			int row = dao.travel_Delete(idx, id_no);
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
