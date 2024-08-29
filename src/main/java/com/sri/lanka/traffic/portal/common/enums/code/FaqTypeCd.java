package com.sri.lanka.traffic.portal.common.enums.code;

import com.sri.lanka.traffic.portal.common.util.CommonUtils;

import lombok.Getter;

@Getter
public enum FaqTypeCd {
	
	RELATED_TO_SYSTEM_USE("FTC001"),
	TRAFFIC_STATISTICS_RELATED("FTC002"),
	DATA_RELATED("FTC003"),
	ETC("FTC004"),
	;
	
	private String code; 
	
	FaqTypeCd(String code) {
		this.code = code;
	}
	
    public String getCode() {
        return code;
    }
	
    public static FaqTypeCd getEnums(String code) {
		if(!CommonUtils.isNull(code)) {
			for(FaqTypeCd r : FaqTypeCd.values()) {
				if(r.code.equals(code)) {
					return r;
				}
			}
		}
		return null;
	}
}
