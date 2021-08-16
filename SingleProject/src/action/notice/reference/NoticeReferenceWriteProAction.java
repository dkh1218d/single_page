package action.notice.reference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import model.notice.reference.referenceDAO;
import model.notice.reference.referenceVO;
import util.useMethod;

public class NoticeReferenceWriteProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		referenceDAO dao = referenceDAO.getinstance();
		referenceVO vo = new referenceVO();
		MultipartRequest multi = null;
		ServletContext context = request.getSession().getServletContext();
		String path = context.getRealPath("/notice/reference"); 	// 첨부파일 저장 경로 폴더
		String encType = "UTF-8";	// 인코딩 방식(한글)
		int sizeLimit = 500 * 1024 * 1024;	// 500메가바이트 파일첨부 용량
		try {
			multi = new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());
			vo.setName(multi.getParameter("name"));
			vo.setContents(multi.getParameter("contents"));
			vo.setSubject(multi.getParameter("subject"));
			vo.setId_no((String)request.getSession().getAttribute("id_no"));
			String imp = "F";
			if(multi.getParameter("important")!=null) {
				imp = multi.getParameter("important");
			}
			vo.setImportant(imp);
			
			int row = dao.reference_Insert(vo);
            String idx = dao.reference_Search_IDX(vo);
            response.setContentType("text/html; charset=UTF-8");
			if(row>0) {
				Enumeration files = multi.getFileNames();
	            while(files.hasMoreElements()){
	            	String name = (String)files.nextElement();
	            	String file_name = multi.getFilesystemName(name);
	            	String file_size = useMethod.getFileSize(path, file_name);
	            	vo.setIdx(idx);
	            	vo.setFile_name(file_name);
	            	vo.setFile_size(file_size);
	            	dao.reference_File_Insert(vo);
	            }
				response.getWriter().print("<script>alert('등록되었습니다'); location.href='SYKK?command=notice_reference'; </script>");
			}else {
				response.getWriter().print("<script>alert('등록 실패'); history.back();</script>");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
