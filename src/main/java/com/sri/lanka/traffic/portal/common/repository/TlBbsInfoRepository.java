package com.sri.lanka.traffic.portal.common.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sri.lanka.traffic.portal.common.dto.common.PagingDTO;
import com.sri.lanka.traffic.portal.common.dto.common.SearchDTO;
import com.sri.lanka.traffic.portal.common.entity.TlBbsInfo;

public interface TlBbsInfoRepository extends JpaRepository<TlBbsInfo, String> {

	List<TlBbsInfo> findTop6ByOrderByRegistDtDesc();

	@Query(value = "SELECT "
			+ "			tbi.bbs_id AS \"bbsId\", "
			+ "			tbi.bbs_type AS \"bbsType\", "
			+ "			tbi.bbs_title AS \"bbsTitle\", "
			+ "			tbi.bbs_cnts AS \"bbsCnts\", "
			+ "			tbi.dspy_yn AS \"dspyYn\", "
			+ "			tum.user_nm AS \"userNm\", "
			+ "			TO_CHAR(tbi.regist_dt,'yyyy-MM-dd HH24:mi') AS \"registDt\", "
			+ "			CAST(tbi.view_cnt + 1 AS text) AS \"viewCnt\", "
			+ "			(CASE "
			+ "				WHEN :lang = 'eng' THEN tci.cdnm_eng "
			+ "				WHEN :lang = 'kor' THEN tci.cdnm_kor "
			+ "				WHEN :lang = 'sin' THEN tci.cdnm_sin "
			+ "				ELSE tci.cdnm_eng END) AS \"userDept\", "
			
			+ "			prev.bbs_id AS \"prevBbsId\", "
            + "			prev.bbs_title AS \"prevBbsTitle\", "
            + "			next.bbs_id AS \"nextBbsId\", "
            + "			next.bbs_title AS \"nextBbsTitle\" "

			+ "		FROM "
			+ "			srlk.tl_bbs_info tbi "
			+ "		LEFT JOIN srlk.tc_user_mng tum "
			+ "			ON tum.user_id = tbi.regist_id "
			+ "		LEFT JOIN srlk.tc_cd_info tci "
			+ "			ON tci.cd = tum.user_dept "
			
			+ "		LEFT JOIN srlk.tl_bbs_info prev ON prev.dspy_yn = 'Y' "
			+ "			AND prev.bbs_type = tbi.bbs_type "
			+ "    		AND prev.regist_dt < tbi.regist_dt AND prev.bbs_id = ("
			+ "  	    	SELECT pb.bbs_id FROM srlk.tl_bbs_info pb "
			+ "    			WHERE pb.dspy_yn = 'Y' AND pb.bbs_type = tbi.bbs_type AND pb.regist_dt < tbi.regist_dt "
			+ "        		ORDER BY pb.regist_dt DESC LIMIT 1)"
			+ "		LEFT JOIN srlk.tl_bbs_info next ON next.dspy_yn = 'Y' "
			+ "			AND next.bbs_type = tbi.bbs_type "
			+ "    		AND next.regist_dt > tbi.regist_dt AND next.bbs_id = ("
			+ "     		SELECT nb.bbs_id FROM srlk.tl_bbs_info nb "
			+ "				WHERE nb.dspy_yn = 'Y' AND nb.bbs_type = tbi.bbs_type AND nb.regist_dt > tbi.regist_dt "
			+ "				ORDER BY nb.regist_dt ASC LIMIT 1)"
			
			+ "		WHERE tbi.bbs_id = :bbsId AND tbi.dspy_yn = 'Y' ", nativeQuery = true)
	Map<String,String> getBbsInfo(@Param("bbsId") String bbsId, @Param("lang") String lang);

