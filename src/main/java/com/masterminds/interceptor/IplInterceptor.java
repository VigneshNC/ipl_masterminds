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
		if (apiName != null && !apiName.isEmpty()) {
			if ("/".equals(apiName) || "/ipl".equals(apiName)) {
				if (request.getSession(false) != null) {
					request.getSession(false).invalidate();
				}
				request.getSession(true);
			}
		}
		return true;
	}

	
	
}
