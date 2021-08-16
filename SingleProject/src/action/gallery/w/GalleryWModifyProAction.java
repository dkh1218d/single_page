package action.gallery.w;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import model.gallery.w.wDAO;
import model.gallery.w.wVO;

public class GalleryWModifyProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		wDAO dao = wDAO.getinstance();
		wVO vo = new wVO();
		String id_no = (String)request.getSession().getAttribute("id_no");
		String idx = request.getParameter("idx");
		MultipartRequest multi = null;
		ServletContext context = request.getSession().getServletContext();
		String path = context.getRealPath("/gallery/gallery_w"); 	// 첨부파일 저장 경로 폴더
		String encType = "UTF-8";	// 인코딩 방식(한글)
		int sizeLimit = 1 * 1024 * 1024;	// 1메가바이트 파일첨부 용량
		try {
			multi = new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());
			String newPhoto = multi.getFilesystemName("photo");
			
			if(newPhoto==null) {
				vo.setName(multi.getParameter("name"));
				vo.setSubject(multi.getParameter("subject"));
				vo.setContents(multi.getParameter("contents"));
				vo.setId_no(id_no);
				vo.setIdx(idx);
			}else {
				String photo = "";
				if(dao.w_Search_Photo(idx)!=null) {
					photo = dao.w_Search_Photo(idx);
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
			}
			
			int row=dao.w_Modify(vo, newPhoto);
			response.setContentType("text/html; charset=UTF-8");
			if(row>0) {
				response.getWriter().print("<script>alert('수정되었습니다'); location.href='SYKK?command=gallery_w'; </script>");
			}else {
				response.getWriter().print("<script>alert('수정 실패'); history.back(); </script>");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
