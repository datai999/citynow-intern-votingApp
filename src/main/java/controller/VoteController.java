package controller;

import controller.session_and_cookie.UserSession;
import model.dao.IUserService;
import model.dao.impl.UserServiceImpl;
import model.dto.user.UserAccount;
import model.dto.vote.Vote;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = { "/vote"})
public class VoteController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    IUserService userService;

    public VoteController() {
        super();
        userService = new UserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String[] options = request.getParameterValues("options");
        if (options == null){
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        UserAccount user = UserSession.getUserLoginSuccess(request.getSession());
        if (user == null){
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Vote vote = new Vote(Integer.parseInt(options[0]), user.getId());

        boolean voteSuccess =  userService.vote(vote);

        response.sendRedirect(request.getContextPath() + "/home");
    }
}
