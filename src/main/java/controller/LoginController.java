package controller;

import model.DAO.Login;
import model.DTO.UserAccount;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

        boolean isLoginSuccess = Login.getInstance().login(username,password, request.getSession());

        if (isLoginSuccess){
//            _logger.info("Login success");
            response.sendRedirect(request.getContextPath() + "/home");
        }
        else{
//            _logger.info("Login invalid");
            response.sendRedirect(request.getContextPath() + "/login");
        }


    }
}
