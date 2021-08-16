package action.notice.apprise;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.notice.apprise.appriseDAO;

public class NoticeAppriseDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		appriseDAO dao = appriseDAO.getinstance();
		String idx = request.getParameter("idx");
		String id_no = (String)request.getSession().getAttribute("id_no");
		try {
			ServletContext context = request.getSession().getServletContext();
			String path = context.getRealPath("/notice/apprise");
			String photo = "";
			if(dao.apprise_Search_Photo(idx)!=null) {
				photo = dao.apprise_Search_Photo(idx);
			}else 
				photo = null;
			
			int row = dao.apprise_Delete(idx, id_no);
			if(row>0) { 
				if(photo!=null) {
					File file = new File(path, photo);
					file.delete();
				}
			}
			response.setContentType("text/html; charset=UTF-8");
			if(row>0) {
				response.getWriter().print("<script> alert('게시글이 삭제 되었습니다'); location.href='SYKK?command=notice_apprise'; </script>");
			}else {
				response.getWriter().print("<script>alert('삭제 실패'); history.back();</script>");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
