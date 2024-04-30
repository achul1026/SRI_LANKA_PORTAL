package com.sri.lanka.traffic.portal.web.controller.trfcDatabase;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import groovyjarjarpicocli.CommandLine.Model;

@Controller
@RequestMapping("/trfcstats")
public class TrfcStatsController {
	
	/**
	  * @Method Name : trfcVelocityStatsPage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 교통DB 속도 통계 페이지
	  * @return
	  */
	@GetMapping("/velocity")
	public String trfcVelocityStatsPage(Model model) {
		return "views/trfcStats/trfcVelocityStats";
	}
	
	/**
	  * @Method Name : trfcTrafficVolumeStatsPage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 교통DB 교통량 통계 페이지
	  * @return
	  */
	@GetMapping("/trafficvolume")
	public String trfcTrafficVolumeStatsPage(Model model) {
		return "views/trfcStats/trfcTrafficVolumeStats";
	}
	
	@GetMapping("/congestionrate")
	public String trfcCongestionRateStatsPage(Model model) {
		return "views/trfcStats/trfcCongestionRateStats";
	}

}
