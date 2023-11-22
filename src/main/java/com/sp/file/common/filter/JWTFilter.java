package com.sp.file.common.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sp.file.common.provider.JWTProvider;

import lombok.RequiredArgsConstructor;

public class JWTFilter extends OncePerRequestFilter{
	private JWTProvider jwtProvider;
	
	public JWTFilter(JWTProvider jwtProvider) {
		this.jwtProvider =jwtProvider;
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String authJwt=request.getHeader("Authorization");
		authJwt = authJwt.replace("Bearer", "");
		if(!jwtProvider.validateJWT(authJwt)) {
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			Map<String,String> res =new HashMap<>();
			res.put("msg", "권한이 없음");
			ObjectMapper om =new ObjectMapper();
		 response.getWriter().print(om.writeValueAsString(res));
		
			return;
		}
		filterChain.doFilter(request, response);
	
		}
	}
 

