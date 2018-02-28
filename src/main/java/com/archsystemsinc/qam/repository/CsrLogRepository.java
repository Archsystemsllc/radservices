package com.archsystemsinc.qam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.qam.model.CsrLog;

/**
 */
public interface CsrLogRepository extends JpaRepository<CsrLog, Long>, JpaSpecificationExecutor<CsrLog>{
	
	//AllCsrLogdReport
	
		/*@Query("SELECT c FROM CsrLog c WHERE c.macId = :macId and c.jurisdiction = :jurisdiction and c.createdDate between :fromDate and :toDate ")
	    public List<CsrLog> complianceReport(@Param("macId") Integer macId,@Param("jurisdiction") String jurisdiction,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
		
		@Query("SELECT c FROM CsrLog c WHERE c.createdDate between :fromDate and :toDate ")
	    public List<CsrLog> complianceReport_AllMacAllJuris(@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
		
		@Query("SELECT c FROM CsrLog c WHERE c.macId = :macId and c.createdDate between :fromDate and :toDate ")
	    public List<CsrLog> complianceReport_AllJuris(@Param("macId") Integer macId,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
		
		@Query("SELECT c FROM CsrLog c WHERE c.jurisdiction = :jurisdiction and c.createdDate between :fromDate and :toDate ")
	    public List<CsrLog> complianceReport_AllMac(@Param("jurisdiction") String jurisdiction,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);

	*/	
	    
}
