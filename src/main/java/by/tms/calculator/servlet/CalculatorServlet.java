package by.tms.calculator.servlet;
/*
@author Ilya Moiseenko 28.08.2023
*/

import by.tms.calculator.models.Operation;
import by.tms.calculator.models.User;
import by.tms.calculator.services.OperationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CalculatorServlet", urlPatterns = "/calculator")
public class CalculatorServlet extends HttpServlet {

    private final OperationService operationService = new OperationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        if (user != null) {
            double num1 = Double.parseDouble(req.getParameter("num1"));
            double num2 = Double.parseDouble(req.getParameter("num2"));
            String type = req.getParameter("type");

            Operation operation = new Operation(num1, num2, type, user.getId());
            Operation result = operationService.calculate(operation);

            resp.getWriter().println(result.getResult());
        } else {
            resp.getWriter().println("Please, login or register!");
        }
    }
}
