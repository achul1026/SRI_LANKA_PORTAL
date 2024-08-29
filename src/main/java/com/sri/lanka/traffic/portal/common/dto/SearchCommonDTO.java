package com.sri.lanka.traffic.portal.common.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SearchCommonDTO {
	 private String searchType = "";
	 private String searchContent;
	 private String searchSttsCd;
	 private String searchTypeCd;
	 private LocalDate startDate;
	 private LocalDate endDate;
}

