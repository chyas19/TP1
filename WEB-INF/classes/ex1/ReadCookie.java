package ex1;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
public class ReadCookie extends HttpServlet{
     @Override
    protected void doGet(HttpServletRequest request,
                           HttpServletResponse response)
            throws IOException {
    response.setContentType("text/html");

    PrintWriter out = response.getWriter();
    
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Personal Information</title>");
    out.println("</head>");
    out.println("<body>");

    Cookie[] cookies=request.getCookies();
    out.println("<table border='1'>");
    
    if(cookies!=null)
    {
        for(Cookie c : cookies){
        out.println("<tr>");
        out.println("<th>"+c.getName()+"</th>");
        
        out.println("<td>" + c.getValue() + "</td>");
        out.println("</tr>");
        
        }
    }
    
     out.println("</table>");
    out.println("</body>");
        out.println("</html>");
    }

}