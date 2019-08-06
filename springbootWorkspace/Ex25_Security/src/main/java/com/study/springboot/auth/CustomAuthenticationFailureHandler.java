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

//Error�� handling �� �� �ִ� Ŭ����(custom)
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
			errorMsg = "���̵� ��й�ȣ�� ���� �ʽ��ϴ�. �ٽ� Ȯ�����ּ���.";
		} else if (exception instanceof InternalAuthenticationServiceException) {
			loginFailureCount(loginId);
			errorMsg = "���̵� ��й�ȣ�� ���� �ʽ��ϴ�. �ٽ� Ȯ�����ּ���.";
		} else if (exception instanceof DisabledException) {
			errorMsg = "������ ��Ȱ��ȭ �Ǿ����ϴ�. �����ڿ��� �����ϼ���.";
		} else if (exception instanceof CredentialsExpiredException) {
			errorMsg = "��й�ȣ ��ȿ�Ⱓ�� ���� �Ǿ����ϴ�. �����ڿ��� �����ϼ���.";
		}

		request.setAttribute("username", loginId);
		request.setAttribute("error_message", errorMsg);

		request.getRequestDispatcher("/sChkLoginForm?error=true").forward(request, response);
	}

	//��й�ȣ 3ȸ �̻� Ʋ�� �� ���� ��� ó��(����)
	public void loginFailureCount(String loginId) {
		/*
		userDao.countFailure(username);					//Ʋ�� Ƚ�� ������Ʈ
		int cnt = userDao.checkFailureCount(username); 	//Ʋ�� Ƚ�� ��ȸ
		if (cnt == 3) {
			userDao.disabledUsername(username);			//���� ��� ó��
		}
		*/
	}
}
