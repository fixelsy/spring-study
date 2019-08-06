package com.study.springboot.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

//Error를 handling 할 수 있는 클래스(custom)
@Configuration
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
										HttpServletResponse response,
										AuthenticationException exception) throws IOException, ServletException {
		String loginId = request.getParameter("j_username");
		String errorMsg = "";

		if (exception instanceof BadCredentialsException) {
			loginFailureCount(loginId);
			errorMsg = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
		} else if (exception instanceof InternalAuthenticationServiceException) {
			loginFailureCount(loginId);
			errorMsg = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
		} else if (exception instanceof DisabledException) {
			errorMsg = "계정이 비활성화 되었습니다. 관리자에게 문의하세요.";
		} else if (exception instanceof CredentialsExpiredException) {
			errorMsg = "비밀번호 유효기간이 만료 되었습니다. 관리자에게 문의하세요.";
		}

		request.setAttribute("username", loginId);
		request.setAttribute("error_message", errorMsg);

		request.getRequestDispatcher("/sChkLoginForm?error=true").forward(request, response);
	}

	//비밀번호 3회 이상 틀릴 시 계정 잠금 처리(예시)
	public void loginFailureCount(String loginId) {
		/*
		userDao.countFailure(username);					//틀린 횟수 업데이트
		int cnt = userDao.checkFailureCount(username); 	//틀린 횟수 조회
		if (cnt == 3) {
			userDao.disabledUsername(username);			//계정 잠금 처리
		}
		*/
	}
}
