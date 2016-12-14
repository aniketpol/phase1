package twilio;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


/**
 * 
 * @author Aniket
 * Class is designed to show generic message on error
 * ErrorHandler entry is added to wen.xml as a error handler
 * in order to secure application from displaying critical information such as class name its wrapped in this handler
 * 
 * 
 */
public class ErrorHandler extends HttpServlet {
 
  
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
  {
            
      Throwable throwable = (Throwable)
      request.getAttribute("javax.servlet.error.exception");
      request.getAttribute("javax.servlet.error.status_code");
      String servletName = (String)
      request.getAttribute("javax.servlet.error.servlet_name");
      if (servletName == null){
         servletName = "Unknown";
      }
      String requestUri = (String)
      request.getAttribute("javax.servlet.error.request_uri");
      if (requestUri == null){
         requestUri = "Unknown";
      }
      response.setContentType("text/html");
 
      PrintWriter out = response.getWriter();
	  String title = "Error/Exception Information";
      String docType =
      "<!doctype html public \"-//w3c//dtd html 4.0 " +
      "transitional//en\">\n";
      out.println(docType +
        "<html>\n" +
        "<head><title>" + title + "</title></head>\n" +
        "<body bgcolor=\"#f0f0f0\">\n");
      out.println("Exception Type : " +throwable.getClass( ).getName( ) + "</br></br>");  
      out.println("The exception message: " +throwable.getMessage( ));
     
      out.println("</body>");
      out.println("</html>");
  }
 
  public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
  }
}