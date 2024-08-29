package com.sri.lanka.traffic.portal.common.component;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileComponent {
	
//	@Autowired
//	private TlBbsFileRepository tlBbsFileRepository;

//	@Value("${file.upload.path}")
//	String uploadPath;
//	
//	@Value("${file.sample.download.path}")
//	public String fileSampleDownloadPath;

	/**
	  * @Method Name : fileDownload
	  * @작성일 : 2024. 2. 5.
	  * @작성자 : SM.KIM
	  * @Method 설명 : 파일 다운로드
	  */
	public static void fileDownload(HttpServletResponse response, String fileName, String fileOriginName, String fileDownloadPath) {
		try {
			String filePath = fileDownloadPath +File.separator + fileName;
	    	File file = new File(filePath);
			log.info("filePath" + filePath);
	        // 파일이 존재
	        if (file.exists()) {
				log.info("file exists");
				String encodedFileName = URLEncoder.encode(fileOriginName, "UTF-8").replaceAll("\\+", "%20");
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment; fileName=\"" + encodedFileName + "\"");
	            response.setContentLengthLong(file.length());
	            BufferedInputStream in = null;
	            BufferedOutputStream out = null;
	            in = new BufferedInputStream(new FileInputStream(file));
	            out = new BufferedOutputStream(response.getOutputStream());
	            try {
	                byte[] buffer = new byte[4096];
	                int bytesRead = 0;
	                while ((bytesRead = in.read(buffer)) != -1) {
	                    out.write(buffer, 0, bytesRead);
	                }
	                out.flush();
	            } finally {
	                in.close();
	                out.close();
	            }
	        } else {
	            throw new FileNotFoundException();
	        }
		
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
}
