package com.archsystemsinc.qam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.archsystemsinc.qam.model.MacAssignmentObject;

/**
 */
public interface MacAssignmentObjectRepository extends JpaRepository<MacAssignmentObject, Integer>, JpaSpecificationExecutor<MacAssignmentObject> {
	
	@Query("SELECT MONTHNAME(c.createdDate) as month, YEAR(c.createdDate) as year FROM MacAssignmentObject c WHERE EXTRACT(YEAR_MONTH FROM c.createdDate) <= :toMonthYear "
				+ "GROUP BY EXTRACT(YEAR_MONTH FROM c.createdDate)")
    public List<Object[]> findMonthsByMonthYearRange(@Param("toMonthYear") Integer toMonthYear);
    
}