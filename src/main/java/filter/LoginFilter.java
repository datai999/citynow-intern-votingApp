package filter;

import model.DAO.LoginAction;
import model.DTO.UserAccount;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = { "/*" })
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;

        String path = req.getRequestURI();

//        System.out.println(path);

        if (path.matches("/|/login/?|/register/?")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }


        HttpSession session = req.getSession();
        UserAccount userInSession = LoginAction.getInstance().getUserLoginSuccess(session);

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
