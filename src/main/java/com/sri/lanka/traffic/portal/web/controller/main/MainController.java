package com.sri.lanka.traffic.portal.web.controller.main;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sri.lanka.traffic.portal.common.component.FileComponent;
import com.sri.lanka.traffic.portal.common.entity.TlBbsFile;
import com.sri.lanka.traffic.portal.common.repository.TcFaqMngRepository;
import com.sri.lanka.traffic.portal.common.repository.TlBbsFileRepository;
import com.sri.lanka.traffic.portal.common.repository.TlBbsInfoRepository;


@Controller
public class MainController {
	
	@Autowired
	TlBbsInfoRepository tlBbsInfoRepository;
	
	@Autowired
	TcFaqMngRepository tcFaqMngRepository;
	
	@Autowired
	TlBbsFileRepository tlBbsFileRepository;


    /**
      * @Method Name : main
      * @작성일 : 2024. 2. 14.
      * @작성자 : SM.KIM
      * @Method 설명 : 메인 페이지
      * @param model
      * @return
      */
    @GetMapping(value = {"/main", "/"})
    public String main(Model model){
    	model.addAttribute("noticeList", tlBbsInfoRepository.findTop6ByOrderByRegistDtDesc());
    	model.addAttribute("faqList", tcFaqMngRepository.findTop3ByOrderByRegistDtDesc());
        return "views/main/main";
    }
    
    /**
	  * @Method Name : fileDownload
	  * @작성일 : 2024. 7. 29.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 첨부파일 다운로드
	  * @param response
	  * @param fileId
	  */
	@GetMapping("/download/{fileId}")
	public void fileDownload(HttpServletResponse response, @PathVariable("fileId") String fileId) {
		Optional<TlBbsFile> fileMng = tlBbsFileRepository.findById(fileId);
		if (fileMng.isPresent()) {
			FileComponent.fileDownload(response, fileMng.get().getFileNm(), fileMng.get().getOrgFilenm(), fileMng.get().getFilePath());
		}
	}
}
