package by.tms.calculator.servlet.user;

import by.tms.calculator.models.User;
import by.tms.calculator.services.UserService;
import by.tms.calculator.utils.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdatePasswordServlet", urlPatterns = {"/update-password"})
public class UpdatePasswordServlet extends HttpServlet {

    private final UserService userService = new UserService();
    private final UserValidator validation = new UserValidator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("user");
        String password = req.getParameter("password");

        if (validation.passwordValidation(password)) {
            boolean updatedStatus = userService.updatePassword(currentUser, password);
            if (updatedStatus)
                resp.getWriter().println("Password was updated!");
        }
    }
}
