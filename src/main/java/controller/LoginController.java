package controller;

import controller.session_and_cookie.UserSession;
import model.dao.IUserService;
import model.dtO.user.UserAccount;
import model.dtO.user.UserRole;
import model.dao.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(urlPatterns = { "/login",})
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final Logger _logger;
    IUserService userService;

    public LoginController() {
        super();
        userService = new UserServiceImpl();
        _logger = Logger.getLogger(this.getClass().getName());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");


        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {


        String username = request.getParameter("username");
        String password = request.getParameter("password");


        UserAccount user = null;
        if (userService != null)
            user = userService.login(username,password);

        if (user != null){
            _logger.info("Login success");
            UserSession.storeLoginSuccess(request.getSession(), user);
            if (user.getRole() == UserRole.ROOT.value){
//                System.out.println("redirect to manager");
                response.sendRedirect((request.getContextPath() + "/root"));
                return;
            }
            response.sendRedirect(request.getContextPath() + "/home");
        }
        else{
            _logger.info("Login invalid");
            response.sendRedirect(request.getContextPath() + "/login");
        }


    }
}
