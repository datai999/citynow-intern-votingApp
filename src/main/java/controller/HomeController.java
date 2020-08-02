package controller;

import controller.session_and_cookie.UserSession;
import model.dto.comment.CommentPoll;
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
    int currentVote = -1;
    int size = 0;

    public HomeController() {
        super();
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        int timeNow = (int) (System.currentTimeMillis()/1000);


        UserAccount user = UserSession.getUserLoginSuccess(request.getSession());
        request.setAttribute("user", user);


        //        Get top vote
        List<Poll> lsTopPoll = userService.getTopVote(timeNow - 3*24*60*60, timeNow);
        request.setAttribute("lsTopPoll", lsTopPoll);



        List<Poll> lsPoll = userService.getPollBeforeEnd(timeNow - 24*60*60);
        size = lsPoll.size();

        if (size < 1){
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp");
            dispatcher.forward(request, response);
            return;
        }



        if (currentVote < 0)
            currentVote = size-1;

        Poll currentPoll = lsPoll.get(currentVote);
        request.setAttribute("currentPoll", currentPoll);


//        Kiểm tra user đã vote hay chưa
        if (user != null){
            int votedOptionId = userService.getVoteOptionByUserId(currentPoll.getId(),user.getId());
            if (votedOptionId > 0){
                request.setAttribute("voted", true);
                request.setAttribute("votedOptionId", votedOptionId);
            }
        }


//        Get comment
        List<CommentPoll> lsComment =  userService.getCommentByPollId(currentPoll.getId());
        request.setAttribute("lsComment", lsComment);


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
