package filter;

import controller.session.Session;
import model.DTO.UserAccount;

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

        String path = req.getRequestURI();
//        System.out.println(path);

        if (path.matches("/|/login/?|/register/?")){
//            _logger.info("chain by /login/register");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }


        HttpSession session = req.getSession();
        UserAccount userInSession = Session.getUserLoginSuccess(session);

        if (userInSession != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else{
            HttpServletResponse rep = (HttpServletResponse) servletResponse;
            rep.sendRedirect(req.getContextPath() + "/login");
        }

    }

    @Override
    public void destroy() {

    }
}
