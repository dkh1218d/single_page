package action.board.free;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.board.free.freeDAO;
import model.board.free.freeVO;

public class BoardFreeViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		freeDAO dao = freeDAO.getinstance();
		String idx = request.getParameter("idx");
		freeVO vo = dao.free_View(idx);
		
		int page = 1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		request.setAttribute("vo", vo);
		request.setAttribute("page", page);
		// 쿠키 검사
		boolean bool = false;
		Cookie info = null;
		
		Cookie[] cookies = request.getCookies();	// 배열상태의 쿠키 호출
		
		for(int x=0; x<cookies.length; x++){
			info = cookies[x];
			if(info.getName().equals("Fr_"+idx)){
				bool = true;
				break;
				// 쿠키 생성시 브레이크
			}
		}
		
		String newValue = "" + System.currentTimeMillis();	//1000분의 1초
		
		if(!bool){	// 쿠키가 존재하지 않으면
			// 조회수 증가 메소드
			dao.free_Readcnt(idx);
		
			info = new Cookie("Fr_"+idx, newValue);	// 쿠키 생성
			
			info.setMaxAge(60*60*24);	// 24시간 - 세션 유지 시간 - 시간이 지나면 쿠키 삭제
			response.addCookie(info);
		}
		
		// 댓글 작성 
		
		List<freeVO> list = dao.reply_List(idx);
		int reply_cnt = dao.reply_cnt(idx); 
		request.setAttribute("list", list);
		request.setAttribute("reply_cnt", reply_cnt);
		request.getRequestDispatcher("board/free_view.jsp").forward(request, response);
	}

}
