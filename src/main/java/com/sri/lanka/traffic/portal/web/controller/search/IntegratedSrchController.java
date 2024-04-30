package com.sri.lanka.traffic.portal.web.controller.search;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import groovyjarjarpicocli.CommandLine.Model;

@Controller
@RequestMapping("/search")
public class IntegratedSrchController {

	/**
	  * @Method Name : integratedSrchListPage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 통합검색 통합검색 목록 조회 페이지
	  * @param model
	  * @return
	  */
	@GetMapping("/integrate")
	public String integratedSrchListPage(Model model) {
		return "views/search/integratedSrchList";
	}
	
	/**
	  * @Method Name : integratedSrchNewsListPage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 통합검색 교통소식 목록 조회 페이지
	  * @param model
	  * @return
	  */
	@GetMapping("/news")
	public String integratedSrchNewsListPage(Model model) {
		return "views/search/integratedSrchNewsList";
	}
	
	/**
	  * @Method Name : integratedSrchResourcesListPage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 통합검색 자료실 목록 조회 페이지
	  * @param model
	  * @return
	  */
	@GetMapping("/resources")
	public String integratedSrchResourcesListPage(Model model) {
		return "views/search/integratedSrchResourcesList";
	}
	
	/**
	  * @Method Name : integratedSrchNoticeListPage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 통합검색 공지사항 목록 조회 페이지
	  * @param model
	  * @return
	  */
	@GetMapping("/notice")
	public String integratedSrchNoticeListPage(Model model) {
		return "views/search/integratedSrchNoticeList";
	}
}
