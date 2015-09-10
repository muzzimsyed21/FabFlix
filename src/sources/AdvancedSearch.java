package sources; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class AdvancedSearch extends HttpServlet {
  public void init () throws ServletException {
    // ....
  }

  public void doGet (HttpServletRequest   req, HttpServletResponse  res) throws ServletException, IOException {

    res.setContentType("text/html");
    PrintWriter w = res.getWriter();
    w.println("<html><head><title>Hello World</title></head>");
    w.println("<body><h1>Hello World</h1></body></html>");

    w.flush(); // Commits the response
    w.close();
  }

  public void doPost (HttpServletRequest  req, HttpServletResponse  res) throws ServletException, IOException {
    // ...
  }

  public void destroy () {
    // ...
  }

  public String getServletInfo () {
    return "....";
  }
}