package sources;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


public class ListMovies extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		PrintWriter out = response.getWriter();
		HttpSession sesh = request.getSession();
		User currentUser = (User)sesh.getAttribute("User");
		Page pages = (Page)sesh.getAttribute("pages"); 
		Servlet servlets = (Servlet)sesh.getAttribute("servlets"); 
		Query queryGenerator = new Query(); 
		
		if (currentUser.notExists()){
			
			request.getRequestDispatcher(pages.login).forward(request, response);
			
		}
		
		else{
			
			try { 
				
				//Create a JNDI Initial context to be able to lookup  the DataSource
				//consider caching this as a static or instance variable since its expensive to create JNDI context
				Context init = new InitialContext();
				if (init == null) out.println ("initialContext is NULL");
				
				//can also be cached as static/instance variable since JNDI lookups expensive. 
				Context env = (Context) init.lookup("java:comp/env"); 
				if (env == null ) out.println("Env is NULL");
				
				DataSource source = (DataSource)env.lookup("jdbc/moviedb"); 
				
				//get connection from pool 
				Connection connection = source.getConnection();
				ArrayList<Movie> movies = (ArrayList<Movie>)sesh.getAttribute("moviesToShow");
				PreparedStatement pStatement = null; 
				
				if (request.getParameter("action").equals("bySearch")){
					
					System.out.println("BY SEARCH");
					System.out.println(request.getParameter("search"));
					
				}
				
				
				if (request.getParameter("action").equals("byAdvancedSearch")){
					
					System.out.println("BY SEARCH");
					System.out.println(request.getParameter("title"));
					
				}
				
				if (request.getParameter("action").equals("byGenre")){
					
					pStatement = connection.prepareStatement(queryGenerator.genre(request.getParameter("genre")));
					movies = listByGenre(pStatement); 
					 
				}
				
				else if (request.getParameter("action").equals("byTitle")) {
					
					pStatement = connection.prepareStatement(queryGenerator.title(request.getParameter("Letter")));
					movies = listByTitle(pStatement); 
				}
				
				
				sesh.setAttribute("moviesToShow", movies);
				sesh.setAttribute("showAdvancedMenu", false);
				request.getRequestDispatcher("/main.jsp").forward(request, response);
 				
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}
	
	private ArrayList<Movie> listByGenre(PreparedStatement pStatement) throws SQLException{
		
		ResultSet result = pStatement.executeQuery();
		ArrayList<Movie> movies = new ArrayList<Movie>(); 

		while(result.next()){
			
			movies.add(new Movie(result.getString("id"), result.getString("title"))); 
		}
		
		return movies; 
	}

	private ArrayList<Movie> listByTitle(PreparedStatement pStatement) throws SQLException{
		
		ResultSet result = pStatement.executeQuery();
		ArrayList<Movie> movies = new ArrayList<Movie>(); 

		while(result.next()){
			
			movies.add(new Movie(result.getString("id"), result.getString("title"))); 
		}
		
		return movies; 
	}
	
	private ArrayList<Movie> listBySearch(PreparedStatement pStatement) throws SQLException{
		
		ArrayList<Movie> movies = new ArrayList<Movie>(); 
		
		return movies; 
	}
	
}