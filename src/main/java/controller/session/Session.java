package controller.session;

import model.DTO.UserAccount;

import javax.servlet.http.HttpSession;

public class Session {

    static String attName = "loginSuccess";

    public static void storeLoginSuccess(HttpSession session, UserAccount user){
        user.setPassword("");
        session.setAttribute(attName, user);
    }

    public static UserAccount getUserLoginSuccess(HttpSession session){
        return (UserAccount) session.getAttribute(attName);
    }

    public static void removeSession(HttpSession session){
        session.removeAttribute(attName);
    }
}
