package com.archsystemsinc.qam.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.archsystemsinc.qam.model.CsrList;
import com.archsystemsinc.qam.model.CsrLists;

/**
 */
public interface CsrListRepository extends JpaRepository<CsrLists, Long>{
	
	public List<CsrList> findByCreatedDateBetween(Date from, Date to);
	
	@Query("SELECT c FROM CsrLists c WHERE EXTRACT(YEAR_MONTH FROM c.createdDate) >= :fromMonthYear and EXTRACT(YEAR_MONTH FROM c.createdDate) <= :toMonthYear and c.recordStatus = 1")
    public List<CsrLists> findByMonthYearRange(@Param("fromMonthYear") Integer fromMonthYear, @Param("toMonthYear") Integer toMonthYear);
	
	@Query("SELECT MONTHNAME(c.createdDate) as month, YEAR(c.createdDate) as year FROM CsrLists c WHERE EXTRACT(YEAR_MONTH FROM c.createdDate) >= :fromMonthYear and EXTRACT(YEAR_MONTH FROM c.createdDate) <= :toMonthYear and c.recordStatus = 1 "
			+ "GROUP BY EXTRACT(YEAR_MONTH FROM c.createdDate)")
    public List<Object[]> findMonthsByMonthYearRange(@Param("fromMonthYear") Integer fromMonthYear, @Param("toMonthYear") Integer toMonthYear);

	@Query("SELECT c FROM CsrLists c WHERE c.userId = :userId and EXTRACT(YEAR_MONTH FROM c.createdDate) = :monthYear and c.recordStatus = 1")
    public List<CsrLists> existingCsrListByUserMonthYear(@Param("userId") Long userId,@Param("monthYear") Integer monthYear);

	@Modifying
	@Query("update CsrLists c set c.recordStatus = :status, c.updateddDate = :updatedDate where c.userId = :userId and EXTRACT(YEAR_MONTH FROM c.createdDate) = :monthYear")
	int markStatusDeleted(@Param("status") Long status, @Param("userId") Long userId,@Param("monthYear") Integer monthYear,@Param("updatedDate") Date updatedDate);
}
