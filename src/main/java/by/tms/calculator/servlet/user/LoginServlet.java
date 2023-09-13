package by.tms.calculator.servlet.user;
/*
@author Ilya Moiseenko 28.08.2023
*/

import by.tms.calculator.enums.Role;
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

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Optional<User> user = userService.logIn(username, password);
        if (user.isPresent()) {
            User currentUser = user.get();

            if (currentUser.getRole() == Role.USER) {
                req.getSession().setAttribute("user", currentUser);
                resp.sendRedirect("/");
            } else if (currentUser.getRole() == Role.ADMIN) {
                req.getSession().setAttribute("admin", currentUser);
                resp.sendRedirect("/");
            }
        } else {
            resp.getWriter().println("Invalid user data!");
        }
    }
}
