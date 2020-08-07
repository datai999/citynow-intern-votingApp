package controller;

import controller.session_and_cookie.UserSession;
import model.dao.IUserService;
import model.dao.impl.UserServiceImpl;
import model.dto.comment.CommentPoll;
import model.dto.user.UserAccount;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(urlPatterns = { "/comment"})
public class CommentController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    Logger _logger;
    IUserService userService;

    public CommentController() {
        super();
        userService = new UserServiceImpl();
        _logger = Logger.getLogger(this.getClass().getName());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");


        response.sendRedirect(request.getContextPath() + "/comment");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        int pollId = Integer.parseInt(request.getParameter("pollId"));
//        int replyCommentId = Integer.parseInt(request.getParameter("replyCommentId"));
        int replyCommentId = 0;
        String content = request.getParameter("content");

        UserAccount user = UserSession.getUserLoginSuccess(request.getSession());

        CommentPoll comment = new CommentPoll(pollId, user.getId(), replyCommentId, content);
        comment.setCommentator(user);

        boolean isSuccess = userService.comment(comment);

        response.sendRedirect(request.getContextPath() + "/home");
    }
}
