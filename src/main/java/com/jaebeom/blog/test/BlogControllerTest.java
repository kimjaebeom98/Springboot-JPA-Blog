 package com.jaebeom.blog.test;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaebeom.blog.config.auth.PrincipalDetail;

// 스프링이 com.jaebeom.blog 패키지 이하를 스캔해서 모든 파일을 메모리에 new하는 것이 아니라
// 특정 어노테이션이 붙어있는 클래스 파일들을 new 해서 (IoC) 스프링 컨테이너에 관리 해 줌
@RestController
public class BlogControllerTest {
	
	@GetMapping("/test/hello")
	public String hello(@AuthenticationPrincipal PrincipalDetail principal) {
		System.out.println("로그인 사용자 아이디 : " + principal.getUsername());
		return "<h1>hello spring boot </h1>";
	}
}
