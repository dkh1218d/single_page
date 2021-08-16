package action.hobit.travel;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import model.hobit.travel.travelDAO;
import model.hobit.travel.travelVO;

public class HobitTravelWriteProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		travelDAO dao = travelDAO.getinstance();
		travelVO vo = new travelVO();
		
		MultipartRequest multi = null;
		ServletContext context = request.getSession().getServletContext();
		String path = context.getRealPath("/hobit/travel"); 	// 첨부파일 저장 경로 폴더
		String encType = "UTF-8";	// 인코딩 방식(한글)
		int sizeLimit = 1 * 1024 * 1024;	// 1메가바이트 파일첨부 용량
		try {
			
			multi = new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());
			vo.setName(multi.getParameter("name"));
			vo.setSubject(multi.getParameter("subject"));
			vo.setContents(multi.getParameter("contents"));
			vo.setPhoto(multi.getFilesystemName("photo"));
			vo.setId_no((String)request.getSession().getAttribute("id_no"));
			int row = dao.travel_Insert(vo);
			
			response.setContentType("text/html; charset=UTF-8");
			if(row>0) {
				response.getWriter().print("<script>alert('등록되었습니다'); location.href='SYKK?command=hobit_travel'; </script>");
			}else {
				response.getWriter().print("<script>alert('등록 실패'); history.back();</script>");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
