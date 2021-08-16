package action.notice.apprise;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import model.notice.apprise.appriseDAO;
import model.notice.apprise.appriseVO;

public class NoticeAppriseWriteProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		appriseDAO dao = appriseDAO.getinstance();
		appriseVO vo = new appriseVO();
		MultipartRequest multi = null;
		ServletContext context = request.getSession().getServletContext();
		String path = context.getRealPath("/notice/apprise"); 	// 첨부파일 저장 경로 폴더
		String encType = "UTF-8";	// 인코딩 방식(한글)
		int sizeLimit = 5 * 1024 * 1024;	// 5메가바이트 파일첨부 용량
		
		try {
			multi = new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());
			vo.setName(multi.getParameter("name"));
			vo.setContents(multi.getParameter("contents"));
			vo.setSubject(multi.getParameter("subject"));
			String imp = "F";
			if(multi.getParameter("important")!=null) {
				imp = multi.getParameter("important");
			}
			vo.setImportant(imp);
			String photo = null;
			if(multi.getFilesystemName("photo")!=null) {
				photo = multi.getFilesystemName("photo");
			}
			vo.setPhoto(photo);
			vo.setId_no((String)request.getSession().getAttribute("id_no"));
			
			int row = dao.apprise_Insert(vo);
			
			response.setContentType("text/html; charset=UTF-8");
			if(row>0) {
				response.getWriter().print("<script>alert('등록되었습니다'); location.href='SYKK?command=notice_apprise'; </script>");
			}else {
				response.getWriter().print("<script>alert('등록 실패'); history.back();</script>");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
