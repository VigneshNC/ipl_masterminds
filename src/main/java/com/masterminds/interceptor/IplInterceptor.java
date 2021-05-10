package com.masterminds.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class IplInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("pre handle");
		System.out.println("api: " + request.getServletPath());
		String apiName = request.getServletPath();
		System.out.println("session: " + request.getSession(false));
		if (request.getSession(false) != null) {
			System.out.println("session id: " + request.getSession(false).getId());
		}
		if (apiName != null && !apiName.isEmpty()) {
			if ("/".equals(apiName) || "/ipl".equals(apiName) || "/ipl/login".equals(apiName)) {
				if (request.getSession(false) != null) {
					request.getSession(false).invalidate();
				}
			} else if("/ipl/authenticate".equals(apiName)) {
				if (request.getSession(false) != null) {
					request.getSession(false).invalidate();
				}
				request.getSession(true);
			}
		}
		return true;
	}

	
	
}
