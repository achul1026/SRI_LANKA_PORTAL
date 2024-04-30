package com.sri.lanka.traffic.portal.web.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import groovyjarjarpicocli.CommandLine.Model;

@Controller
public class LoginController {

	/**
	  * @Method Name : loginPage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 로그인 페이지
	  * @param model
	  * @return
	  */
	@GetMapping("/login")
	public String loginPage(Model model) {
		return "views/login/login";
	}
	
	/**
	  * @Method Name : joinPage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 회원가입 페이지
	  * @param model
	  * @return
	  */
	@GetMapping("/join")
	public String joinPage(Model model) {
		return "views/login/join";
	}
}
