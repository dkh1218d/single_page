package util;

import javax.servlet.http.HttpServletRequest;

import KISA.SHA256;
import java.util.Base64;

public class UsefulMethod 
{
	public final static int SELF_CLOSE = 1;
	public final static int HISTORY_BACK = 2;
	public final static int LOCATION_HREF = 3;

	public static String SHA256Base64(String str)
	{
		SHA256 			s = new SHA256(str.getBytes());
		/* BASE64Encoder Base64 = new BASE64Encoder(); */
		/* return Base64.encode(s.GetHashCode()); */
		
		return Base64.getEncoder().encodeToString(s.GetHashCode());
		
	}
	
	public static String[] batchGetParameter(
			HttpServletRequest request, 
			String[] pnames)
	{
		String[] pvalues;
		boolean bIgnore = false;
		
		if (null == pnames)
			return null;
		
		pvalues = new String[pnames.length];
		
		for (int i = 0; i < pnames.length; ++i)
		{
			if (bIgnore = ('*' == pnames[i].charAt(0)))
				pnames[i] = pnames[i].substring(1);

			pvalues[i] = request.getParameter(pnames[i]);
					
			if (!bIgnore && (null == pvalues[i] || 0 == pvalues[i].length()))
				return null;
		}
		
		return pvalues;
	}
	
	public static boolean isNumberic(String val)
	{
		int len, i = 0;

		// String 에 아무것도 없을 경우
		if (null == val)
			return false;
		
		// 음수가 들어올 경우 맨 앞의 마이너스 문자는 올바른 것이기 때문에 검사 시작 포지션 을 한칸 늘린다.
		if (val.charAt(0) == '-')
			++i;
		
		// 음수 부호('-') 빼고 문자열 길이를 검사해서 1 미만일 경우
		if ((len = val.length()) - i < 1)
			return false;
		
		// 본격적으로 숫자인지 검사하는 부분
		for (int c; i < len; ++i)
			if ((c = val.charAt(i)) < '0' || c > '9')
				return false;
		
		return true;
	}
	
	public static String MakeRandomString(int MaxLine)
	{
		String seed = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ012343456789";
		StringBuffer sb = new StringBuffer();
		int seedlen = seed.length();
		
		for (int i = 0; i < MaxLine; ++i)
			sb.append(seed.charAt((int)(Math.random() * seedlen)));
		
		return sb.toString();
	}
	
	public static String pageList(int page, int totpage, String url, String addtag) 
	{
		String s_pre = "";    // Prev 저장 변수
		String s_idx = "";    // 번호 저장 변수
		String s_next = "";   // Next 저장 변수
		
		String double_prev_html = "<i class='fa  fa-angle-double-left'></i>";
		String double_next_html = "<i class='fa  fa-angle-double-right'></i>";
		String prev_html = "<i class='fa fa-angle-left'></i>";
		String next_html = "<i class='fa fa-angle-right'></i>";
	
	  	// Prev 표시 부분
		double_prev_html = "<a href='" + url + "?page=1" + addtag + "'>" + double_prev_html + "</a>" ;
		double_next_html = "<a href='" + url + "?page=" + totpage + addtag + "'>" + double_next_html + "</a>" ;
		
	  	s_pre = "<a href='" + url + "?page=" + (page - 1) + addtag + "'>" + prev_html + "</a>";

	  	int maxview = 5;
	  	
	  	int startpage = page - (maxview / 2);

	  	if (totpage - startpage < maxview)
	  		startpage = totpage - (maxview - 1);
	  	
	  	if (startpage < 1)
	  		startpage = 1;
	  	
	  	// 번호 표시부분	
	  	for (int i = 0; i < maxview; i++, startpage++) 
	  	{
	  		if( startpage > totpage ) 
	  			break;
	  		
	  		if( startpage == page )
	  			s_idx += "<a class='active'>" + startpage + "</a>";
	  		else 
	  			s_idx += "<a href='" + url + "?page=" + startpage + addtag + "'>" + startpage + "</a>";
	  	}

		// Next 표시부분
	  	s_next = "<a href='" + url + "?page=" + (page + 1) + addtag +  "'>" + next_html + "</a>";

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
	
	public static String MakeJSErrorProc(int type, String msg, String op1)
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append("<script>alert('");
		sb.append(msg);
		sb.append("');");
		
		switch (type)
		{
			case SELF_CLOSE:
				sb.append("self.close();");
				break;
			case HISTORY_BACK:
				sb.append("history.back();");
				break;
			case LOCATION_HREF:
				sb.append("location.href='");
				sb.append(op1);
				sb.append("';");
				break;
		}
		
		sb.append("</script>");
		
		return sb.toString();
	}
	
	public static String MakeJSErrorProc(int type, String msg)
	{
		return MakeJSErrorProc(type, msg, null);
	}
	
	public static String ParseFirstImage(String contents)
	{
		int st, ed, srcst;
		String imgt;
		
		contents = contents.replaceAll("\"", "'").replaceAll(" ", "");
				
		st = contents.indexOf("<");
		
		while (st > -1)
		{
			++st;
			
			if (contents.substring(st, st + 3).equals("img") && 
				(ed = contents.indexOf(">", st + 3)) > -1 &&
				(srcst = (imgt = contents.substring(st + 3, ed)).indexOf("src='")) > -1 &&
				(ed = imgt.indexOf("'", srcst + 5)) > -1)
			{
				return imgt.substring(srcst + 5, ed);
			}
			
			st = contents.indexOf("<", st);
		}
		
		return "/images/noimage.jpg";
	}
	
	public static String ParseHTMLtoText(String contents)
	{
		int len = contents.length();
		
		len = len < 256 ? len : 256;
		
		contents = contents.substring(0, len);
		
		int st = contents.lastIndexOf("<");

		if (st > -1)
			len = st;

		contents = contents.substring(0, len).replaceAll("\r|\n", " ");
		contents = contents.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", " ");
		contents = contents.replaceAll("<", "&lt;");
		contents = contents.replaceAll(">", "&gt;");
		
		return contents;
	}
	
	public static String[] FindMimeType(String extension)
	{
		String extInf[] = null;
		String MIME[] = {
				"png", 	"image/png", 	"false",
				"jpg", 	"image/jpeg", 	"false",
				"jpeg", "image/jpeg", 	"false",
				"gif", 	"image/gif", 	"false",
				"mp3", 	"video/mpeg", 	"false",
				"zip", 	"application/x-compressed", "true"
		};

		for (int i = 0, j = 3; i < MIME.length; i += 3)
		{
			if (MIME[i].equals(extension))
			{
				extInf = new String[j];
				
				while (j-- > 0)
					extInf[j] = MIME[i + j];
				
				break;
			}
		}
		
		return extInf;
	}
	
	public static String parseURLStringParameter(String URL, String name)
	{
		if (null == URL || null == name)
			return null;
		
		int st, ed;
		
		name += "=";
		
		if (0 > (st = URL.indexOf(name)))
			return null;
		
		st += name.length();
		
		if (-1 < (ed = URL.indexOf("&", st)))
			return URL.substring(st, ed);
		
		return URL.substring(st);
	}
}
