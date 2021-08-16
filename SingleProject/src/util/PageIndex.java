package util;

public class PageIndex{
	public static String pageList(int page, int totpage, String url, String addtag){
		String s_pre = "";    // Prev 저장 변수
		String s_idx = "";    // 번호 저장 변수
		String s_next = "";   // Next 저장 변수
		
		String double_prev_html = "<i class='fa  fa-angle-double-left'></i>";
		String double_next_html = "<i class='fa  fa-angle-double-right'></i>";
		String prev_html = "<i class='fa fa-angle-left'></i>";
		String next_html = "<i class='fa fa-angle-right'></i>";
	
	  	// Prev 표시 부분
		double_prev_html = "<a href='" + url + "&page=1" + addtag + "'>" + double_prev_html + "</a>" ;
		double_next_html = "<a href='" + url + "&page=" + totpage + addtag + "'>" + double_next_html + "</a>" ;
		
	  	s_pre = "<a href='" + ( page > 1 ? url + "&page=" + (page - 1) : url + "&page=" + page ) + addtag + "'>" + prev_html + "</a>";

	  	int maxview = 5;
	  	
	  	int startpage = page - (maxview / 2);

	  	if (totpage - startpage < maxview)
	  		startpage = totpage - (maxview - 1);
	  	
	  	if (startpage < 1)
	  		startpage = 1;
	  	
	  	// 번호 표시부분	
	  	for(int i = 0; i < maxview; i++, startpage++){
	  		if( startpage > totpage ) 
	  			break;
	  		
	  		if( startpage == page )
	  			s_idx += "<a class='active'>" + startpage + "</a>";
	  		else 
	  			s_idx += "<a href='" + url + "&page=" + startpage + addtag + "'>" + startpage + "</a>";
	  	}

		// Next 표시부분
	  	s_next = "<a href='" + ( page < totpage ? url + "&page=" + (page + 1) : url + "&page=" + page) + addtag +  "'>" + next_html + "</a>";
	  	
		String s_result;
		  	
		  	if (totpage < maxview)
		  		s_result = s_idx;
		  	else if (page <= 1)
		  		s_result = s_idx + s_next + double_next_html;
		  	else if (page >= totpage)
		  		s_result = double_prev_html + s_pre + s_idx;
		  	else
		  		s_result = double_prev_html + s_pre + s_idx + s_next + double_next_html;
		  	
		 return s_result;
	}

}
