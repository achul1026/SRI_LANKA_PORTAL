package com.sri.lanka.traffic.portal.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sri.lanka.traffic.portal.common.entity.TlBbsFile;

public interface TlBbsFileRepository extends JpaRepository<TlBbsFile, String> {

	List<TlBbsFile> findByFilegrpId(String filegrpId);

}
