package action.notice.reference;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.notice.reference.referenceDAO;
import model.notice.reference.referenceVO;
import util.PageIndex;

public class NoticeReferenceAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		referenceDAO dao = referenceDAO.getinstance();
		
		String key = null;
		String tempkey = request.getParameter("key");
		if(tempkey!=null)
			key = URLDecoder.decode(tempkey,"UTF-8");
		String search = request.getParameter("search"), addtag = "";
		
		if(key!=null && search!=null) {
			if(key.equals("") || search.equals("")) {
				key = null;
				search = null;
			}else {
				key = URLEncoder.encode(key,"UTF-8");
				addtag = "&search=" + search + "&key="+ key;
				key = URLDecoder.decode(key,"UTF-8");
			}
		}
		int cnt = dao.reference_Listcnt(key, search);
				
		int page = 1, totpage = 1, maxlist = 10;
		if(cnt%maxlist==0)
			totpage = cnt/maxlist;
		else totpage = cnt/maxlist+1;
		
		if(request.getParameter("page")!=null)
			page = Integer.parseInt(request.getParameter("page"));
		
		int liststart = (page-1)*maxlist;
		int listend = page*maxlist;
		int listcnt = cnt-liststart;
		
		List<referenceVO> list = dao.reference_List(liststart, listend, key, search);
		
		String pageSkip = PageIndex.pageList(page, totpage, "SYKK?command=notice_reference", addtag);
		
		request.setAttribute("search", search);
		request.setAttribute("key", key);
		request.setAttribute("listcnt", listcnt);
		request.setAttribute("totpage", totpage);
		request.setAttribute("page", page);
		request.setAttribute("pageSkip", pageSkip);
		request.setAttribute("list", list);
		request.setAttribute("cnt", cnt);
		request.getRequestDispatcher("notice/reference.jsp").forward(request, response);
	}

}
