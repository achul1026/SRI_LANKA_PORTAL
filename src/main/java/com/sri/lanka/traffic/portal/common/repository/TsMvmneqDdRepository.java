package com.sri.lanka.traffic.portal.common.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sri.lanka.traffic.portal.common.entity.TsMvmneqDd;

public interface TsMvmneqDdRepository extends JpaRepository<TsMvmneqDd, String>{

	@Query(value="SELECT "
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
			+ "			COALESCE (AVG(tmo.avg_speed),0) AS \"avg_speed\" "
			+ "			FROM "
			+ "				statistics_time_date "
			+ "			LEFT JOIN srlk.ts_mvmneq_onhr tmo ON CAST(RIGHT(tmo.stats_yymmdt,2) AS numeric) = statistics_time_date.statistics_time "
			+ "			AND LEFT(tmo.stats_yymmdt,8) = :searchDate "
			+ "			GROUP BY statistics_time_date.statistics_time "
			+ "			ORDER BY statistics_time_date.statistics_time "
			+ ") RESULT "
			, nativeQuery = true)
	Map<String, String> getRemovableAvgSpeed(@Param("searchDate") String searchDate);
	@Query(value=
			"SELECT "
			+ "		CAST(JSONB_BUILD_OBJECT('trfvlm',JSONB_AGG(RESULT.trfvlm),"
			+ "		'legend',JSONB_AGG(RESULT.vhcl_clsf)) AS TEXT) AS \"resultData\" "
			+ "	FROM( "
			+ " SELECT "
			+ "	tfd.vhcl_clsf, "
			+ "	SUM(tfd.trfvlm) AS \"trfvlm\" "
			+ "FROM "
			+ "	srlk.ts_mvmneq_dd tfd "
			+ "WHERE TO_DATE(tfd.stats_yymmdd,'YYYYMMDD') BETWEEN TO_DATE(:startDate, 'YYYY-MM-DD') AND TO_DATE(:endDate, 'YYYY-MM-DD') "
			+ "GROUP BY tfd.vhcl_clsf "
			+ "ORDER BY SUM(tfd.trfvlm) DESC "
			+ ") RESULT ", nativeQuery = true)
	Map<String, String> getRemovableVolumeStats(@Param("startDate") String startDate, @Param("endDate") String endDate);

}