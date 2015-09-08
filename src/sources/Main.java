package sources;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Main extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		HttpSession sesh = request.getSession();
		User currentUser = (User)sesh.getAttribute("User");
		Page pages = (Page)sesh.getAttribute("pages"); 
		Servlet servlets = (Servlet)sesh.getAttribute("servlets"); 
		
		if (currentUser.notExists()){
			
			request.getRequestDispatcher(pages.login).forward(request, response);
			
		}
		
		else{
			
			request.getRequestDispatcher(pages.main).forward(request, response);
		}

		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}

}