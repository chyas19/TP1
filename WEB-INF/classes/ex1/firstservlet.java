package ex1;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class firstservlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String birthDate = request.getParameter("birthDate");
        String sexe = request.getParameter("sexe");
        
        Cookie c1=new Cookie("name",firstName);
        Cookie c2=new Cookie("la9ab",lastName);
        Cookie c3=new Cookie("bday",birthDate);
        Cookie c4=new Cookie("sexe",sexe);
        
        response.addCookie(c1);
        response.addCookie(c2);
        response.addCookie(c3);
        response.addCookie(c4);

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Personal Information</title>");
        out.println("</head>");

        out.println("<body>");

        out.println("<h1>Personal Information</h1>");

        out.println("<table border='1'>");

        out.println("<tr>");
        out.println("<th>First Name</th>");
        out.println("<th>Last Name</th>");
        out.println("<th>Birth Date</th>");
        out.println("<th>Sexe</th>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>" + firstName + "</td>");
        out.println("<td>" + lastName + "</td>");
        out.println("<td>" + birthDate + "</td>");
        out.println("<td>" + sexe + "</td>");
        out.println("</tr>");

        out.println("</table>");

        out.println("</body>");
        out.println("</html>");
    }
}