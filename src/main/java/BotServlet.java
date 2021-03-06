import model.Bot;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * model.Bot Servlet class.
 *
 * Created by promoscow on 26.07.17.
 */
public class BotServlet extends HttpServlet {

    private Bot bot;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();

        bot = new Bot("Amigo", "228274635", 1);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Enter doGet");

        String action = request.getParameter("action");
        request.setAttribute("bot", bot);
        switch (action == null ? "info" : action) {
            case "update":
                request.getRequestDispatcher("/update.jsp").forward(request, response);
                break;
            case "info":
            default:
                request.getRequestDispatcher("/bot.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Enter doPost");

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if ("submit".equals(action)) {
            bot.setId(Integer.parseInt(request.getParameter("id")));
            bot.setName(request.getParameter("name"));
            bot.setSerial(request.getParameter("serial"));
        }

        request.setAttribute("bot", bot);
        request.getRequestDispatcher("/bot.jsp").forward(request, response);
    }
}