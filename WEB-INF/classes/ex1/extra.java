
package ex1;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class extra extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        int visits = 0;

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("visits")) {
                    visits = Integer.parseInt(c.getValue());
                    break;
                }
            }
        }

        visits++;

        Cookie counter = new Cookie("visits", String.valueOf(visits));
        counter.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(counter);

        out.println("<html>");
        out.println("<head><title>Visit Counter</title></head>");
        out.println("<body>");
        out.println("<h1>Visit Counter</h1>");
        out.println("<p>Number of visits: " + visits + "</p>");
        out.println("</body>");
        out.println("</html>");
    }
}