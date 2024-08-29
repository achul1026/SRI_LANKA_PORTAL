package com.sri.lanka.traffic.portal.common.enums.code;

import com.sri.lanka.traffic.portal.common.util.CommonUtils;

import lombok.Getter;

@Getter
public enum FacilityTypeCd {
	
	VDS("FCT001"),
	METRO_COUNT("FCT002"),
	PORTABLE_METRO_COUNT("FCT003"),
	;
	
	private String code; 
	
	FacilityTypeCd(String code) {
		this.code = code;
	}
	
    public String getCode() {
        return code;
    }
    
    public static FacilityTypeCd getEnums(String code) {
		if(!CommonUtils.isNull(code)) {
			for(FacilityTypeCd r : FacilityTypeCd.values()) {
				if(r.code.equals(code)) {
					return r;
				}
			}
		}
		return null;
	}
	
}
