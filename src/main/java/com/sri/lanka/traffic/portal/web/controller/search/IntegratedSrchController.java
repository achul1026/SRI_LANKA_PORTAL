package com.sri.lanka.traffic.portal.web.controller.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sri.lanka.traffic.portal.common.dto.common.SearchDTO;
import com.sri.lanka.traffic.portal.common.enums.code.BbsTypeCd;
import com.sri.lanka.traffic.portal.common.enums.code.GroupCode;
import com.sri.lanka.traffic.portal.common.repository.TlBbsInfoRepository;
import com.sri.lanka.traffic.portal.common.util.CommonUtils;


@Controller
@RequestMapping("/search")
public class IntegratedSrchController {
	
	@Autowired
	TlBbsInfoRepository tlBbsInfoRepository;

	/**
	  * @Method Name : integratedSrchListPage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 통합검색 통합검색 목록 조회 페이지
	  * @param model
	  * @return
	  */
	@GetMapping
	public String integratedSrchListPage(Model model, SearchDTO searchDTO) {
		String lang = LocaleContextHolder.getLocale().toString();
		model.addAttribute("boardList", tlBbsInfoRepository.getBoardListNotAlarm(GroupCode.BBS_TYPE_CD.getCode(),BbsTypeCd.ALARM.getCode(),lang));
		model.addAttribute("searchDTO", searchDTO);
		return "views/search/integratedSrchList";
	}
	
	@GetMapping("/{typeCd}")
	public String getBbsListByType(Model model, @PathVariable(name = "typeCd") String typeCd, SearchDTO searchDTO) {
		String typeName = null;
		if (!CommonUtils.isNull(typeCd) && !typeCd.equals("all")) {
			typeName= BbsTypeCd.getEnums(typeCd).getName();
		}
		
		if (!CommonUtils.isNull(typeCd) && typeCd.equals("all")) {
			model.addAttribute("newsList", tlBbsInfoRepository.findTop5ByBbsType(BbsTypeCd.NEWS.getCode(), searchDTO.getSearchContent()));
			model.addAttribute("resourcesList", tlBbsInfoRepository.findTop5ByBbsType(BbsTypeCd.RESOURCES.getCode(), searchDTO.getSearchContent()));
			model.addAttribute("noticeList", tlBbsInfoRepository.findTop5ByBbsType(BbsTypeCd.NOTICE.getCode(), searchDTO.getSearchContent()));
		} else {
			model.addAttribute(typeName+"List", tlBbsInfoRepository.findTop5ByBbsType(typeCd, searchDTO.getSearchContent()));
		}
		
		model.addAttribute("type", typeCd);
		return "views/search/searchList";
	}
	
	/**
	  * @Method Name : integratedSrchNewsListPage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 통합검색 교통소식 목록 조회 페이지
	  * @param model
	  * @return
	  */
//	@GetMapping("/news")
//	public String integratedSrchNewsListPage(Model model, SearchDTO searchDTO) {
//		model.addAttribute("newsList", tlBbsInfoRepository.findTop5ByBbsType(BbsTypeCd.NEWS.getCode(), searchDTO.getSearchContent()));
//		return "views/search/integratedSrchNewsList";
//	}
	
	/**
	  * @Method Name : integratedSrchResourcesListPage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 통합검색 자료실 목록 조회 페이지
	  * @param model
	  * @return
	  */
//	@GetMapping("/resources")
//	public String integratedSrchResourcesListPage(Model model, SearchDTO searchDTO, PagingDTO pagingDTO) {
//		model.addAttribute("bbsList", tlBbsInfoRepository.getBbsList(BbsTypeCd.NEWS.getCode(), searchDTO, pagingDTO));
//		return "views/search/integratedSrchResourcesList";
//	}
	
	/**
	  * @Method Name : integratedSrchNoticeListPage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 통합검색 공지사항 목록 조회 페이지
	  * @param model
	  * @return
	  */
//	@GetMapping("/notice")
//	public String integratedSrchNoticeListPage(Model model, SearchDTO searchDTO, PagingDTO pagingDTO) {
//		model.addAttribute("bbsList", tlBbsInfoRepository.getBbsList(BbsTypeCd.NEWS.getCode(), searchDTO, pagingDTO));
//		return "views/search/integratedSrchNoticeList";
//	}
}
