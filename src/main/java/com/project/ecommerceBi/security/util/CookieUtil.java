package com.project.ecommerceBi.security.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    public static void create(HttpServletResponse httpServletResponse, String name, String value, Boolean secure, Integer maxAge, String domain) {
        /*Cookie cookie = new Cookie(name, value);
        cookie.setSecure(secure);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        //cookie.setDomain(domain);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);*/
        ResponseCookie responseCookie = ResponseCookie.from(name,value)
                .httpOnly(true).path("/").maxAge(maxAge)
                .sameSite("None").secure(secure).build();
        httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
    }

    public static void clear(HttpServletResponse httpServletResponse, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        cookie.setDomain("ecommerce.test.com");
        httpServletResponse.addCookie(cookie);
    }
}
