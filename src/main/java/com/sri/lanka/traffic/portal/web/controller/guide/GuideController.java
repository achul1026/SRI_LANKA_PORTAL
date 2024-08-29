package com.sri.lanka.traffic.portal.web.controller.guide;

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
import com.sri.lanka.traffic.portal.common.enums.code.FaqTypeCd;
import com.sri.lanka.traffic.portal.common.enums.code.GroupCode;
import com.sri.lanka.traffic.portal.common.repository.TcCdGrpRepository;
import com.sri.lanka.traffic.portal.common.repository.TcFaqMngRepository;
import com.sri.lanka.traffic.portal.common.repository.TlBbsFileGrpRepository;
import com.sri.lanka.traffic.portal.common.repository.TlBbsFileRepository;
import com.sri.lanka.traffic.portal.common.repository.TlBbsInfoRepository;
import com.sri.lanka.traffic.portal.common.util.CommonUtils;


@Controller
@RequestMapping("/guide")
public class GuideController {
	
	@Autowired
	TlBbsInfoRepository tlBbsInfoRepository;
	
	@Autowired
	TcFaqMngRepository tcFaqMngRepository;
	
	@Autowired
	TlBbsFileGrpRepository tlBbsFileGrpRepository;
	
	@Autowired
	TlBbsFileRepository tlBbsFileRepository;
	
	@Autowired
	TcCdGrpRepository tcCdGrpRepository;

	/**
	  * @Method Name : guideNoticeListPage
	  * @작성일 : 2024. 2. 14.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 이용안내 공지사항 목록 조회 페이지
	  * @param model
	  * @return
	  */
	@GetMapping("/notice")
	public String guideNoticeListPage(Model model, SearchDTO searchDTO, PagingDTO pagingDTO) {
		List<Map<String,String>> bbsList = tlBbsInfoRepository.getBbsList(BbsTypeCd.NOTICE.getCode(), searchDTO, pagingDTO);
		long totalCount = tlBbsInfoRepository.getTotalCount(BbsTypeCd.NOTICE.getCode(), searchDTO);
		pagingDTO.setTotalCount(totalCount);
		model.addAttribute("bbsList", bbsList);
		model.addAttribute("searchDTO", searchDTO);
		model.addAttribute("pagingDTO", pagingDTO);
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
	@GetMapping("/notice/{bbsId}")
	public String guideNoticeDetailPage(Model model, @PathVariable("bbsId") String bbsId) {
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
		List<Map<String,String>> faqList = tcCdGrpRepository.getFaqList(GroupCode.FAQ_TYPE_CD.getCode(), LocaleContextHolder.getLocale().toString());
		model.addAttribute("faqList", faqList);
		return "views/guide/guideFaq";
	}
	
	/**
	  * @Method Name : getFaqListByType
	  * @작성일 : 2024. 7. 29.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 이용안내 FAQ 목록 조회
	  * @param model
	  * @param typeCd
	  * @param pagingDTO
	  * @return
	  */
	@GetMapping("/faq/{typeCd}")
	public String getFaqListByType(Model model, @PathVariable(name = "typeCd") String typeCd, PagingDTO pagingDTO) {
		FaqTypeCd faq = FaqTypeCd.getEnums(typeCd);
		String faqType = CommonUtils.isNull(faq) ? FaqTypeCd.RELATED_TO_SYSTEM_USE.getCode() : faq.getCode();
		
		List<Map<String,String>> faqList = tcFaqMngRepository.getFaqList(faqType, pagingDTO);
		long totalCount = tcFaqMngRepository.getTotalCount(faqType);
		pagingDTO.setTotalCount(totalCount);
		
		model.addAttribute("type", typeCd);
		model.addAttribute("faqList", faqList);
		model.addAttribute("pagingDTO", pagingDTO);
		return "views/guide/guideFaqList";
	}
	
}
