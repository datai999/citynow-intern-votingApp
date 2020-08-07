package controller;

import database.CloudinaryConnection;
import model.dao.IUserService;
import model.dto.user.UserAccount;
import model.dao.impl.UserServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/register"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class RegisterController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    IUserService userService;

    public RegisterController() {
        super();
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/register.jsp");

        dispatcher.forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String fullName = request.getParameter("fullName");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String avatar = request.getParameter("avatar");

        UserAccount user = new UserAccount(username, password, email, fullName, avatar);

        boolean isRegisterSuccess = userService.register(user);

        if (isRegisterSuccess){
//            if (LoginDao.getInstance().login(username,password) != null)
//                response.sendRedirect(request.getContextPath() + "/home");
//            else
                response.sendRedirect(request.getContextPath() + "/login");
        }
        else{
//            _logger.info("Login invalid");
            response.sendRedirect(request.getContextPath() + "/register");
        }

    }

}
