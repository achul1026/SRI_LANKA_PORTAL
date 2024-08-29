package com.sri.lanka.traffic.portal.web.controller.about;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import groovyjarjarpicocli.CommandLine.Model;

@Controller
@RequestMapping("/about")
public class AboutController {
	
	/**
	  * @Method Name : organizationPage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 기관소개 조직도 페이지
	  * @param model
	  * @return
	  */
	@GetMapping("/organization")
	public String organizationPage(Model model) {
		//TODO 삭제
		return "views/about/aboutOrganization";
	}
	
	/**
	  * @Method Name : relatedSitePage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 기관소개 관련사이트 페이지
	  * @param model
	  * @return
	  */
	@GetMapping("/related-site")
	public String relatedSitePage(Model model) {
		return "views/about/aboutRelatedSite";
	}
	
	/**
	  * @Method Name : directionPage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 기관소개 오시는 길 페이지
	  * @param model
	  * @return
	  */
	@GetMapping("/direction")
	public String directionPage(Model model) {
		return "views/about/aboutDirection";
	}

}
