package action.notice.reference;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.notice.reference.referenceDAO;
import model.notice.reference.referenceVO;

public class NoticeReferenceDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		referenceDAO dao = referenceDAO.getinstance();
		String idx = request.getParameter("idx");
		String id_no = (String)request.getSession().getAttribute("id_no");
		try {
			ServletContext context = request.getSession().getServletContext();
			String path = context.getRealPath("/notice/reference");
			
			List<referenceVO> list = null;
			if(dao.reference_Delete_Filename(idx)!=null) {
				list = dao.reference_Delete_Filename(idx);
			}else 
				list = null;
			
			int row = dao.reference_Delete(idx, id_no);
			if(row>0) { 
				if(list!=null) {
					for(int x=0; x<list.size(); x++) {
						File file = new File(path, list.get(x).getFile_name());
						file.delete();
					}
				}
			}
			response.setContentType("text/html; charset=UTF-8");
			if(row>0) {
				response.getWriter().print("<script> alert('게시글이 삭제 되었습니다'); location.href='SYKK?command=notice_reference'; </script>");
			}else {
				response.getWriter().print("<script>alert('삭제 실패'); history.back();</script>");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
