package com.sri.lanka.traffic.portal.web.controller.trfcDatabase;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sri.lanka.traffic.portal.common.dto.stats.StatsSearchDTO;
import com.sri.lanka.traffic.portal.common.enums.code.FacilityTypeCd;
import com.sri.lanka.traffic.portal.common.enums.code.GroupCode;
import com.sri.lanka.traffic.portal.common.repository.TcCdInfoRepository;
import com.sri.lanka.traffic.portal.common.repository.TsFixedDdRepository;
import com.sri.lanka.traffic.portal.common.repository.TsMvmneqDdRepository;
import com.sri.lanka.traffic.portal.common.repository.TsVdsDdRepository;
import com.sri.lanka.traffic.portal.common.util.CommonUtils;


@Controller
@RequestMapping("/trfcstats")
public class TrfcStatsController {
	
	@Autowired
	TsVdsDdRepository tsVdsDdRepository;
	
	@Autowired
	TsFixedDdRepository tsFixedDdRepository;
	
	@Autowired
	TsMvmneqDdRepository tsMvmneqDdRepository;
	
	@Autowired
	TcCdInfoRepository tcCdInfoRepository;
	
	/**
	  * @Method Name : trfcVelocityStatsPage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 교통DB 속도 통계 페이지
	  * @return
	  */
	@GetMapping("/velocity")
	public String trfcVelocityStatsPage(Model model,StatsSearchDTO statsSearchDTO) {
		List<Map<String,String>> facilityList = tcCdInfoRepository.getCdListByGrpcdId(GroupCode.FACILITY_TYPE_CD.getCode(),LocaleContextHolder.getLocale().toString());
		model.addAttribute("facilityList", facilityList);
		if (!CommonUtils.isNull(statsSearchDTO.getDate()) && !CommonUtils.isNull(statsSearchDTO.getSearchType())) {
			String searchDate = statsSearchDTO.getDate().replace("-", "");
		    model.addAttribute("statsSearchDTO", statsSearchDTO);
		    Map<String, String> velocityStats = null; 
		    if (statsSearchDTO.getSearchType().equals(FacilityTypeCd.VDS.getCode())) {
		    	velocityStats = tsVdsDdRepository.getVdsAvgSpeed(searchDate);
			} else if(statsSearchDTO.getSearchType().equals(FacilityTypeCd.METRO_COUNT.getCode())) {
				velocityStats = tsFixedDdRepository.getFixedAvgSpeed(searchDate);
			} else if(statsSearchDTO.getSearchType().equals(FacilityTypeCd.PORTABLE_METRO_COUNT.getCode())) {
				velocityStats = tsMvmneqDdRepository.getRemovableAvgSpeed(searchDate);
			}
			model.addAttribute("velocityStats", velocityStats);
		}
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
	public String trfcTrafficVolumeStatsPage(Model model,StatsSearchDTO statsSearchDTO) {
		List<Map<String,String>> facilityList = tcCdInfoRepository.getCdListByGrpcdId(GroupCode.FACILITY_TYPE_CD.getCode(),LocaleContextHolder.getLocale().toString());
		model.addAttribute("facilityList", facilityList);
		if (!CommonUtils.isNull(statsSearchDTO.getStartDate()) 
				&& !CommonUtils.isNull(statsSearchDTO.getEndDate()) 
				&& !CommonUtils.isNull(statsSearchDTO.getSearchType())) {
			
			Map<String, String> velocityStats = null; 
			if (statsSearchDTO.getSearchType().equals(FacilityTypeCd.VDS.getCode())) {
		    	velocityStats =  tsVdsDdRepository.getVdsVolumeStats(statsSearchDTO.getStartDate(), statsSearchDTO.getEndDate());
			} else if(statsSearchDTO.getSearchType().equals(FacilityTypeCd.METRO_COUNT.getCode())) {
				velocityStats = tsFixedDdRepository.getFixedVolumeStats(statsSearchDTO.getStartDate(), statsSearchDTO.getEndDate());
			} else if(statsSearchDTO.getSearchType().equals(FacilityTypeCd.PORTABLE_METRO_COUNT.getCode())) {
				velocityStats = tsMvmneqDdRepository.getRemovableVolumeStats(statsSearchDTO.getStartDate(), statsSearchDTO.getEndDate());
			}
			
		    model.addAttribute("startDt", statsSearchDTO.getStartDate());
		    model.addAttribute("endDt", statsSearchDTO.getEndDate());
			model.addAttribute("velocityStats",velocityStats);
		}
		return "views/trfcStats/trfcTrafficVolumeStats";
	}
	
	@GetMapping("/congestionrate")
	public String trfcCongestionRateStatsPage(Model model) {
		return "views/trfcStats/trfcCongestionRateStats";
	}

}
