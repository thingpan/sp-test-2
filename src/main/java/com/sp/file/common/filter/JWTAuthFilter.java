package com.sp.file.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sp.file.common.provider.JWTProvider;
import com.sp.file.common.service.UserInfoService;
import com.sp.file.vo.UserInfoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class JWTAuthFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authManager;
	private final UserInfoService userInfoService;
	private final JWTProvider jwtProvider;
	private final ObjectMapper om;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		UserInfoVO login;

		try {
			login = om.readValue(request.getInputStream(), UserInfoVO.class);

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login.getUiId(),
					login.getUiPwd());
			return authManager.authenticate(authToken);
		} catch (Exception e) {
			log.error("login error=>{}", e);
		}
		return null;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		UserInfoVO login = (UserInfoVO) authResult.getPrincipal();
		String token = jwtProvider.generateToken(login);
		response.addHeader("Authorization", "Bearer" + token);
		getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
	}
}
