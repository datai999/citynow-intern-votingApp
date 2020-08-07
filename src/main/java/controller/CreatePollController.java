package controller;

import controller.session_and_cookie.UserSession;
import model.dao.IAdminService;
import model.dtO.poll.IPollBuilder;
import model.dtO.poll.Poll;
import model.dtO.poll.PollBuilder;
import model.dtO.user.UserAccount;
import model.dao.impl.AdminServiceImpl;
import model.dtO.user.UserRole;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@WebServlet(urlPatterns = { "/create" })
public class CreatePollController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    Logger _logger;
    IAdminService adminService;

    public CreatePollController() {
        super();
        adminService = new AdminServiceImpl();
        _logger = Logger.getLogger(this.getClass().getName());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/create.jsp");

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        String title = request.getParameter("title");
        String question = request.getParameter("question");
        String[] options = request.getParameterValues("options");
        String[] viewRole = request.getParameterValues("viewRole");
        String[] voteRole = request.getParameterValues("voteRole");
        String startTime = request.getParameter("startTime");
        String deadline = request.getParameter("deadline");

        HttpSession session = request.getSession();
        UserAccount userInSession = UserSession.getUserLoginSuccess(session);
        int userId = userInSession.getId();

        IPollBuilder builder = new PollBuilder().buildBase(userId, convert(deadline), title, question, options);
        builder.buildViewRole(UserRole.fromInteger(Integer.parseInt(viewRole[0])));
        builder.buildVoteRole(UserRole.fromInteger(Integer.parseInt(voteRole[0])));
        builder.buildTimeStart(convert(startTime));
        Poll poll = builder.build();

        boolean isSuccess = adminService.createPoll(poll);
        if (isSuccess){
            response.sendRedirect(request.getContextPath() + "/home");
        }
        else{
            _logger.info("Create question fail");
            doGet(request,response);
        }
    }

    int convert(String strDeadline){

        strDeadline = strDeadline.substring(0,10) + "+" + strDeadline.substring(11);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd+HH:mm");

        Date date = null;
        try {
            date = dateFormat.parse(strDeadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assert date != null;
        return (int) (date.getTime()/1000);
    }
}
