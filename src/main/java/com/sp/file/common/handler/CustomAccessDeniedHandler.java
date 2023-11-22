package com.sp.file.common.handler;

import java.beans.JavaBean;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler{
 private final ObjectMapper om;
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String authJwt=request.getHeader("Authorization");
		authJwt = authJwt.replace("Bearer", "");
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			Map<String,String> res =new HashMap<>();
			res.put("msg", "권한이 없음");
			ObjectMapper om =new ObjectMapper();
		 response.getWriter().print(om.writeValueAsString(res));
	
	}
	public static void main(String[] args) {
		log.info("status code:{}",HttpStatus.FORBIDDEN);
	}

}
