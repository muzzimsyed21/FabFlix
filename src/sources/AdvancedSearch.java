package sources;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AdvancedSearch extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		HttpSession sesh = request.getSession();
		User currentUser = (User)sesh.getAttribute("User");
		Page pages = (Page)sesh.getAttribute("pages"); 
		Servlet servlets = (Servlet)sesh.getAttribute("servlets"); 
		request.getSession().setAttribute("showMovies", false);
		
		if (currentUser== null){ // TODO FIX DIRECT LINK WITHOUT LOGIN REROUTE BACK TO LOGIN INTERFACE

			ArrayList<Movie> movies =  (ArrayList<Movie>)sesh.getAttribute("moviesToShow");
			if (movies != null) {
				movies.clear();
			}
			
			sesh.setAttribute("moviesToShow", movies);
			request.getRequestDispatcher(pages.login).forward(request, response);
			
		}
		
		else{
			
			ArrayList<Movie> movies =  (ArrayList<Movie>)sesh.getAttribute("moviesToShow");
			if (!movies.isEmpty()){
				movies.clear();
			}
			
			sesh.setAttribute("moviesToShow", movies);
			sesh.setAttribute("showAdvancedMenu", true);
			request.getRequestDispatcher(pages.main).forward(request, response);
			
		}

		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}

}