	@Query(value = "SELECT "
			+ "			(ROW_NUMBER() OVER(order by RESULT.REGIST_DT asc) ) AS \"rownm\" , "
			+ "			RESULT.bbs_id AS \"bbsId\", "
			+ "			RESULT.bbs_title AS \"bbsTitle\", "
			+ "			RESULT.file_yn AS \"fileYn\", "
			+ "			TO_CHAR(RESULT.regist_dt,'yyyy-MM-dd') AS \"registDt\", "
			+ "			RESULT.view_cnt AS \"viewCnt\" "
			+ "		FROM ("
			+ "			SELECT"
			+ "				tbi.bbs_id, "
			+ "				tbi.bbs_title, "
			+ "				tbi.view_cnt, "
			+ "				(CASE WHEN tfg.filegrp_id IS NULL THEN 'N' "
			+ "				ELSE 'Y' END) AS \"file_yn\", "
			+ "				tbi.regist_dt "
			+ "			FROM"
			+ "				srlk.tl_bbs_info tbi "
			+ "			LEFT JOIN "
			+ "				srlk.tl_bbs_file_grp tfg "
			+ "				ON tfg.bbs_id = tbi.bbs_id "
			+ "			WHERE "
			+ "				tbi.bbs_type = :bbsType "
			+ "				AND tbi.dspy_yn = 'Y' "
			+ "				AND ("
			+ "    					("
			+ "        					(:#{#searchDTO.searchType} = 'title' AND tbi.bbs_title LIKE CONCAT('%', :#{#searchDTO.searchContent}, '%'))"
			+ "        					OR (:#{#searchDTO.searchType} = 'content' AND tbi.bbs_cnts LIKE CONCAT('%', :#{#searchDTO.searchContent}, '%'))"
			+ "        					OR (:#{#searchDTO.searchType} = '' AND (tbi.bbs_title LIKE CONCAT('%', :#{#searchDTO.searchContent}, '%') OR tbi.bbs_cnts LIKE CONCAT('%', :#{#searchDTO.searchContent}, '%')))"
			+ "    					)"
			+ "    				OR :#{#searchDTO.searchContent} IS NULL"
			+ "				)"
			+ "			ORDER BY "
			+ "				tbi.regist_dt DESC "
			+ "			) RESULT"
			+ "		ORDER BY RESULT.regist_dt DESC "
			+ "		OFFSET :#{#pagingDTO.offsetCount} LIMIT :#{#pagingDTO.limitCount} "
			+ " ", nativeQuery = true)
	List<Map<String,String>> getBbsList(@Param("bbsType") String bbsType, @Param("searchDTO") SearchDTO searchDTO, @Param("pagingDTO") PagingDTO pagingDTO);

	@Query(value = "SELECT COUNT(*) "
			+ "		FROM "
			+ "			srlk.tl_bbs_info tbi "
			+ "		LEFT JOIN "
			+ "			srlk.tl_bbs_file_grp tfg "
			+ "			ON tfg.bbs_id = tbi.bbs_id "
			+ "		WHERE "
			+ "			tbi.bbs_type = :bbsType "
			+ "			AND tbi.dspy_yn = 'Y' "
			+ "			AND ("
			+ "    				("
			+ "        				(:#{#searchDTO.searchType} = 'title' AND tbi.bbs_title LIKE CONCAT('%', :#{#searchDTO.searchContent}, '%'))"
			+ "        				OR (:#{#searchDTO.searchType} = 'content' AND tbi.bbs_cnts LIKE CONCAT('%', :#{#searchDTO.searchContent}, '%'))"
			+ "        				OR (:#{#searchDTO.searchType} = '' AND (tbi.bbs_title LIKE CONCAT('%', :#{#searchDTO.searchContent}, '%') OR tbi.bbs_cnts LIKE CONCAT('%', :#{#searchDTO.searchContent}, '%')))"
			+ "    				)"
			+ "    			OR :#{#searchDTO.searchContent} IS NULL"
			+ "			)", nativeQuery = true)
	long getTotalCount(@Param("bbsType") String bbsType, @Param("searchDTO") SearchDTO searchDTO);

