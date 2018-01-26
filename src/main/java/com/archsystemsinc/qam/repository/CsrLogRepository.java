package com.archsystemsinc.qam.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.archsystemsinc.qam.model.CsrLog;

/**
 */
public interface CsrLogRepository extends JpaRepository<CsrLog, Long>{
	
	//AllCsrLogdReport
	
		@Query("SELECT c FROM CsrLog c WHERE c.macId = :macId and c.jurisdiction = :jurisdiction and c.createdDate between :fromDate and :toDate ")
	    public List<CsrLog> complianceReport(@Param("macId") Integer macId,@Param("jurisdiction") String jurisdiction,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
		
		@Query("SELECT c FROM CsrLog c WHERE c.createdDate between :fromDate and :toDate ")
	    public List<CsrLog> complianceReport_AllMacAllJuris(@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
		
		@Query("SELECT c FROM CsrLog c WHERE c.macId = :macId and c.createdDate between :fromDate and :toDate ")
	    public List<CsrLog> complianceReport_AllJuris(@Param("macId") Integer macId,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
		
		@Query("SELECT c FROM CsrLog c WHERE c.jurisdiction = :jurisdiction and c.createdDate between :fromDate and :toDate ")
	    public List<CsrLog> complianceReport_AllMac(@Param("jurisdiction") String jurisdiction,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);

		
	    
}
