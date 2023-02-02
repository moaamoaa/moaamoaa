package com.ssafy.moamoa.config.security;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class CookieUtil {

    public Cookie createCookie(String cookieName, String value) {
        Cookie cookie = new Cookie(cookieName, value);
//        cookie.setHttpOnly(true);
        // cookie.setMaxAge(60 * 60);
        // cookie.setPath("/");
        //cookie.setSecure(true);

        return cookie;
    }

    public Cookie getCookie(HttpServletRequest request, String CookieName) {
        final Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(CookieName))
                return cookie;
        }
        return null;
    }
}
