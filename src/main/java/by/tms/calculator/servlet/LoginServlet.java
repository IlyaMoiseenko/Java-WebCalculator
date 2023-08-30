package by.tms.calculator.servlet;
/*
@author Ilya Moiseenko 28.08.2023
*/

import by.tms.calculator.models.User;
import by.tms.calculator.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Optional<User> user = userService.logIn(username, password);
        if (user.isPresent()) {
            req.getSession().setAttribute("user", user.get());
            resp.getWriter().println("Login success!");
        } else {
            resp.getWriter().println("Invalid user data!");
        }
    }
}
