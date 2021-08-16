package action.gallery.sa;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.gallery.sa.saDAO;
import model.gallery.sa.saVO;

public class GallerySaViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		saDAO dao = saDAO.getinstance();
		saVO vo = dao.sa_View(request.getParameter("idx"));
		
		String idx = request.getParameter("idx");
		int page = 1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		// 쿠키 검사
		boolean bool = false;
		Cookie info = null;
		
		Cookie[] cookies = request.getCookies();	// 배열상태의 쿠키 호출
		
		for(int x=0; x<cookies.length; x++){
			info = cookies[x];
			if(info.getName().equals("Sa_"+idx)){
				bool = true;
				break;
				// 쿠키 생성시 브레이크
			}
		}
		
		String newValue = "" + System.currentTimeMillis();	//1000분의 1초
		
		if(!bool){	// 쿠키가 존재하지 않으면
			// 조회수 증가 메소드
			dao.sa_Readcnt(idx);
		
			info = new Cookie("Sa_"+idx, newValue);	// 쿠키 생성
			
			info.setMaxAge(60*60*24);	// 24시간 - 세션 유지 시간 - 시간이 지나면 쿠키 삭제
			response.addCookie(info);
		}
		
		request.setAttribute("vo", vo);
		request.setAttribute("page", page);
		request.getRequestDispatcher("gallery/gallery_sa_view.jsp").forward(request, response);
	}

}
