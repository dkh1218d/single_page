package action.about.introduce;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.about.introduce.introduceDAO;

public class AboutIntroduceEducationProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		introduceDAO dao = introduceDAO.getinstance();
		int row = dao.About_education(request.getParameter("education"));
		
		response.setContentType("text/html; charset=UTF-8");
		if(row>0) {
			response.getWriter().print("<script>alert('추가되었습니다'); opener.location.replace('SYKK?command=about_introduce');self.close(); </script>");
		}else {
			response.getWriter().print("<script>alert('추가 실패'); location.href=history.back();</script>");
		}
	}

}
