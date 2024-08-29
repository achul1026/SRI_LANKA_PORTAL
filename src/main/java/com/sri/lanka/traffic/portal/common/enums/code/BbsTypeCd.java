package com.sri.lanka.traffic.portal.common.enums.code;

import com.sri.lanka.traffic.portal.common.util.CommonUtils;

import lombok.Getter;

@Getter
public enum BbsTypeCd {
	
	NOTICE("BTC001","notice"),
	NEWS("BTC002","news"),
	RESOURCES("BTC003","resources"),
	ALARM("BTC004","alarm"),
	;
	
	private String code; 
	private String name; 
	
	BbsTypeCd(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
    public String getCode() {
        return code;
    }
    
    public String getName() {
    	return name;
    }
    
    public static BbsTypeCd getEnums(String code) {
		if(!CommonUtils.isNull(code)) {
			for(BbsTypeCd r : BbsTypeCd.values()) {
				if(r.code.equals(code)) {
					return r;
				}
			}
		}
		return null;
	}
	
}
