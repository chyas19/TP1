
package ex1;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ListItems extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();

        ArrayList<String> items =
                (ArrayList<String>) session.getAttribute("items");

        if (items == null) {
            items = new ArrayList<String>();
        }

        String newItem = request.getParameter("newItem");

        if (newItem != null && !newItem.trim().isEmpty()) {
            items.add(newItem.trim());
        }

        session.setAttribute("items", items);

        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>List of Items</h1>");

        for (int i = 0; i < items.size(); i++) {

            String item = items.get(i);
            int count = 0;

            for (int j = 0; j < items.size(); j++) {
                if (items.get(j).equals(item)) {
                    count++;
                }
            }

            boolean alreadyDisplayed = false;

            for (int k = 0; k < i; k++) {
                if (items.get(k).equals(item)) {
                    alreadyDisplayed = true;
                    break;
                }
            }

            if (!alreadyDisplayed) {
                out.println("<p>" + item + " : " + count + " fois</p>");
            }
        }

        out.println("<br><a href='items.html'>Add another item</a>");
        out.println("</body></html>");
    }
}
