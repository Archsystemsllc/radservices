package com.archsystemsinc.qam.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.archsystemsinc.qam.model.CsrLists;
import com.archsystemsinc.qam.model.CsrLog;
import com.archsystemsinc.qam.model.QamEnvironmentChangeForm;

/**
 */
public interface QamEnvironmentChangeFormRepository extends JpaRepository<QamEnvironmentChangeForm, Long>, JpaSpecificationExecutor<QamEnvironmentChangeForm>{
	
	//Queries to Find Months based on CSR Lists by From Date and To Date
		@Query("SELECT MONTHNAME(c.createdDate) as month, YEAR(c.createdDate) as year FROM QamEnvironmentChangeForm c WHERE EXTRACT(YEAR_MONTH FROM c.createdDate) >= :fromMonthYear and EXTRACT(YEAR_MONTH FROM c.createdDate) <= :toMonthYear "
				+ "and c.macLookupId in (:macLookupIdList) and c.jurisdictionId in (:jurisdictionList) and c.recordStatus = 1 "
				+ "GROUP BY EXTRACT(YEAR_MONTH FROM c.createdDate)")
	    public List<Object[]> findMonthsByMonthYearRange(@Param("fromMonthYear") Integer fromMonthYear, @Param("toMonthYear") Integer toMonthYear,@Param("macLookupIdList")ArrayList<Long> macLookupArrayList,@Param("jurisdictionList")ArrayList<Long> jurisdictionArrayList);
	    
	    @Query("SELECT MONTHNAME(c.createdDate) as month, YEAR(c.createdDate) as year FROM QamEnvironmentChangeForm c WHERE EXTRACT(YEAR_MONTH FROM c.createdDate) >= :fromMonthYear and EXTRACT(YEAR_MONTH FROM c.createdDate) <= :toMonthYear "
				+ "and c.recordStatus = 1 "
				+ "GROUP BY EXTRACT(YEAR_MONTH FROM c.createdDate)")
	    public List<Object[]> findMonthsByMonthYearRangeAll(@Param("fromMonthYear") Integer fromMonthYear, @Param("toMonthYear") Integer toMonthYear);
	    
	    @Query("SELECT MONTHNAME(c.createdDate) as month, YEAR(c.createdDate) as year FROM QamEnvironmentChangeForm c WHERE EXTRACT(YEAR_MONTH FROM c.createdDate) >= :fromMonthYear and EXTRACT(YEAR_MONTH FROM c.createdDate) <= :toMonthYear "
				+ "and c.jurisdictionId in (:jurisdictionList) and c.recordStatus = 1 "
				+ "GROUP BY EXTRACT(YEAR_MONTH FROM c.createdDate)")
	    public List<Object[]> findMonthsByMonthYearRangeAllMac(@Param("fromMonthYear") Integer fromMonthYear, @Param("toMonthYear") Integer toMonthYear,@Param("jurisdictionList")ArrayList<Long> jurisdictionArrayList);
	    
	    @Query("SELECT MONTHNAME(c.createdDate) as month, YEAR(c.createdDate) as year FROM QamEnvironmentChangeForm c WHERE EXTRACT(YEAR_MONTH FROM c.createdDate) >= :fromMonthYear and EXTRACT(YEAR_MONTH FROM c.createdDate) <= :toMonthYear "
				+ "and c.macLookupId in (:macLookupIdList) and c.recordStatus = 1 "
				+ "GROUP BY EXTRACT(YEAR_MONTH FROM c.createdDate)")
	    public List<Object[]> findMonthsByMonthYearRangeAllJuris(@Param("fromMonthYear") Integer fromMonthYear, @Param("toMonthYear") Integer toMonthYear,@Param("macLookupIdList")ArrayList<Long> macLookupArrayList);
	    
	  //Queries to Find CSR Lists by From Date and To Date
		
		@Query("SELECT c FROM QamEnvironmentChangeForm c WHERE EXTRACT(YEAR_MONTH FROM c.createdDate) >= :fromMonthYear and EXTRACT(YEAR_MONTH FROM c.createdDate) <= :toMonthYear "
				+ "and c.macLookupId in (:macLookupIdList) and c.jurisdictionId in (:jurisdictionList) and c.recordStatus = 1")
	    public List<QamEnvironmentChangeForm> findByMonthYearRange(@Param("fromMonthYear") Integer fromMonthYear, @Param("toMonthYear") Integer toMonthYear,@Param("macLookupIdList")ArrayList<Long> macLookupArrayList,@Param("jurisdictionList")ArrayList<Long> jurisdictionArrayList);
		
		@Query("SELECT c FROM QamEnvironmentChangeForm c WHERE EXTRACT(YEAR_MONTH FROM c.createdDate) >= :fromMonthYear and EXTRACT(YEAR_MONTH FROM c.createdDate) <= :toMonthYear "
				+ "and c.recordStatus = 1")
	    public List<QamEnvironmentChangeForm> findByMonthYearRangeAll(@Param("fromMonthYear") Integer fromMonthYear, @Param("toMonthYear") Integer toMonthYear);
		
		@Query("SELECT c FROM QamEnvironmentChangeForm c WHERE EXTRACT(YEAR_MONTH FROM c.createdDate) >= :fromMonthYear and EXTRACT(YEAR_MONTH FROM c.createdDate) <= :toMonthYear "
				+ "and c.jurisdictionId in (:jurisdictionList) and c.recordStatus = 1")
	    public List<QamEnvironmentChangeForm> findByMonthYearRangeAllMac(@Param("fromMonthYear") Integer fromMonthYear, @Param("toMonthYear") Integer toMonthYear,@Param("jurisdictionList")ArrayList<Long> jurisdictionArrayList);
		
		@Query("SELECT c FROM QamEnvironmentChangeForm c WHERE EXTRACT(YEAR_MONTH FROM c.createdDate) >= :fromMonthYear and EXTRACT(YEAR_MONTH FROM c.createdDate) <= :toMonthYear "
				+ "and c.macLookupId in (:macLookupIdList) and c.recordStatus = 1")
	    public List<QamEnvironmentChangeForm> findByMonthYearRangeAllJuris(@Param("fromMonthYear") Integer fromMonthYear, @Param("toMonthYear") Integer toMonthYear,@Param("macLookupIdList")ArrayList<Long> macLookupArrayList);
		
}
