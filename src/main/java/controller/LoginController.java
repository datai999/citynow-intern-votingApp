package controller;

import controller.session.Session;
import model.DAO.LoginAction;
import model.DTO.UserAccount;
import setting.IConst;

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

        boolean isLoginSuccess = LoginAction.getInstance().login(username,password, request.getSession());

        if (isLoginSuccess){
//            _logger.info("Login success");
            UserAccount user = Session.getUserLoginSuccess(request.getSession());
            if (user.getRole() == UserAccount.ROOT){
//                System.out.println("redirect to manager");
                response.sendRedirect((request.getContextPath() + "/root"));
                return;
            }
            response.sendRedirect(request.getContextPath() + "/home");
        }
        else{
//            _logger.info("Login invalid");
            response.sendRedirect(request.getContextPath() + "/login");
        }


    }
}
