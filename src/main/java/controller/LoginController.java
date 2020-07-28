package controller;

import controller.session_and_cookie.UserSession;
import model.dao.impl.LoginDao;
import model.dto.UserAccount;
import model.dto.UserRole;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(urlPatterns = { "/","/login",})
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final Logger _logger;

    public LoginController() {
        super();
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

        LoginDao instance = LoginDao.getInstance();
        UserAccount user = null;
        if (instance != null)
            user = instance.login(username,password);

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
