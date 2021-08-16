package action.gallery.f;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import model.gallery.f.fDAO;
import model.gallery.f.fVO;

public class GalleryFModifyProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		fDAO dao = fDAO.getinstance();
		fVO vo = new fVO();
		
		String idx = request.getParameter("idx");
		MultipartRequest multi = null;
		ServletContext context = request.getSession().getServletContext();
		String path = context.getRealPath("/gallery/gallery_f"); 	// 첨부파일 저장 경로 폴더
		String encType = "UTF-8";	// 인코딩 방식(한글)
		int sizeLimit = 1 * 1024 * 1024;	// 1메가바이트 파일첨부 용량
		try {
			multi = new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());
			String newPhoto = multi.getFilesystemName("photo");
			
			if(newPhoto==null) {
				vo.setName(multi.getParameter("name"));
				vo.setSubject(multi.getParameter("subject"));
				vo.setContents(multi.getParameter("contents"));
				vo.setId_no((String)request.getSession().getAttribute("id_no"));
				vo.setIdx(idx);
			}else {
				String photo = "";
				if(dao.f_Search_Photo(idx)!=null) {
					photo = dao.f_Search_Photo(idx);
				}else 
					photo = null;
				File file = new File(path, photo);
				file.delete();
				vo.setName(multi.getParameter("name"));
				vo.setSubject(multi.getParameter("subject"));
				vo.setContents(multi.getParameter("contents"));
				vo.setId_no((String)request.getSession().getAttribute("id_no"));
				vo.setIdx(idx);
				vo.setPhoto(newPhoto);
			}
			
			int row=dao.f_Modify(vo, newPhoto);
			response.setContentType("text/html; charset=UTF-8");
			if(row>0) {
				response.getWriter().print("<script>alert('수정되었습니다'); location.href='SYKK?command=gallery_f'; </script>");
			}else {
				response.getWriter().print("<script>alert('수정 실패'); history.back(); </script>");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
