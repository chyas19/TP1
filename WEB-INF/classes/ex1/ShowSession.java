package ex1;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.temporal.ChronoUnit;

public class ShowSession extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();

        Integer visits = (Integer) session.getAttribute("visits");

        if (visits == null) {
            visits = 1;
        } else {
            visits++;
        }

        session.setAttribute("visits", visits);

        out.println("<html><body>");

        if (session.isNew()) {
            out.println("<h1>Welcome on my site</h1>");

            out.println("<form method='post' action='ShowSession'>");
            out.println("Full name: <input type='text' name='fullName' required><br><br>");
            out.println("Date of birth: <input type='date' name='birthDate' required><br><br>");
            out.println("<input type='submit' value='Submit'>");
            out.println("</form>");

        } else {
            String fullName = (String) session.getAttribute("fullName");
            String birthDate = (String) session.getAttribute("birthDate");

            if (fullName != null && birthDate != null) {
                LocalDate birth = LocalDate.parse(birthDate);
                LocalDate today = LocalDate.now();

                LocalDate nextBirthday = birth.withYear(today.getYear());

                if (!nextBirthday.isAfter(today)) {
                    nextBirthday = nextBirthday.plusYears(1);
                }

                long days = ChronoUnit.DAYS.between(today, nextBirthday);

                out.println("<h1>Hi, " + fullName + ".</h1>");
                out.println("<p>There are " + days
                        + " days to your birthday.</p>");
            } else {
                out.println("<h1>Welcome back</h1>");
                out.println("<p>Please enter your information:</p>");

                out.println("<form method='post' action='ShowSession'>");
                out.println("Full name: <input type='text' name='fullName' required><br><br>");
                out.println("Date of birth: <input type='date' name='birthDate' required><br><br>");
                out.println("<input type='submit' value='Submit'>");
                out.println("</form>");
            }
        }

        out.println("<p>Number of visits: " + visits + "</p>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        String fullName = request.getParameter("fullName");
        String birthDate = request.getParameter("birthDate");

        if (fullName != null && !fullName.trim().isEmpty()
                && birthDate != null && !birthDate.isEmpty()) {

            session.setAttribute("fullName", fullName.trim());
            session.setAttribute("birthDate", birthDate);
        }

        response.sendRedirect("ShowSession");
    }
}