package com.sri.lanka.traffic.portal.common.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sri.lanka.traffic.portal.common.entity.TcCdInfo;

public interface TcCdInfoRepository extends JpaRepository<TcCdInfo, String>{

	@Query(value="SELECT tci.cd,"
			+ "        	(CASE "
			+ "            	WHEN :lang = 'eng' THEN tci.cdnm_eng "
			+ "            	WHEN :lang = 'kor' THEN tci.cdnm_kor "
			+ "            	WHEN :lang = 'sin' THEN tci.cdnm_sin "
			+ "        	ELSE tci.cdnm_eng END) AS cdNm "
			+ "	FROM srlk.tc_cd_info tci "
			+ "	LEFT JOIN srlk.tc_cd_grp tcg ON tcg.grp_cd = :grpCd "
			+ "	WHERE tcg.grpcd_id = tci.grpcd_id "
			+ "		AND tci.use_yn = 'Y' ", nativeQuery = true)
	List<Map<String,String>> getCdListByGrpcdId(@Param("grpCd") String grpCd, @Param("lang") String lang);

}
