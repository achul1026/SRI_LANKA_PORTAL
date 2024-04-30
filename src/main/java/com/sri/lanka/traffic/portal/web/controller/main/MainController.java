package com.sri.lanka.traffic.portal.web.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import groovyjarjarpicocli.CommandLine.Model;

@Controller
public class MainController {
	
    /**
      * @Method Name : login
      * @작성일 : 2024. 2. 14.
      * @작성자 : SM.KIM
      * @Method 설명 : 메인 페이지
      * @param model
      * @return
      */
    @GetMapping("/main")
    public String login(Model model){
        return "views/main/main";
    }
}
