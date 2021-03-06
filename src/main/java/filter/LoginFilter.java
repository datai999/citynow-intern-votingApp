package filter;

import controller.session_and_cookie.UserSession;
import model.dto.user.UserAccount;

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

        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");

        HttpServletRequest req = (HttpServletRequest) servletRequest;


        HttpSession session = req.getSession();
        UserAccount userInSession = UserSession.getUserLoginSuccess(session);

        if (userInSession != null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String path = req.getRequestURI();

        if (path.matches("/|/login/?|/register/?|/home/?")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpServletResponse rep = (HttpServletResponse) servletResponse;
        rep.sendRedirect(req.getContextPath() + "/login");

    }

    @Override
    public void destroy() {

    }
}
