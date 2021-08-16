package action.about.introduce;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import model.about.introduce.introduceDAO;
import model.about.introduce.introduceVO;

public class AboutIntroducePhotoProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		introduceDAO dao = introduceDAO.getinstance();
		introduceVO vo = dao.About_photo_idx();
		
		MultipartRequest multi = null;
		ServletContext context = request.getSession().getServletContext();
		String path = context.getRealPath("/about/photo"); 	// 첨부파일 저장 경로 폴더
		String encType = "UTF-8";	// 인코딩 방식(한글)
		int sizeLimit = 1 * 1024 * 1024;	// 1메가바이트 파일첨부 용량
		
		try {
			int row = 0;
			multi = new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());
			if(vo!=null) {
				dao.About_del(vo.getIdx());
				File file = new File(path, vo.getPhoto());
				file.delete();
				row = dao.About_photo(multi.getFilesystemName("photo"));
			}else
				row = dao.About_photo(multi.getFilesystemName("photo"));
			
			
			response.setContentType("text/html; charset=UTF-8");
			if(row>0) {
				response.getWriter().print("<script>alert('변경되었습니다'); opener.location.href='SYKK?command=about_introduce'; self.close(); </script>");
			}else {
				response.getWriter().print("<script>alert('변경 실패'); location.href=history.back();</script>");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
