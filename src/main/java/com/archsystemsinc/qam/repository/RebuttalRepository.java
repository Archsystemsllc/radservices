package com.archsystemsinc.qam.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.archsystemsinc.qam.model.Rebuttal;

/**
 */
public interface RebuttalRepository extends JpaRepository<Rebuttal, Integer>, JpaSpecificationExecutor<Rebuttal>{	
	
	//AllCsrLogdReport
	
			@Query("SELECT r FROM Rebuttal r WHERE r.macId = :macId and r.jurisId = :jurisId and STR_TO_DATE(r.datePosted,'%m/%d/%Y %h:%i:%s %p') between :fromDate and :toDate ")
		    public List<Rebuttal> rebuttalReport(@Param("macId") Integer macId,@Param("jurisId") Integer jurisId,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
			
			@Query("SELECT r FROM Rebuttal r WHERE STR_TO_DATE(r.datePosted,'%m/%d/%Y %h:%i:%s %p') between :fromDate and :toDate ")
			public List<Rebuttal> rebuttalReport_AllMacAllJuris(@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
			
			@Query("SELECT r FROM Rebuttal r WHERE r.macId = :macId and STR_TO_DATE(r.datePosted,'%m/%d/%Y %h:%i:%s %p') between :fromDate and :toDate ")
			
		    public List<Rebuttal> rebuttalReport_AllJuris(@Param("macId") Integer macId,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
			
			@Query("SELECT r FROM Rebuttal r WHERE r.jurisId = :jurisId and STR_TO_DATE(r.datePosted,'%m/%d/%Y %h:%i:%s %p') between :fromDate and :toDate ")		
		    public List<Rebuttal> rebuttalReport_AllMac(@Param("jurisId") Integer jurisId,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);	
}