package controller;

import controller.session_and_cookie.UserSession;
import model.dto.poll.Poll;
import model.dto.user.UserAccount;
import model.dao.IUserService;
import model.dao.impl.UserServiceImpl;
import model.dto.user.UserRole;
import cache.PollCache;

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
    int day = 3;

    public HomeController() {
        super();
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        int timeNow = (int) (System.currentTimeMillis()/1000);

        //                Get top vote
        List<Poll> lsTopPoll = PollCache.getInstance().getTopPull();
        if (lsTopPoll == null){
            lsTopPoll = userService.getTopVote(timeNow - day*24*60*60, timeNow);
            PollCache.getInstance().setTopPollCache(lsTopPoll);
        }
        request.setAttribute("lsTopPoll", lsTopPoll);


        UserAccount user = UserSession.getUserLoginSuccess(request.getSession());
        request.setAttribute("user", user);


//        Get poll
        UserRole viewRole = UserRole.GUEST;
        if (user != null)
            viewRole = UserRole.fromInteger(user.getRole());
        List<Poll> lsPoll = PollCache.getInstance().getPoll(viewRole);
        if (lsPoll == null){
            lsPoll = userService.getPollBeforeEnd(day, viewRole);
            PollCache.getInstance().setPollCache(lsPoll);
            lsPoll = PollCache.getInstance().getPoll(viewRole);
        }


        if (lsPoll.size() < 1){
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp");
            dispatcher.forward(request, response);
            return;
        }


        request.setAttribute("lsPoll", lsPoll);


//        Kiểm tra user đã vote hay chưa
        if (user != null){
            userService.getVoteOptionByUserId(lsPoll,user.getId());
        }


//        Get comment
        userService.getCommentByPollId(lsPoll);


        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp");
        dispatcher.forward(request, response);

    }
}
