package com.sri.lanka.traffic.portal.common.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data //Faq 관리
@EqualsAndHashCode(callSuper=true)
public class TcFaqMng extends BaseEntity{

    @Id
    private String faqId; //FAQ 아이디

    private String faqType; //FAQ 유형

    private String faqQstn; //FAQ 질문

    private String faqAns; //FAQ 답변

    private String dspyYn; //표출 여부

}