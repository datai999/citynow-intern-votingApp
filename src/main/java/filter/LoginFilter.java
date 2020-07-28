package filter;

import controller.session_and_cookie.UserCookie;
import controller.session_and_cookie.UserSession;
import model.dao.impl.RootDao;
import model.dto.UserAccount;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

@WebFilter(filterName = "LoginFilter", urlPatterns = { "/*" })
public class LoginFilter implements Filter {

    Logger _logger;

    @Override
    public void init(FilterConfig filterConfig) {
        _logger = Logger.getLogger(this.getClass().getName());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;


        HttpSession session = req.getSession();
        UserAccount userInSession = UserSession.getUserLoginSuccess(session);

        if (userInSession != null) {
            System.out.println("user in session");
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String checked = (String) session.getAttribute("COOKIE_CHECKED");
        if (checked == null) {
            System.out.println("checked == null");
            String userName = UserCookie.getUserNameInCookie(req);
            UserAccount user = RootDao.getInstance().getUserByUsername(userName);
            if (user != null){
                System.out.println("user != null");
                UserSession.storeLoginSuccess(session, user);
                session.setAttribute("COOKIE_CHECKED", "CHECKED");
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }

        String path = req.getRequestURI();

        if (path.matches("/|/login/?|/register/?")){
            System.out.println("pass chan");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        System.out.println("redirect");

        HttpServletResponse rep = (HttpServletResponse) servletResponse;
        rep.sendRedirect(req.getContextPath() + "/login");

    }

    @Override
    public void destroy() {

    }
}
