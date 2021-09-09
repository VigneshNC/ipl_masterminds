package com.masterminds.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.masterminds.entity.UserInfo;
import com.masterminds.entity.UserSession;
import com.masterminds.service.IplService;

//@Component
public class IplInterceptor implements HandlerInterceptor {
	
	@Autowired
	IplService iplService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("pre handle");
		System.out.println("api: " + request.getServletPath());
		String apiName = request.getServletPath();
		System.out.println("session: " + request.getSession(false));
		if (request.getSession(false) != null) {
			System.out.println("session id: " + request.getSession(false).getId());
			UserSession existingSession = iplService.getUserSessionBySessionId(request.getSession(false).getId());
			if (existingSession != null) {
				if (apiName != null && !apiName.isEmpty()) {
					if ("/".equals(apiName) || "/ipl".equals(apiName) || "/ipl/logout".equals(apiName)) {
						if (request.getSession(false) != null) {
							existingSession.setOnline(false);
							iplService.saveOrUpdate(existingSession);
							
							Object userIdObj = request.getSession(false).getAttribute("userId");
							if (userIdObj != null) {
								Long userId = (Long) userIdObj;
								UserInfo userInfo = iplService.getById(userId);
								System.out.println("online: " + userInfo.getOnline());
								userInfo.setOnline(false);
								iplService.saveOrUpdate(userInfo);
								request.getSession(false).setAttribute("userId", null);
							}
							
							request.getSession(false).invalidate();
						}
					} else if("/ipl/authenticate".equals(apiName)) {
						if (request.getSession(false) != null) {
							existingSession.setOnline(false);
							iplService.saveOrUpdate(existingSession);
							request.getSession(false).invalidate();
						}
						request.getSession(true);
						UserSession newSession = new UserSession();
						newSession.setSessionId(request.getSession(false).getId());
						newSession.setOnline(true);
						iplService.saveOrUpdate(newSession);
					} else {
						System.out.println("other");
						if (!existingSession.isOnline() || !request.getSession(false).getId().equals(existingSession.getSessionId())) {
							System.out.println("not valid");
							existingSession.setOnline(false);
							iplService.saveOrUpdate(existingSession);
						}
					}
				}
			} else {
				if("/ipl/authenticate".equals(apiName)) {
					if (request.getSession(false) != null) {
						request.getSession(false).invalidate();
					}
					request.getSession(true);
					UserSession newSession = new UserSession();
					newSession.setSessionId(request.getSession(false).getId());
					newSession.setOnline(true);
					iplService.saveOrUpdate(newSession);
				}
			}
		} else {
			System.out.println("session is null");
			if (!"/".equals(apiName) && !"/ipl".equals(apiName) && !"/ipl/logout".equals(apiName)) {
				response.sendRedirect("/");
			}
		}
		return true;
	}

	
	
}
