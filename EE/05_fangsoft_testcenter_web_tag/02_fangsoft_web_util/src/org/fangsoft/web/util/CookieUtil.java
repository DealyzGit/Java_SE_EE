package org.fangsoft.web.util;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtil {
    public static Cookie getCookie(HttpServletRequest request, String cookieName){
        Cookie[] cookies=request.getCookies();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals(cookieName))return cookie;
            }
        }
        return null;
    }
    public static String getCookieValue(HttpServletRequest request,String cookieName){
        Cookie cookie=getCookie(request,cookieName);
        return cookie==null?"":cookie.getValue();
    }
    public static void killCookie(HttpServletRequest request, HttpServletResponse response, String cookieName){
        Cookie cookie=CookieUtil.getCookie(request, cookieName);
        if(cookie!=null){
            cookie.setMaxAge(0);//浏览器立即删除此Cookie
            response.addCookie(cookie);
        }
    }



}
