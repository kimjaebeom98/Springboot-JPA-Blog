package com.jaebeom.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.jaebeom.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {

	@GetMapping({"", "/"})
	public String index() {
		return "index";
	}
	
	// USER 권한 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
