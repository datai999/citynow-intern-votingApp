package controller;

import controller.session_and_cookie.UserSession;
import model.dto.poll.Poll;
import model.dto.user.UserAccount;
import model.dao.IUserService;
import model.dao.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/","/home"})
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    IUserService userService;
    List<Object> lsObj;
    int currentVote = 0;
    int size = 0;

    public HomeController() {
        super();
        userService = new UserServiceImpl();
//        lsObj = userService.getAllPoll();
//        size = ((List<Poll>) lsObj.get(0)).size();
//        currentVote = size-1;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        lsObj = userService.getAllPoll();
        size = ((List<Poll>) lsObj.get(0)).size();
        currentVote = size-1;

        UserAccount user = UserSession.getUserLoginSuccess(request.getSession());
        request.setAttribute("user", user);

        Poll currentPoll = ((List<Poll>) lsObj.get(0)).get(currentVote);
        request.setAttribute("currentPoll", currentPoll);

        UserAccount currentPollUser = ((List<UserAccount>) lsObj.get(1)).get(currentVote);
        request.setAttribute("currentPollUser", currentPollUser);

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp");

        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String previous = request.getParameter("previous");
        String next = request.getParameter("next");

        if (previous != null){
            if (currentVote == 0) currentVote = size;
            currentVote--;
        }

        if (next != null){
            if (currentVote == size-1) currentVote = -1;
            currentVote ++;
        }

        doGet(request, response);
    }
}
