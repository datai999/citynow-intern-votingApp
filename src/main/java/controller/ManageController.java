package controller;

import model.dao.*;
import model.dao.impl.RootServiceImpl;
import model.dto.user.UserAccount;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(urlPatterns = { "/root"})
public class ManageController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    Logger _logger;

    IRootService rootService;
    List<UserAccount> lsUser;

    public ManageController() {
        super();
        rootService = new RootServiceImpl();

        _logger = Logger.getLogger(this.getClass().getName());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        lsUser = rootService.getAllUser();
        request.setAttribute("lsUser", lsUser);

//        lsUser.forEach(System.out::println);

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/manager.jsp");
            dispatcher.forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] lsId = request.getParameterValues("checkboxes");

        if (rootService.updateRole(lsId)){
//            lsUser.clear();
//            lsUser = rootService.getAllUser();
        }

        doGet(request,response);
    }
}
