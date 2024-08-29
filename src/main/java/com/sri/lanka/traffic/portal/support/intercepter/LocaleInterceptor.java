package com.sri.lanka.traffic.portal.support.intercepter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LocaleInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    	//쿠키값 비교 다국어 처리
		Cookie[] cookies = request.getCookies();
		String lang = "eng";
		if(cookies == null) {
			request.setAttribute("lang", lang);
		}else {
			for (Cookie cookie : cookies) {
				if ("lang".equals(cookie.getName())) {
					lang = cookie.getValue();
					request.setAttribute("lang", lang);
				}
			}
		}
    }
}