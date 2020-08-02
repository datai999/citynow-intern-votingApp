package controller;

import controller.session_and_cookie.UserSession;
import model.dto.comment.Comment;
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
//        lsObj = userService.getAllPoll();
//        size = ((List<Poll>) lsObj.get(0)).size();
//        currentVote = size-1;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        int timeNow = (int) (System.currentTimeMillis()/1000);


        UserAccount user = UserSession.getUserLoginSuccess(request.getSession());
        request.setAttribute("user", user);

        List<Object> lsObj = userService.getPollBeforeEnd(timeNow);
        size = ((List<Poll>) lsObj.get(0)).size();
        if (currentVote < 0)
            currentVote = size-1;

        List<Poll> lsPoll = (List<Poll>) lsObj.get(0);
        if (lsPoll.size() < 1){
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp");
            dispatcher.forward(request, response);
            return;
        }
        Poll currentPoll = lsPoll.get(currentVote);

        List<UserAccount> lsPollUser = (List<UserAccount>) lsObj.get(1);
        UserAccount currentPollUser = lsPollUser.get(currentVote);


        request.setAttribute("currentPoll", currentPoll);
        request.setAttribute("currentPollUser", currentPollUser);


//        Get top vote

        List<Object> lsObj1 = userService.getTopVote(timeNow - 3*24*60*60, timeNow);
        request.setAttribute("lsTopPoll", lsObj1.get(0));
        request.setAttribute("lsTopPollUser", lsObj1.get(1));




//        Kiểm tra user đã vote hay chưa
        if (user != null){
            List<Object> lsObj2 = userService.getVoteByUserId(timeNow,lsPoll.get(0).getId(),user.getId());
            List<Integer> lsVotedPoll = (List<Integer>) lsObj2.get(0);
            List<Integer> lsVotedOptionPoll = (List<Integer>) lsObj2.get(1);

            boolean voted = false;
            int votedOptionId = 0;
            for (int i = 0; i<lsVotedPoll.size(); i++){
                if (currentPoll.getId() == lsVotedPoll.get(i)){
                    voted = true;
                    votedOptionId = lsVotedOptionPoll.get(i);
                    break;
                }
            }


            request.setAttribute("voted", voted);
            request.setAttribute("votedOptionId", votedOptionId);
        }


//        Get comment
        List<Comment> lsComment =  userService.getCommentByPollId(currentPoll.getId());
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
