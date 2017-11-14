package com.archsystemsinc.qam.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.archsystemsinc.qam.model.CsrList;

/**
 */
public interface CsrListRepository extends JpaRepository<CsrList, Long>{
	
	public List<CsrList> findByCreatedDateBetween(Date from, Date to);
}
