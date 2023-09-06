package by.tms.calculator.servlet.user;

import by.tms.calculator.interfaces.Validation;
import by.tms.calculator.models.User;
import by.tms.calculator.services.UserService;
import by.tms.calculator.utils.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateUsernameServlet", urlPatterns = {"/update-username"})
public class UpdateUsernameServlet extends HttpServlet {

    private final UserService userService = new UserService();
    private final Validation validation = new Validator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("user");
        String newUsername = req.getParameter("username");

        if (validation.usernameValidation(newUsername)) {
            boolean updatedStatus = userService.updateUsername(currentUser, newUsername);
            if (updatedStatus)
                resp.getWriter().println("Username was updated!");
        }
    }
}
