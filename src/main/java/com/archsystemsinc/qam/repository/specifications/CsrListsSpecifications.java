package com.archsystemsinc.qam.repository.specifications;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.archsystemsinc.qam.model.CsrLists;
import com.archsystemsinc.qam.model.CsrLists_;

public final class CsrListsSpecifications {

	private CsrListsSpecifications() {
		throw new AssertionError();
	}
	
	// API
	
	
	public static Specification<CsrLists> searchByMacId(final Integer macId) {
		return new Specification<CsrLists>() {
			@Override
			public final Predicate toPredicate(final Root<CsrLists> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(macId != null ) {
					final Predicate matchingByMacId = builder.equal(root.get(CsrLists_.macLookupId), macId);
					return matchingByMacId;
				} else 
					return null;
			}
		};
	}
	
	public static Specification<CsrLists> searchByRecordStatus(final Long recordStatus) {
		return new Specification<CsrLists>() {
			@Override
			public final Predicate toPredicate(final Root<CsrLists> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(recordStatus != null ) {
					final Predicate matchingByMacId = builder.equal(root.get(CsrLists_.recordStatus), recordStatus);
					return matchingByMacId;
				} else 
					return null;
			}
		};
	}
	
	public static Specification<CsrLists> searchByMacIdList(final ArrayList<Integer> macIdList) {
		return new Specification<CsrLists>() {
			@Override
			public final Predicate toPredicate(final Root<CsrLists> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				
				if(macIdList != null ) {
					
					Expression<Long> exp = root.get(CsrLists_.macLookupId);
					final Predicate matchingByMacIdList = exp.in(macIdList);
					return matchingByMacIdList;
					
				} else 
					return null;
			}
		};
	}
	
	public static Specification<CsrLists> searchByJurisdictionList(final ArrayList<String> jurisdictionList) {
		return new Specification<CsrLists>() {
			@Override
			public final Predicate toPredicate(final Root<CsrLists> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				
				if(jurisdictionList != null ) {
					
					Expression<String> exp = root.get(CsrLists_.jurisdiction);
					final Predicate matchingByJurisdictionList = exp.in(jurisdictionList);
					return matchingByJurisdictionList;
					
				} else 
					return null;
			}
		};
	}
	
	public static Specification<CsrLists> searchByJurisdiction(final String jurisdiction) {
		return new Specification<CsrLists>() {
			@Override
			public final Predicate toPredicate(final Root<CsrLists> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(jurisdiction != null ) {
					final Predicate matchingByJurisdiction = builder.equal(root.get(CsrLists_.jurisdiction), jurisdiction);
					return matchingByJurisdiction;
				} else 
					return null;
			}
		};
	}
	
	public static Specification<CsrLists> searchByProgram(final String program) {
		return new Specification<CsrLists>() {
			@Override
			public final Predicate toPredicate(final Root<CsrLists> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(program != null ) {
					final Predicate matchingByProgramId = builder.equal(root.get(CsrLists_.program), program);
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
	
	public static Specification<CsrLists> findByCreatedDateBetween(final Date filterFromDate, final Date filterToDate) {
		return new Specification<CsrLists>() {
			@Override
			public final Predicate toPredicate(final Root<CsrLists> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				Predicate matchingByCreatedDate = null;
				
				if(filterFromDate != null ){					
					/*matchingByCreatedDate = builder.between(root.get(CsrLists_.createdDate), filterFromDate, filterToDate);
					Expression<String> exp = root.get(CsrLists_.jurisdiction);*/
					matchingByCreatedDate = builder.equal(builder.function("YEAR_MONTH", Integer.class, root.get(CsrLists_.createdDate)), filterFromDate);
				}
				return matchingByCreatedDate;		
				
				
				/*CriteriaQuery<CsrLists> cq = builder.createQuery(CsrLists.class);
				Root<CsrLists> exRateLine = cq.from(CsrLists.class);
				cq.where(builder.equal(builder.function("year", Integer.class, exRateLine.get(CsrLists_.createdDate)), filterFromDate));
				//.and(builder.equal(builder.function("month", Integer.class, exRateLine.get(CsrLists_.createdDate)), filterToDate)));
				return null;*/
			}			
		};
	}
	
	
	

}