	@Query(value = "SELECT "
			+ "			tbi.bbs_id AS \"bbsId\", "
			+ "			tbi.bbs_title AS \"bbsTitle\", "
			+ "			tbi.bbs_cnts AS \"bbsCnts\", "
			+ "			TO_CHAR(tbi.regist_dt,'yyyy-MM-dd HH24:mi') AS \"registDt\" "
			+ "		FROM "
			+ "			srlk.tl_bbs_info tbi "
			+ "		WHERE "
			+ "			tbi.bbs_type = :bbsType "
			+ "			AND tbi.dspy_yn = 'Y' "
			+ "			AND ("
			+ " 				("
			+ "     				(tbi.bbs_title LIKE CONCAT('%', :searchContent, '%'))"
			+ "     				OR (tbi.bbs_cnts LIKE CONCAT('%', :searchContent, '%'))"
			+ "     				OR ((tbi.bbs_title LIKE CONCAT('%', :searchContent, '%') OR tbi.bbs_cnts LIKE CONCAT('%', :searchContent, '%')))"
			+ " 				)"
			+ " 			OR :searchContent IS NULL) "
			+ "		ORDER BY tbi.regist_dt DESC LIMIT 5", nativeQuery = true)
	List<Map<String,String>> findTop5ByBbsType(@Param("bbsType") String bbsType, @Param("searchContent") String searchContent);

	
	@Query(value=""
			+ "	SELECT "
			+ "		tci.cd AS \"cd\", "
			+ "		(CASE "
			+ "	  	  	WHEN :lang = 'eng' THEN tci.cdnm_eng "
			+ "		  	WHEN :lang = 'kor' THEN tci.cdnm_kor "
			+ "		  	WHEN :lang = 'sin' THEN tci.cdnm_sin "
			+ "		  	ELSE tci.cdnm_eng END) AS \"cdNm\" "
			+ "	FROM srlk.tc_cd_info tci "
			+ "	LEFT JOIN srlk.tc_cd_grp tcg ON tcg.grp_cd = :grpCd"
			+ "	WHERE tcg.grpcd_id = tci.grpcd_id AND tci.cd != :cd AND tci.use_yn = 'Y' "
			, nativeQuery = true)
	List<Map<String,String>> getBoardListNotAlarm(@Param("grpCd") String grpCd, @Param("cd") String cd, @Param("lang") String lang);
	
//	@Query(value = "SELECT "
//            + "tbi.bbs_id AS \"bbsId\", "
//            + "tbi.bbs_title AS \"bbsTitle\" "
//            + "FROM srlk.tl_bbs_info tbi "
//            + "WHERE tbi.dspy_yn = 'Y' "
//            + "AND tbi.bbs_type = :bbsType "
//            + "AND tbi.regist_dt < (SELECT regist_dt FROM srlk.tl_bbs_info WHERE bbs_id = :bbsId) "
//            + "ORDER BY tbi.regist_dt DESC "
//            + "LIMIT 1", nativeQuery = true)
//	Map<String,String> getPreBbs(@Param("bbsId") String bbsId, @Param("bbsType") String bbsType);
//	
//	@Query(value = "SELECT "
//            + "tbi.bbs_id AS \"bbsId\", "
//            + "tbi.bbs_title AS \"bbsTitle\" "
//            + "FROM srlk.tl_bbs_info tbi "
//            + "WHERE tbi.dspy_yn = 'Y' "
//            + "AND tbi.bbs_type = :bbsType "
//            + "AND tbi.regist_dt > (SELECT regist_dt FROM srlk.tl_bbs_info WHERE bbs_id = :bbsId) "
//            + "ORDER BY tbi.regist_dt ASC "
//            + "LIMIT 1", nativeQuery = true)
//	Map<String,String> getNextBbs(@Param("bbsId") String bbsId, @Param("bbsType") String bbsType);

}
