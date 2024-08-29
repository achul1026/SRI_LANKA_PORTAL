package com.sri.lanka.traffic.portal.common.entity;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data //게시판 정보
@EqualsAndHashCode(callSuper=true)
public class TlBbsInfo extends BaseEntity{

    @Id
    private String bbsId; //게시판 아이디

    private String bbsType; //게시판 유형

    private String bbsTitle; //게시판 제목

    private String bbsCnts; //게시판 내용

    private String dspyYn; //표출 여부
    
    private int viewCnt = 0; //조회수
    
    public TlBbsInfo() {}

	public TlBbsInfo(Map<String, String> bbsInfo) {
		this.bbsId=bbsInfo.get("bbsId");
		this.bbsType=bbsInfo.get("bbsType");
		this.bbsTitle=bbsInfo.get("bbsTitle");
		this.bbsCnts=bbsInfo.get("bbsCnts");
		this.dspyYn=bbsInfo.get("dspyYn");
		if(bbsInfo.get("viewCnt") == null){
			this.viewCnt=0;
		}else{
			this.viewCnt=Integer.parseInt(bbsInfo.get("viewCnt"));
		}
	};
}