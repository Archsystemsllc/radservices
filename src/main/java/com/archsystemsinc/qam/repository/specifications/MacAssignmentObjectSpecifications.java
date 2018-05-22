package com.archsystemsinc.qam.repository.specifications;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.archsystemsinc.qam.model.MacAssignmentObject;
import com.archsystemsinc.qam.model.MacAssignmentObject_;

public final class MacAssignmentObjectSpecifications {

	private MacAssignmentObjectSpecifications() {
		throw new AssertionError();
	}
	
	// API
	
	
	public static Specification<MacAssignmentObject> searchByMacName(final String macName) {
		return new Specification<MacAssignmentObject>() {
			@Override
			public final Predicate toPredicate(final Root<MacAssignmentObject> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(macName != null && !macName.equalsIgnoreCase("")) {
					final Predicate matchingByMacId = builder.equal(root.get(MacAssignmentObject_.macName), macName);
					return matchingByMacId;
				} else 
					return null;
			}
		};
	}
	
	
	public static Specification<MacAssignmentObject> searchByJurisdiction(final String jurisdictionName) {
		return new Specification<MacAssignmentObject>() {
			@Override
			public final Predicate toPredicate(final Root<MacAssignmentObject> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(jurisdictionName != null && !jurisdictionName.equalsIgnoreCase("")) {
					final Predicate matchingByJurisdiction = builder.equal(root.get(MacAssignmentObject_.jurisdictionName), jurisdictionName);
					return matchingByJurisdiction;
				} else 
					return null;
			}
		};
	}
	
	public static Specification<MacAssignmentObject> searchByProgram(final String programName) {
		return new Specification<MacAssignmentObject>() {
			@Override
			public final Predicate toPredicate(final Root<MacAssignmentObject> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(programName != null && !programName.equalsIgnoreCase("")) {
					final Predicate matchingByProgramId = builder.equal(root.get(MacAssignmentObject_.programName), programName);
					return matchingByProgramId;
				} else 
					return null;
			}
		};
	}
	
	/*
	 * Select the eps inbetween dates
	 * @query SELECT * FROM cb_cmts_prod.eps where created_date between '2015-05-01' AND '2015-05-06' ;
	 */
	
	public static Specification<MacAssignmentObject> findByCreatedDateBetween(final Date filterFromDate, final Date filterToDate) {
		return new Specification<MacAssignmentObject>() {
			@Override
			public final Predicate toPredicate(final Root<MacAssignmentObject> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				Predicate matchingByCreatedDate = null;
				
				if(filterFromDate != null ){					
					
					matchingByCreatedDate = builder.equal(builder.function("YEAR_MONTH", Integer.class, root.get(MacAssignmentObject_.createdDate)), filterFromDate);
				}
				return matchingByCreatedDate;				
				
			}			
		};
	}
	
	public static Specification<MacAssignmentObject> findByCurrentMonthYear(final String monthYear) {
		return new Specification<MacAssignmentObject>() {
			@Override
			public final Predicate toPredicate(final Root<MacAssignmentObject> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				Predicate matchingByMonthYear = null;
				
				if(monthYear != null && !monthYear.equalsIgnoreCase("")){					
					
					matchingByMonthYear = builder.equal(root.get(MacAssignmentObject_.assignedMonthYear), monthYear);
					
				}
				return matchingByMonthYear;				
				
			}			
		};
	}

	
	
	public static Specification<MacAssignmentObject> searchByCreatedDateByMonth(Date dateObject) {
		return new Specification<MacAssignmentObject>() {
			@Override
			public final Predicate toPredicate(final Root<MacAssignmentObject> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				
				Predicate matchingByCreatedDate = null;
				if(dateObject != null ) {
					matchingByCreatedDate = builder.equal(builder.function("YEAR_MONTH", Integer.class, root.get(MacAssignmentObject_.createdDate)), dateObject);
					
					return matchingByCreatedDate;
				} else 
					return null;				
			}
		};
	}		
	

}
