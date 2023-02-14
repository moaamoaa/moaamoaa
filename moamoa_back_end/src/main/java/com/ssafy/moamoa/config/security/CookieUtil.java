package com.ssafy.moamoa.config.security;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

@Service
public class CookieUtil {

    public ResponseCookie createCookie(String cookieName, String value) {
        return ResponseCookie.from(cookieName,value)
            .path("/")
            .sameSite("none")
            .httpOnly(true)
            .secure(false)
            .domain("")
            .build();
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
