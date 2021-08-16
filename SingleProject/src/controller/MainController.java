package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;


/**
 * Servlet implementation class AboutController
 */
@WebServlet("/SYKK")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Action action = null;
		
		String command = request.getParameter("command");
		System.out.println("command : "+command);
		String div[] = command.split("_");
		
		switch(div[0]){
			case "member" : action = MemberActionFactory.getinstance().getAction(command);	break;
			case "about" : action = AboutActionFactory.getinstance().getAction(command);	break;
			case "gallery" : action = GalleryActionFactory.getinstance().getAction(command);break;
			case "hobit" : action = HobitActionFactory.getinstance().getAction(command);	break;
			case "board" : action = BoardActionFactory.getinstance().getAction(command);	break;
			case "notice" : action = NoticeActionFactory.getinstance().getAction(command);	break;
			
			
			default	:	response.setContentType("text/html; charset=UTF-8");
						response.getWriter().print("<script>location.href='';</script>");
		}
		
		if(action!=null)action.execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
