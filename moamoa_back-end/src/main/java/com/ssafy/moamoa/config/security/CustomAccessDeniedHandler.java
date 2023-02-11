package com.ssafy.moamoa.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.moamoa.exception.ErrorResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	private final ObjectMapper objectMapper;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
		AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setCharacterEncoding("utf-8");
		log.warn("CustomAccessDeniedHandler : 권한이 없는 사용자의 접근");

		String msg = accessDeniedException.getMessage().isEmpty() ? "허용하지 않는 권한에 접근하였습니다" : accessDeniedException.getMessage();
		ErrorResult errorResult = new ErrorResult("403", msg);

		response.setCharacterEncoding("utf-8");
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.getWriter().write(objectMapper.writeValueAsString(errorResult));
	}
}
