package com.sri.lanka.traffic.portal.common.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Metrocount 고정식 일 통계
 */
@Data
@Entity
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class TsFixedDd extends BaseEntity{
	@Id
	private String statsYymmdd;		//통계연월일
	private String deviceId;		//장치ID
	private String instllcId;
	private String vhclDrct;		//차량 방향
	private String vhclClsf;		//차량 분류
	private long trfvlm;			//교통량
	private long avgSpeed;		//평균 속도
}
