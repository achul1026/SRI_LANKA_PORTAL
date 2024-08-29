package com.sri.lanka.traffic.portal.common.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sri.lanka.traffic.portal.common.dto.common.PagingDTO;
import com.sri.lanka.traffic.portal.common.entity.TcFaqMng;

public interface TcFaqMngRepository extends JpaRepository<TcFaqMng, String> {

	List<TcFaqMng> findTop3ByOrderByRegistDtDesc();

	@Query(value = "SELECT"
			+ "			tfm.faq_qstn AS \"faqQstn\", "
			+ "			tfm.faq_ans AS \"faqAns\" "
			+ "		FROM"
			+ "			srlk.tc_faq_mng tfm "
			+ "		WHERE "
			+ "			tfm.faq_type = :typeCd "
			+ "			AND tfm.dspy_yn = 'Y' "
			+ "		ORDER BY "
			+ "			tfm.regist_dt DESC "
			+ "		OFFSET :#{#pagingDTO.offsetCount} LIMIT :#{#pagingDTO.limitCount} "
			, nativeQuery = true)
	List<Map<String, String>> getFaqList(@Param("typeCd") String typeCd, @Param("pagingDTO") PagingDTO pagingDTO);

	@Query(value = "SELECT COUNT(*) "
			+ "		FROM "
			+ "			srlk.tc_faq_mng tfm "
			+ "		WHERE "
			+ "			tfm.faq_type = :faqType "
			+ "			AND tfm.dspy_yn = 'Y' "
			, nativeQuery = true)
	long getTotalCount(@Param("faqType") String faqType);

}
