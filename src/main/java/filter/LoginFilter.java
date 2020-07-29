package filter;

import controller.session_and_cookie.UserCookie;
import controller.session_and_cookie.UserSession;
import model.service.IRootService;
import model.service.dao.root.UpdateRoleDao;
import model.dto.UserAccount;
import model.service.impl.RootServiceImpl;

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
//            session.setAttribute("COOKIE_CHECKED", "CHECKED");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // TODO: 7/29/2020 Cookie
//        String checked = (String) session.getAttribute("COOKIE_CHECKED");
//        if (checked == null) {
//            String userName = UserCookie.getUserNameInCookie(req);
//            IRootService service = new RootServiceImpl();
//            UserAccount user = service.getUserByUsername(userName);
//            if (user != null){
//                UserSession.storeLoginSuccess(session, user);
//                session.setAttribute("COOKIE_CHECKED", "CHECKED");
//                filterChain.doFilter(servletRequest, servletResponse);
//            }
//        }

        String path = req.getRequestURI();

        if (path.matches("/|/login/?|/register/?")){
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
