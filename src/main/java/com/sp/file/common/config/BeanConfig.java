package com.sp.file.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfig {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
/*모든 Spring Security의 비밀번호 Encoder는 위 PasswordEncoder 인터페이스를 구현한다.

이 인터페이스에는 평문인 비밀번호를 암호화하는 encode(),

평문 비밀번호를 인코딩(암호화)된 비밀번호와 비교하는 matches()가 존재한다 */