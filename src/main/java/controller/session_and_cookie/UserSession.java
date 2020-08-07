package controller.session_and_cookie;

import model.dtO.user.UserAccount;

import javax.servlet.http.HttpSession;

public class UserSession {

    static String attName = "loginSuccess";

    public static void storeLoginSuccess(HttpSession session, UserAccount user){
        user.setPassword("");
        session.setAttribute(attName, user);
    }

    public static UserAccount getUserLoginSuccess(HttpSession session){
        if (session == null) return null;
        return (UserAccount) session.getAttribute(attName);
    }

    public static void removeSession(HttpSession session){
        session.removeAttribute(attName);
    }
}
