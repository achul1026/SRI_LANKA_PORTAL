package com.sri.lanka.traffic.portal.common.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sri.lanka.traffic.portal.common.entity.TlBbsFileGrp;

public interface TlBbsFileGrpRepository extends JpaRepository<TlBbsFileGrp, String> {

	Optional<TlBbsFileGrp> findByBbsId(String bbsId);

}
