package controller;

import cache.IPollCache;
import cache.ITopPollCache;
import cache.impl.PollCacheImpl;
import cache.impl.TopPollCacheImpl;
import controller.session_and_cookie.UserSession;
import model.dto.poll.Poll;
import model.dto.user.UserAccount;
import model.dao.IUserService;
import model.dao.impl.UserServiceImpl;
import model.dto.user.UserRole;

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
    ITopPollCache topPollCache;
    IPollCache pollCache;
    int day = 3;

    public HomeController() {
        super();
        userService = new UserServiceImpl();
        topPollCache = TopPollCacheImpl.getInstance();
        pollCache = PollCacheImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        int timeNow = (int) (System.currentTimeMillis()/1000);

        //                Get top vote
        List<Poll> lsTopPoll = topPollCache.getTopPoll();
        if (lsTopPoll == null){
            lsTopPoll = userService.getTopVote(timeNow - day*24*60*60, timeNow);
            topPollCache.setTopPollCache(lsTopPoll);
        }
        request.setAttribute("lsTopPoll", lsTopPoll);


        UserAccount user = UserSession.getUserLoginSuccess(request.getSession());
        request.setAttribute("user", user);


//        Get poll
        UserRole viewRole = UserRole.GUEST;
        if (user != null)
            viewRole = UserRole.fromInteger(user.getRole());

        List<Poll> lsPoll = pollCache.getPoll(viewRole);
        if (lsPoll == null){
            lsPoll = userService.getPollBeforeEnd(day, viewRole);
            userService.getCommentByPollId(lsPoll);
            pollCache.setPollCache(lsPoll);
            lsPoll = pollCache.getPoll(viewRole);
        }


        if (lsPoll == null || lsPoll.size() < 1){
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp");
            dispatcher.forward(request, response);
            return;
        }



//        Kiểm tra user vote
        if (user != null){
            userService.getVoteOptionByUserId(lsPoll,user.getId());
        }




        request.setAttribute("lsPoll", lsPoll);

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp");
        dispatcher.forward(request, response);

    }
}
