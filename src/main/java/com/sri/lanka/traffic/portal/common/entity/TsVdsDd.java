package com.sri.lanka.traffic.portal.common.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data //게시판 정보
@EqualsAndHashCode(callSuper=true)
public class TsVdsDd extends BaseEntity{

    @Id
    private String statsYymmdd; //통계연월일

    private String cameraId; //카메라 ID

    private String instllcId;
    
    private String vhclClsf; //차량 분류
    
    private String trfvlm; //교통량
    
    private String avgSpeed; //평균 속도

}
