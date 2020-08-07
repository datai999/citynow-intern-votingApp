package filter;

import controller.session_and_cookie.UserSession;
import model.dtO.user.UserAccount;
import model.dtO.user.UserRole;

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

        HttpSession session = req.getSession();
        UserAccount userInSession = UserSession.getUserLoginSuccess(session);



        if (userInSession != null){
            if (userInSession.getRole() == UserRole.ROOT.value){
    //            _logger.info("visit by root");
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        if (path.matches("/|/login/?|/register/?|/home/?")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }



        boolean hasError = false;
        if (path.matches("/create/?")){
            if (userInSession.getRole() == UserRole.ADMIN.value){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            hasError = true;
        }

        if (path.matches("/manager/?")){
//            _logger.info("visit manager");
            if (userInSession.getRole() == UserRole.ROOT.value){
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
