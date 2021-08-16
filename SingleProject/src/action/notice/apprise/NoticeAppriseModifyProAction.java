package action.notice.apprise;

import java.io.File;
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

public class NoticeAppriseModifyProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		appriseDAO dao = appriseDAO.getinstance();
		appriseVO vo = new appriseVO();
		
		String page = request.getParameter("page");
		String id_no = (String)request.getSession().getAttribute("id_no");
		MultipartRequest multi = null;
		ServletContext context = request.getSession().getServletContext();
		String path = context.getRealPath("/notice/apprise"); 	// 첨부파일 저장 경로 폴더
		String encType = "UTF-8";	// 인코딩 방식(한글)
		int sizeLimit = 1 * 1024 * 1024;	// 1메가바이트 파일첨부 용량
		try {
			multi = new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());
			String newPhoto = multi.getFilesystemName("photo");
			String idx = multi.getParameter("idx");
			
			String imp = multi.getParameter("important");
			if(imp==null) {
				imp = "F"; 
			}
			
			if(newPhoto==null) {
				vo.setName(multi.getParameter("name"));
				vo.setSubject(multi.getParameter("subject"));
				vo.setContents(multi.getParameter("contents"));
				vo.setId_no(id_no);
				vo.setIdx(idx);
				vo.setImportant(imp);
			}else {
				String photo = "";
				if(dao.apprise_Search_Photo(idx)!=null) {
					photo = dao.apprise_Search_Photo(idx);
				}else 
					photo = null;
				File file = new File(path, photo);
				file.delete();
				vo.setName(multi.getParameter("name"));
				vo.setSubject(multi.getParameter("subject"));
				vo.setContents(multi.getParameter("contents"));
				vo.setIdx(idx);
				vo.setId_no(id_no);
				vo.setPhoto(newPhoto);
				vo.setImportant(imp);
			}
			
			int row=dao.apprise_Modify(vo, newPhoto);
			response.setContentType("text/html; charset=UTF-8");
			if(row>0) {
				response.getWriter().print("<script>alert('수정되었습니다'); location.href='SYKK?command=notice_apprise_view&idx="+idx+"&page="+page+"'; </script>");
			}else {
				response.getWriter().print("<script>alert('수정 실패'); history.back(); </script>");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
