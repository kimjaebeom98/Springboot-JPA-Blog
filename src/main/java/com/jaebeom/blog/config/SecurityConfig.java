package com.jaebeom.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jaebeom.blog.config.auth.PrincipalDetailService;

@Configuration // IoC
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable() // csrf 토큰 비활성화 
				.authorizeRequests()
					.antMatchers("/", "/css/**", "/images/**", "/js/**", "/auth/**")
					.permitAll()
					.anyRequest()
					.authenticated()
				.and()
					.formLogin()
					.loginPage("/auth/loginForm")
					.loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 진행
					.defaultSuccessUrl("/");
		return http.build();
	}
}
