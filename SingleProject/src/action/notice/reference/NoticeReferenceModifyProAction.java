package action.notice.reference;

import java.io.File;
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

public class NoticeReferenceModifyProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		referenceDAO dao = referenceDAO.getinstance();
		referenceVO vo = new referenceVO();
		referenceVO file_vo = new referenceVO();
		
		String page = request.getParameter("page");
		String id_no = (String)request.getSession().getAttribute("id_no");
		MultipartRequest multi = null;
		ServletContext context = request.getSession().getServletContext();
		String path = context.getRealPath("/notice/reference"); 	// 첨부파일 저장 경로 폴더
		String encType = "UTF-8";	// 인코딩 방식(한글)
		int sizeLimit = 50 * 1024 * 1024;	// 50메가바이트 파일첨부 용량
		try {
			multi = new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());
			String idx = multi.getParameter("idx");
			
			String imp = multi.getParameter("important");
			if(imp==null) {
				imp = "F"; 
			}
			
			vo.setName(multi.getParameter("name"));
			vo.setSubject(multi.getParameter("subject"));
			vo.setContents(multi.getParameter("contents"));
			vo.setId_no(id_no);
			vo.setIdx(idx);
			vo.setImportant(imp);
			
			Enumeration files = multi.getFileNames();
			if(files.hasMoreElements()) {
				List<referenceVO> old_list = null;
				if(dao.reference_Delete_Filename(idx)!=null)
					old_list = dao.reference_Delete_Filename(idx);
				else
					old_list = null;
				
				for(int x=0; x<old_list.size(); x++) {
					File file = new File(path, old_list.get(x).getFile_name());
					file.delete();
					dao.reference_Delete_File(old_list.get(x).getIdx(), old_list.get(x).getFile_name());
				}
				
				while(files.hasMoreElements()){
		           	String name = (String)files.nextElement();
		           	String file_name = multi.getFilesystemName(name);
		          	String file_size = useMethod.getFileSize(path, file_name);
		           	file_vo.setFile_name(file_name);
		           	file_vo.setFile_size(file_size);
		           	file_vo.setIdx(idx);
		           	dao.reference_File_Insert(file_vo);
		        }
			}
			
				
			int row=dao.reference_Modify(vo);
			response.setContentType("text/html; charset=UTF-8");
			if(row>0) {
				response.getWriter().print("<script>alert('수정되었습니다'); location.href='SYKK?command=notice_reference_view&idx="+idx+"&page="+page+"'; </script>");
			}else {
				response.getWriter().print("<script>alert('수정 실패'); history.back(); </script>");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
