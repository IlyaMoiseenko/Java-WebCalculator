package by.tms.calculator.servlet.user;
/*
@author Ilya Moiseenko 28.08.2023
*/

import by.tms.calculator.models.User;
import by.tms.calculator.services.OperationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HistoryServlet", urlPatterns = "/history")
public class HistoryServlet extends HttpServlet {

    private final OperationService operationService = new OperationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        if (user != null) {
            List<String> historyByUser = operationService.getHistoryByUser(user);

            resp.getWriter().println(historyByUser);
        } else {
            resp.getWriter().println("Please, login or register");
        }
    }
}
