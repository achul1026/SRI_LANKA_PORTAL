package com.sri.lanka.traffic.portal.common.dto.stats;

import lombok.Data;

@Data
public class StatsSearchDTO {
	private String date;
	private String startDate;
	private String endDate;
	private String searchType;
}
