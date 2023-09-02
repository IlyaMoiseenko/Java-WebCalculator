package by.tms.calculator.servlet.admin;

import by.tms.calculator.models.User;
import by.tms.calculator.services.AdminService;
import by.tms.calculator.services.OperationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/admin/delete-user")
public class DeleteUserServlet extends HttpServlet {

    private final AdminService adminService = new AdminService();
    private final OperationService operationService = new OperationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID userId = UUID.fromString(req.getParameter("id"));

        boolean status = adminService.deleteUserById(userId);
        if (status) {
            operationService.deleteByUserId(userId);
            resp.getWriter().println("User was deleted!");
        } else {
            resp.getWriter().println("User not found!");
        }

    }
}
