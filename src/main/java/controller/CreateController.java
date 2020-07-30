package controller;

import controller.session_and_cookie.UserSession;
import model.dao.IAdminService;
import model.dto.user.UserAccount;
import model.dao.impl.AdminServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(urlPatterns = { "/create" })
public class CreateController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    Logger _logger;
    IAdminService adminService;

    public CreateController() {
        super();
        adminService = new AdminServiceImpl();
        _logger = Logger.getLogger(this.getClass().getName());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/create.jsp");

        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String title = request.getParameter("title");
        String question = request.getParameter("question");
        String[] options = request.getParameterValues("options");
        String deadline = request.getParameter("deadline");

        HttpSession session = request.getSession();
        UserAccount userInSession = UserSession.getUserLoginSuccess(session);
        int userId = userInSession.getId();

        boolean isSuccess = adminService.createPoll(userId, deadline, title, question, options);
        if (isSuccess){
            response.sendRedirect(request.getContextPath() + "/home");
        }
        else{
            _logger.info("Create question fail");
            doGet(request,response);
        }
    }
}
