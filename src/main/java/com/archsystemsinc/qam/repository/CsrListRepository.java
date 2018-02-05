package com.archsystemsinc.qam.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.archsystemsinc.qam.model.CsrLists;
import com.archsystemsinc.qam.model.ScoreCard;

/**
 */
public interface CsrListRepository extends JpaRepository<CsrLists, Long>, JpaSpecificationExecutor<CsrLists>{
	
	//Queries to Find CSR Lists by From Date and To Date
	
	@Query("SELECT c FROM CsrLists c WHERE EXTRACT(YEAR_MONTH FROM c.createdDate) >= :fromMonthYear and EXTRACT(YEAR_MONTH FROM c.createdDate) <= :toMonthYear "
			+ "and c.macLookupId in (:macLookupIdList) and c.jurisdiction in (:jurisdictionList) and c.recordStatus = 1")
    public List<CsrLists> findByMonthYearRange(@Param("fromMonthYear") Integer fromMonthYear, @Param("toMonthYear") Integer toMonthYear,@Param("macLookupIdList")ArrayList<Long> macLookupArrayList,@Param("jurisdictionList")ArrayList<String> jurisdictionArrayList);
	
	@Query("SELECT c FROM CsrLists c WHERE EXTRACT(YEAR_MONTH FROM c.createdDate) >= :fromMonthYear and EXTRACT(YEAR_MONTH FROM c.createdDate) <= :toMonthYear "
			+ "and c.recordStatus = 1")
    public List<CsrLists> findByMonthYearRangeAll(@Param("fromMonthYear") Integer fromMonthYear, @Param("toMonthYear") Integer toMonthYear);
	
	@Query("SELECT c FROM CsrLists c WHERE EXTRACT(YEAR_MONTH FROM c.createdDate) >= :fromMonthYear and EXTRACT(YEAR_MONTH FROM c.createdDate) <= :toMonthYear "
			+ "and c.jurisdiction in (:jurisdictionList) and c.recordStatus = 1")
    public List<CsrLists> findByMonthYearRangeAllMac(@Param("fromMonthYear") Integer fromMonthYear, @Param("toMonthYear") Integer toMonthYear,@Param("jurisdictionList")ArrayList<String> jurisdictionArrayList);
	
	@Query("SELECT c FROM CsrLists c WHERE EXTRACT(YEAR_MONTH FROM c.createdDate) >= :fromMonthYear and EXTRACT(YEAR_MONTH FROM c.createdDate) <= :toMonthYear "
			+ "and c.macLookupId in (:macLookupIdList) and c.recordStatus = 1")
    public List<CsrLists> findByMonthYearRangeAllJuris(@Param("fromMonthYear") Integer fromMonthYear, @Param("toMonthYear") Integer toMonthYear,@Param("macLookupIdList")ArrayList<Long> macLookupArrayList);
	
	//Queries to Find Months based on CSR Lists by From Date and To Date
	@Query("SELECT MONTHNAME(c.createdDate) as month, YEAR(c.createdDate) as year FROM CsrLists c WHERE EXTRACT(YEAR_MONTH FROM c.createdDate) >= :fromMonthYear and EXTRACT(YEAR_MONTH FROM c.createdDate) <= :toMonthYear "
			+ "and c.macLookupId in (:macLookupIdList) and c.jurisdiction in (:jurisdictionList) and c.recordStatus = 1 "
			+ "GROUP BY EXTRACT(YEAR_MONTH FROM c.createdDate)")
    public List<Object[]> findMonthsByMonthYearRange(@Param("fromMonthYear") Integer fromMonthYear, @Param("toMonthYear") Integer toMonthYear,@Param("macLookupIdList")ArrayList<Long> macLookupArrayList,@Param("jurisdictionList")ArrayList<String> jurisdictionArrayList);
    
    @Query("SELECT MONTHNAME(c.createdDate) as month, YEAR(c.createdDate) as year FROM CsrLists c WHERE EXTRACT(YEAR_MONTH FROM c.createdDate) >= :fromMonthYear and EXTRACT(YEAR_MONTH FROM c.createdDate) <= :toMonthYear "
			+ "and c.recordStatus = 1 "
			+ "GROUP BY EXTRACT(YEAR_MONTH FROM c.createdDate)")
    public List<Object[]> findMonthsByMonthYearRangeAll(@Param("fromMonthYear") Integer fromMonthYear, @Param("toMonthYear") Integer toMonthYear);
    
