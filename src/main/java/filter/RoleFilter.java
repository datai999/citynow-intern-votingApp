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

@WebFilter(filterName = "RoleFilter", urlPatterns = { "/*" })
public class RoleFilter implements Filter {

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

        if (userInSession.getRole() == UserAccount.ROOT){
//            _logger.info("visit by root");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        boolean hasError = false;

        if (path.matches("/create/?")){
            if (userInSession.getRole() == UserAccount.ADMIN){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            hasError = true;
        }

        if (path.matches("/manager/?")){
//            _logger.info("visit manager");
            if (userInSession.getRole() == UserAccount.ROOT){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            hasError = true;
        }

        if (hasError){
            _logger.info("has error");
            HttpServletResponse rep = (HttpServletResponse) servletResponse;
            rep.sendRedirect(req.getContextPath() + "/home");
        }
        else
            filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
