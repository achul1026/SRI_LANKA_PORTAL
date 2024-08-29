package com.sri.lanka.traffic.portal.web.controller.trfcInfo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sri.lanka.traffic.portal.common.dto.common.PagingDTO;
import com.sri.lanka.traffic.portal.common.dto.common.SearchDTO;
import com.sri.lanka.traffic.portal.common.entity.TlBbsFile;
import com.sri.lanka.traffic.portal.common.entity.TlBbsFileGrp;
import com.sri.lanka.traffic.portal.common.entity.TlBbsInfo;
import com.sri.lanka.traffic.portal.common.enums.code.BbsTypeCd;
import com.sri.lanka.traffic.portal.common.repository.TcFaqMngRepository;
import com.sri.lanka.traffic.portal.common.repository.TlBbsFileGrpRepository;
import com.sri.lanka.traffic.portal.common.repository.TlBbsFileRepository;
import com.sri.lanka.traffic.portal.common.repository.TlBbsInfoRepository;


@Controller
@RequestMapping("/trfcinfo")
public class TrfcInfoMngController {
	
	@Autowired
	TlBbsInfoRepository tlBbsInfoRepository;
	
	@Autowired
	TcFaqMngRepository tcFaqMngRepository;
	
	@Autowired
	TlBbsFileGrpRepository tlBbsFileGrpRepository;
	
	@Autowired
	TlBbsFileRepository tlBbsFileRepository;

	/**
	  * @Method Name : trfcNewsListPage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 교통정보 교통소식 목록 조회 페이지
	  * @param model
	  * @return
	  */
	@GetMapping("/trfcnews")
	public String trfcNewsListPage(Model model, SearchDTO searchDTO, PagingDTO pagingDTO) {
		List<Map<String,String>> bbsList = tlBbsInfoRepository.getBbsList(BbsTypeCd.NEWS.getCode(), searchDTO, pagingDTO);
		long totalCount = tlBbsInfoRepository.getTotalCount(BbsTypeCd.NEWS.getCode(), searchDTO);
		pagingDTO.setTotalCount(totalCount);
		model.addAttribute("bbsList", bbsList);
		model.addAttribute("searchDTO", searchDTO);
		model.addAttribute("pagingDTO", pagingDTO);
		return "views/trfcInfo/trfcNewsList";
	}
	
	/**
	  * @Method Name : trfcNewsDetailPage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 교통정보 교통소식 상세 페이지
	  * @return
	  */
	@GetMapping("/trfcnews/{bbsId}")
	public String trfcNewsDetailPage(Model model, @PathVariable("bbsId") String bbsId) {
		Map<String,String> bbsInfo = tlBbsInfoRepository.getBbsInfo(bbsId, LocaleContextHolder.getLocale().toString());
		
		TlBbsInfo tlBbsInfo = new TlBbsInfo(bbsInfo);
		tlBbsInfoRepository.save(tlBbsInfo);
		
		Optional<TlBbsFileGrp> tlBbsFileGrp = tlBbsFileGrpRepository.findByBbsId(bbsId);
		if (tlBbsFileGrp.isPresent()) {
			List<TlBbsFile> bbsFile = tlBbsFileRepository.findByFilegrpId(tlBbsFileGrp.get().getFilegrpId());
			model.addAttribute("bbsFile", bbsFile);
		}
		
//		model.addAttribute("preBbsInfo", tlBbsInfoRepository.getPreBbs(bbsId,BbsTypeCd.NOTICE.getCode()));
//		model.addAttribute("nextBbsInfo", tlBbsInfoRepository.getNextBbs(bbsId,BbsTypeCd.NOTICE.getCode()));
		model.addAttribute("bbsInfo", bbsInfo);
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
	public String trfcResourcesListPage(Model model, SearchDTO searchDTO, PagingDTO pagingDTO) {
		List<Map<String,String>> bbsList = tlBbsInfoRepository.getBbsList(BbsTypeCd.RESOURCES.getCode(), searchDTO, pagingDTO);
		long totalCount = tlBbsInfoRepository.getTotalCount(BbsTypeCd.RESOURCES.getCode(), searchDTO);
		pagingDTO.setTotalCount(totalCount);
		model.addAttribute("bbsList", bbsList);
		model.addAttribute("searchDTO", searchDTO);
		model.addAttribute("pagingDTO", pagingDTO);
		return "views/trfcInfo/trfcResourcesList";
	}
	
	/**
	  * @Method Name : trfcNewsDetailPage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 교통정보 자료실 상세 페이지
	  * @return
	  */
	@GetMapping("/resources/{bbsId}")
	public String trfcResourcesDetailPage(Model model, @PathVariable("bbsId") String bbsId) {
		Map<String,String> bbsInfo = tlBbsInfoRepository.getBbsInfo(bbsId, LocaleContextHolder.getLocale().toString());
		
		TlBbsInfo tlBbsInfo = new TlBbsInfo(bbsInfo);
		tlBbsInfoRepository.save(tlBbsInfo);
		
		Optional<TlBbsFileGrp> tlBbsFileGrp = tlBbsFileGrpRepository.findByBbsId(bbsId);
		if (tlBbsFileGrp.isPresent()) {
			List<TlBbsFile> bbsFile = tlBbsFileRepository.findByFilegrpId(tlBbsFileGrp.get().getFilegrpId());
			model.addAttribute("bbsFile", bbsFile);
		}
		
//		model.addAttribute("preBbsInfo", tlBbsInfoRepository.getPreBbs(bbsId,BbsTypeCd.NOTICE.getCode()));
//		model.addAttribute("nextBbsInfo", tlBbsInfoRepository.getNextBbs(bbsId,BbsTypeCd.NOTICE.getCode()));
		model.addAttribute("bbsInfo", bbsInfo);
		return "views/trfcInfo/trfcResourcesDetail";
	}
}
