package com.sp.file.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import com.sp.file.service.UserInfoService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	private final UserInfoService userInfoService;
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return web->{
			web.ignoring()
			.antMatchers("/js/**","/css/**","/imgs/** ");
		};
	}
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity hs) throws Exception {
		hs.authorizeHttpRequests(req->
		req.antMatchers("/login","/join","/html/login","/html/join")
		.permitAll()
		.anyRequest()
		.authenticated()
		)
		.formLogin(f1->f1.loginPage("/html/login")
			.usernameParameter("uiId")
			.passwordParameter("uiPwd")
		);
		return hs.build();
	}
}
