package sources;

import java.sql.*;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.sql.DataSource;

//  @WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		HttpSession session = request.getSession();
		User currentUser = (User)session.getAttribute("User"); 
		
		if (currentUser.notExists()){
			//load login page	
			RequestDispatcher view = request.getRequestDispatcher("/login.html"); 
			view.forward(request, response);
		}
		
		else{
			//go to main page
			response.sendRedirect("Main");
		}
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath()); 
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Query queryGenerator = new Query(); 
		
		try {
			
			//Create a JNDI Initial context to be able to lookup the DataSource
			//consider caching this as a static or instance variable since its expensive to create JNDI context
			Context init = new InitialContext();
			if (init == null) out.println ("initialContext is NULL");
			
			//can also be cached as static/instance variable since JNDI lookups expensive. 
			Context env = (Context) init.lookup("java:comp/env"); 
			if (env == null ) out.println("Env is NULL");
			
			DataSource source = (DataSource)env.lookup("jdbc/moviedb"); 
			
			//get connection from pool 
			Connection connection = source.getConnection();
			
			//get login credential from login form 
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			//query input from form 
			PreparedStatement pStatement = connection.prepareStatement(queryGenerator.login(username, password));
			ResultSet result = pStatement.executeQuery();
			
			//if correct username/password add to to the session variable 
			if (result.next()){
				
				System.out.println("proceed to main");
				request.getSession().setAttribute("User", new User(result.getString("id"), result.getString("first_name"), result.getString("last_name"),
						result.getString("email"), result.getString("password"), new ArrayList<String>()));
				request.getSession().setAttribute("pages", new Page());
				request.getSession().setAttribute("servlets", new Servlet());
				request.getSession().setAttribute("moviesToShow", new ArrayList<Movie>());
				request.getSession().setAttribute("showAdvancedMenu", false); 
				
				response.sendRedirect("Main");
				
			}
			
			// or else no query was returned (wrong credentials) alert client
			else{
				
				request.getRequestDispatcher("/login.html").forward(request, response);
				
				// PRINT THIS SCRIPT ON WRONG UN/PASSWORD!
				//out.println("<SCRIPT>alert('Username and password do not match')</SCRIPT>");
				
				System.out.println("wrong password"); 
			}
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
