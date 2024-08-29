package com.sri.lanka.traffic.portal.common.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sri.lanka.traffic.portal.common.entity.TsVdsDd;

public interface TsVdsDdRepository extends JpaRepository<TsVdsDd, String> {

	@Query(value="SELECT "
//			+ "		CAST(JSONB_BUILD_OBJECT('avgSpeed',JSONB_AGG(RESULT.avg_speed),"
//			+ "		'legend',JSONB_AGG(RESULT.vhcl_clsf)) AS TEXT) AS \"resultData\" "
//			+ "	FROM( "
			
			
			
			+ "		CAST(JSONB_BUILD_OBJECT('avgSpeed',JSONB_AGG(RESULT.avg_speed),"
			+ "		'legend',JSONB_AGG(RESULT.statistics_time)) AS TEXT) AS \"resultData\" "
			+ "	FROM( "
			+ " 	WITH statistics_time_date AS ( "
			+ "					select generate_series( "
			+ "						0, "
			+ "						23 "
			+ "				) AS statistics_time "
			+ "			) "
			+ "			SELECT "
			+ "			statistics_time_date.statistics_time AS statistics_time, "
			+ "			COALESCE (AVG(tvo.avg_speed),0) AS \"avg_speed\" "
			+ "			FROM "
			+ "				statistics_time_date "
			+ "			LEFT JOIN srlk.ts_vds_onhr tvo ON CAST(RIGHT(tvo.stats_yymmdt,2) AS numeric) = statistics_time_date.statistics_time "
			+ "			AND LEFT(tvo.stats_yymmdt,8) = :searchDate "
			+ "			GROUP BY statistics_time_date.statistics_time "
			+ "			ORDER BY statistics_time_date.statistics_time "
			


//			+ "		SELECT "
//			+ "			tvd.vhcl_clsf AS \"vhcl_clsf\", "
//			+ "			ROUND(AVG(tvd.avg_speed),2) AS \"avg_speed\" "
//			+ "		FROM "
//			+ "			srlk.ts_vds_dd tvd "
//			+ "		WHERE "
//			+ "			tvd.stats_yymmdd >= :startDate AND tvd.stats_yymmdd <= :endDate "
//			+ "		GROUP BY "
//			+ "			tvd.vhcl_clsf "
//			+ "		ORDER BY "
//			+ "			AVG(tvd.trfvlm) DESC "
			+ ") RESULT "
			+ " ", nativeQuery = true)
	Map<String, String> getVdsAvgSpeed(@Param("searchDate") String searchDate);
//	Map<String, String> getVdsAvgSpeed(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
	
	@Query(value=
			"SELECT "
			+ "		CAST(JSONB_BUILD_OBJECT('trfvlm',JSONB_AGG(RESULT.trfvlm),"
			+ "		'legend',JSONB_AGG(RESULT.vhcl_clsf)) AS TEXT) AS \"resultData\" "
			+ "	FROM( "
			+ " SELECT "
			+ "	tvd.vhcl_clsf, "
			+ "	SUM(tvd.trfvlm) AS \"trfvlm\" "
			+ "FROM "
			+ "	srlk.ts_vds_dd tvd "
			+ "WHERE TO_DATE(tvd.stats_yymmdd,'YYYYMMDD') BETWEEN TO_DATE(:startDate, 'YYYY-MM-DD') AND TO_DATE(:endDate, 'YYYY-MM-DD') "
			+ "GROUP BY tvd.vhcl_clsf "
			+ "ORDER BY SUM(tvd.trfvlm) DESC "
			+ ") RESULT ", nativeQuery = true)
	Map<String, String> getVdsVolumeStats(@Param("startDate") String startDate, @Param("endDate") String endDate);

}
