package controller.session_and_cookie;

import model.dtO.user.UserAccount;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCookie {

    static String attName = "cookie";

    public static void storeUserCookie(HttpServletResponse response, UserAccount user) {

        Cookie cookieUserName = new Cookie(attName, user.getUsername());
        cookieUserName.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUserName);
    }

    public static String getUserNameInCookie(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (attName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(attName, null);
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }
}
