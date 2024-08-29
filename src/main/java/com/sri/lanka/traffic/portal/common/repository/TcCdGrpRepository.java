package com.sri.lanka.traffic.portal.common.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sri.lanka.traffic.portal.common.entity.TcCdGrp;

public interface TcCdGrpRepository extends JpaRepository<TcCdGrp, String>{

	@Query(value = "SELECT "
			+ "			(CASE WHEN :lang = 'eng' THEN tci.cdnm_eng "
			+ "				WHEN :lang = 'kor' THEN tci.cdnm_kor "
			+ "				WHEN :lang = 'sin' THEN tci.cdnm_sin "
			+ "				ELSE tci.cdnm_eng END) AS \"cdNm\", "
			+ "			tci.cd AS \"cd\" "
			+ " 	FROM "
			+ "			srlk.tc_cd_grp tcg "
			+ "		LEFT JOIN "
			+ "			srlk.tc_cd_info tci "
			+ "			ON tcg.grpcd_id = tci.grpcd_id "
			+ "		WHERE"
			+ "			tcg.grp_cd = :code "
			, nativeQuery = true)
	List<Map<String, String>> getFaqList(@Param("code") String code, @Param("lang") String lang);
	
}
