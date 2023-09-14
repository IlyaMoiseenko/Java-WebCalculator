package by.tms.calculator.servlet.user;
/*
@author Ilya Moiseenko 28.08.2023
*/

import by.tms.calculator.enums.Role;
import by.tms.calculator.models.User;
import by.tms.calculator.services.UserService;
import by.tms.calculator.utils.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final UserService userService = new UserService();
    private final UserValidator validation = new UserValidator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User(username, password, Role.USER);
        if (validation.validate(user))
            userService.create(user);

        resp.sendRedirect("/login");
    }
}
