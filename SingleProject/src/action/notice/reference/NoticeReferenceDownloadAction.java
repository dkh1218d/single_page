package action.notice.reference;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import model.notice.reference.referenceDAO;

public class NoticeReferenceDownloadAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idx = request.getParameter("idx");
		referenceDAO dao = referenceDAO.getinstance();
		
		String filename = dao.reference_Search_Filename(idx);
		String uploadfilename = request.getSession().
				getServletContext().getRealPath("notice/reference")+"/"+filename;
		
		File downFile = new File(uploadfilename);
		if(downFile.exists()&&downFile.isFile()) {
			// exists = 파일이 존재하는지 / isFile = 경로 안에 파일이 있는지
			try {
				long filesize = downFile.length();	// 파일 크기 측정
				response.setContentType("application/x-msdownload");
				response.setContentLength((int)filesize);
				String strClient = request.getHeader("user-agent");

				if(strClient.indexOf("MSIE 5.5")!=-1) 
					response.setHeader("Content-Disposition", "filename=" + filename + ";" );

				else { 
					filename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+","%20");
					response.setHeader("Content-Disposition", "attachment; filename=" + filename + ";" );
				}

				response.setHeader("Content-Length", String.valueOf(filesize));
				response.setHeader("Content-Transfer-Encoding", "binary;");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "private");
				
				byte b[] = new byte[500*1024*1024];
				BufferedInputStream bufin = new BufferedInputStream(new FileInputStream(downFile));
				BufferedOutputStream bufout = new BufferedOutputStream(response.getOutputStream());
				int read = 0;
				while((read=bufin.read(b)) != -1)	{
					bufout.write(b, 0, read);
				}
				bufout.flush();
				bufout.close();
				bufin.close();

			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Download Error : downFile Error [" + downFile + "]");
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print("<script> history.back(); </script>");
		}
	}

}
