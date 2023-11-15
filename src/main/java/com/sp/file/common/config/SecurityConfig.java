package com.sp.file.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import com.sp.file.common.service.UserInfoService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	private final UserInfoService userInfoService;

	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return web -> {
			web.ignoring().antMatchers("/js/**", "/css/**", "/imgs/** ");
		};
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity hs) throws Exception {
		hs.authorizeHttpRequests(req->
			req.antMatchers("/login","/join","/html/login","/html/join","/html/login-fail")
			.permitAll()
			.anyRequest()
			.authenticated()
		)
		.formLogin(fl-> 
			fl.loginPage("/html/login")
			.usernameParameter("uiId")
			.passwordParameter("uiPwd")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/",true)
			.failureUrl("/html/login-fail"))
		.logout(logout->
			logout.logoutUrl("/logout")
			.logoutSuccessUrl("/html/login"))
		.csrf(csrf->csrf.disable())
		.userDetailsService(userInfoService);
		
		return hs.build();
	}
}