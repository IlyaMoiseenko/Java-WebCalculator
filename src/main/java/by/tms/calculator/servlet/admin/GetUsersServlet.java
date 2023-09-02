package by.tms.calculator.servlet.admin;

import by.tms.calculator.models.User;
import by.tms.calculator.services.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/get-users")
public class GetUsersServlet extends HttpServlet {

    private final AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> allUsers = adminService.findAll();
        StringBuilder stringBuilder = new StringBuilder();

        for (User allUser : allUsers) {
            stringBuilder.append(allUser.toString());
        }

        resp.getWriter().println(stringBuilder);
    }
}
