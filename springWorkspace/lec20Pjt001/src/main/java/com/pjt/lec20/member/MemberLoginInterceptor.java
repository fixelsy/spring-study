package com.pjt.lec20.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MemberLoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("HandlerInterceptorAdapter.preHandle() 호출" + request.getServletPath());

		HttpSession session = request.getSession(false);
		//세션이 있는 경우
		if (session != null) {
			Object obj = session.getAttribute("member");
			if(obj != null) {
				return true;
			}
		}

		//세션이 없는 경우 Redirect 요청
		response.sendRedirect(request.getContextPath() + "/");
		return false;

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("HandlerInterceptorAdapter.postHandle() 호출");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("HandlerInterceptorAdapter.afterCompletion() 호출");
		super.afterCompletion(request, response, handler, ex);
	}
}
