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
		};//ignoring() 무시하고 실행
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity hs) throws Exception {
		hs.authorizeHttpRequests(req-> //url 요청
			req.antMatchers("/login","/join","/html/login","/html/join","/html/login-fail")
			.permitAll() //특정 URL을 설정하며,여기서 뒤에 붙은 permitAll()은 antMatchers에서 설정한 URL의 접근을 인증없이 허용한다는 뜻이다.
			.antMatchers("/html/root/index").hasRole("ROOT") //어드민만 허용
			.anyRequest()//이 옵션은 모든 리소스가 인증을 해야만 접근이 허용된다는 뜻이다.보통 이 옵션 이전에 다른 설정을 완료하고 나머지에 위 옵션을 적용한다.
			.authenticated() //인증이 된상태
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
		.csrf(csrf->csrf.disable())// 실제 페이지에서가 아닌 위조된 페이지에서 공격을 받은경우 허용된 요청이 맞는지 검사
		.exceptionHandling(ex ->ex.accessDeniedPage("/html/denied")) //권한이 없을 경우 예외처리
		.userDetailsService(userInfoService);
		
		return hs.build();
	}
}