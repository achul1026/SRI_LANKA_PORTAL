package com.sri.lanka.traffic.portal.web.controller.trfcInfo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import groovyjarjarpicocli.CommandLine.Model;

@Controller
@RequestMapping("/trfcinfo")
public class TrfcInfoMngController {

	/**
	  * @Method Name : trfcNewsListPage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 교통정보 교통소식 목록 조회 페이지
	  * @param model
	  * @return
	  */
	@GetMapping("/trfcnews")
	public String trfcNewsListPage(Model model) {
		return "views/trfcInfo/trfcNewsList";
	}
	
	/**
	  * @Method Name : trfcNewsDetailPage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 교통정보 교통소식 상세 페이지
	  * @return
	  */
	@GetMapping("/trfcnews/detail")
	public String trfcNewsDetailPage(Model model) {
		return "views/trfcInfo/trfcNewsDetail";
	}
	
	/**
	  * @Method Name : trfcResourcesListPage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 교통정보 자료실 목록 조회 페이지
	  * @return
	  */
	@GetMapping("/resources")
	public String trfcResourcesListPage(Model model) {
		return "views/trfcInfo/trfcResourcesList";
	}
	
	/**
	  * @Method Name : trfcNewsDetailPage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 교통정보 자료실 상세 페이지
	  * @return
	  */
	@GetMapping("/resources/detail")
	public String trfcResourcesDetailPage(Model model) {
		return "views/trfcInfo/trfcResourcesDetail";
	}
}
