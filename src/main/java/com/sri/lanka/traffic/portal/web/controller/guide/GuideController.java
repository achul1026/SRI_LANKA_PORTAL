package com.sri.lanka.traffic.portal.web.controller.guide;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import groovyjarjarpicocli.CommandLine.Model;

@Controller
@RequestMapping("/guide")
public class GuideController {

	/**
	  * @Method Name : guideNoticeListPage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 이용안내 공지사항 목록 조회 페이지
	  * @param model
	  * @return
	  */
	@GetMapping("/notice")
	public String guideNoticeListPage(Model model) {
		return "views/guide/guideNoticeList";
	}
	
	/**
	  * @Method Name : guideNoticeDetailPage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 이용안내 공지사항 상세 페이지
	  * @param model
	  * @return
	  */
	@GetMapping("/notice/detail")
	public String guideNoticeDetailPage(Model model) {
		return "views/guide/guideNoticeDetail";
	}
	
	/**
	  * @Method Name : guideFaqPage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 이용안내 FAQ 페이지
	  * @param model
	  * @return
	  */
	@GetMapping("/faq")
	public String guideFaqPage(Model model) {
		return "views/guide/guideFAQ";
	}
}