    @Query("SELECT MONTHNAME(c.createdDate) as month, YEAR(c.createdDate) as year FROM CsrLists c WHERE EXTRACT(YEAR_MONTH FROM c.createdDate) >= :fromMonthYear and EXTRACT(YEAR_MONTH FROM c.createdDate) <= :toMonthYear "
			+ "and c.jurisdiction in (:jurisdictionList) and c.recordStatus = 1 "
			+ "GROUP BY EXTRACT(YEAR_MONTH FROM c.createdDate)")
    public List<Object[]> findMonthsByMonthYearRangeAllMac(@Param("fromMonthYear") Integer fromMonthYear, @Param("toMonthYear") Integer toMonthYear,@Param("jurisdictionList")ArrayList<String> jurisdictionArrayList);
    
    @Query("SELECT MONTHNAME(c.createdDate) as month, YEAR(c.createdDate) as year FROM CsrLists c WHERE EXTRACT(YEAR_MONTH FROM c.createdDate) >= :fromMonthYear and EXTRACT(YEAR_MONTH FROM c.createdDate) <= :toMonthYear "
			+ "and c.macLookupId in (:macLookupIdList) and c.recordStatus = 1 "
			+ "GROUP BY EXTRACT(YEAR_MONTH FROM c.createdDate)")
    public List<Object[]> findMonthsByMonthYearRangeAllJuris(@Param("fromMonthYear") Integer fromMonthYear, @Param("toMonthYear") Integer toMonthYear,@Param("macLookupIdList")ArrayList<Long> macLookupArrayList);
    
    
  //Queries to Find CSR Lists
    
    @Query("SELECT c FROM CsrLists c WHERE c.lastName Like :csrLName% and c.macLookupId = :macLookupId and c.jurisdiction = :jurisdiction and c.program = :program and c.recordStatus = 1")
    public List<CsrLists> existingCsrListByMacIdJurisProgram(@Param("csrLName") String csrLName,@Param("macLookupId") Long macLookupId,@Param("jurisdiction") String jurisdiction,@Param("program") String program);
    
    
	@Query("SELECT c FROM CsrLists c WHERE c.macLookupId = :macLookupId and EXTRACT(YEAR_MONTH FROM c.createdDate) = :monthYear and c.recordStatus = 1")
    public List<CsrLists> existingCsrListByUserMonthYear(@Param("macLookupId") Long macLookupId,@Param("monthYear") Integer monthYear);
	
	@Query("SELECT c FROM CsrLists c WHERE c.macLookupId = :macLookupId and c.jurisdiction=:jurisdiction and EXTRACT(YEAR_MONTH FROM c.createdDate) = :monthYear and c.recordStatus = 1")
    public List<CsrLists> existingCsrListByMacMonthYear(@Param("macLookupId") Long macLookupId,@Param("monthYear") Integer monthYear,@Param("jurisdiction") String jurisdiction);
	
	@Query("SELECT c FROM CsrLists c WHERE c.macLookupId = :macLookupId and c.jurisdiction = :jurisdiction  and EXTRACT(YEAR_MONTH FROM c.createdDate) = :monthYear and c.recordStatus = 1")
    public List<CsrLists> existingCsrListByMacJurisdictionMonthYear(@Param("macLookupId") Long macLookupId,@Param("jurisdiction") String jurisdiction,@Param("monthYear") Integer monthYear);
	
	
	@Query("SELECT c FROM CsrLists c WHERE c.userId = :userId and c.macLookupId = :macLookupId and EXTRACT(YEAR_MONTH FROM c.createdDate) = :monthYear and c.recordStatus = 1")
    public List<CsrLists> existingCsrListByUserMacMonthYear(@Param("userId") Long userId,@Param("macLookupId") Long macLookupId,@Param("monthYear") Integer monthYear);

	@Modifying
	@Query("update CsrLists c set c.recordStatus = :status, c.updateddDate = :updatedDate where c.userId = :userId and EXTRACT(YEAR_MONTH FROM c.createdDate) = :monthYear")
	int markStatusDeleted(@Param("status") Long status, @Param("userId") Long userId,@Param("monthYear") Integer monthYear,@Param("updatedDate") Date updatedDate);
	
	@Modifying
	@Query("update CsrLists c set c.recordStatus = :status, c.updateddDate = :updatedDate where c.macLookupId = :macLookupId and c.jurisdiction=:jurisdiction and EXTRACT(YEAR_MONTH FROM c.createdDate) = :monthYear")
	int markStatusDeletedForAdmin(@Param("status") Long status, @Param("macLookupId") Long macLookupId,@Param("monthYear") Integer monthYear,@Param("updatedDate") Date updatedDate,@Param("jurisdiction") String jurisdiction);
}
