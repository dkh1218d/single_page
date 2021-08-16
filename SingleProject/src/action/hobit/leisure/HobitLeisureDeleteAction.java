package action.hobit.leisure;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.hobit.leisure.leisureDAO;

public class HobitLeisureDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		leisureDAO dao = leisureDAO.getinstance();
		String idx = request.getParameter("idx");
		String id_no = (String)request.getSession().getAttribute("id_no");
		try {
			ServletContext context = request.getSession().getServletContext();
			String path = context.getRealPath("/hobit/leisure");
			String photo = "";
			if(dao.leisure_Search_Photo(idx)!=null) {
				photo = dao.leisure_Search_Photo(idx);
			}else 
				photo = null;
			
			int row = dao.leisure_Delete(idx, id_no);
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
