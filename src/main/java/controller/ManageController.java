package controller;

import model.service.*;
import model.service.impl.RootServiceImpl;
import model.service.dao.root.UpdateRoleDao;
import model.dto.UserAccount;

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
        lsUser = rootService.getAllUser();
        _logger = Logger.getLogger(this.getClass().getName());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("lsUser", lsUser);

//        lsUser.forEach(System.out::println);

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/manager.jsp");
            dispatcher.forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] lsId = request.getParameterValues("checkboxes");

        if (lsId != null){
            if (UpdateRoleDao.getInstance().updateRole(lsId)){
                lsUser.clear();
                lsUser = rootService.getAllUser();
            }
            else {
                _logger.info("Update role failed");
            }
        }
        else {
            _logger.info("lsId is null");
        }

        doGet(request,response);
    }
}
