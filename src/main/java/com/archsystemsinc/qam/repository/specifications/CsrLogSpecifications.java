package com.archsystemsinc.qam.repository.specifications;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.archsystemsinc.qam.model.CsrLog;
import com.archsystemsinc.qam.model.CsrLog_;


public final class CsrLogSpecifications {

	private CsrLogSpecifications() {
		throw new AssertionError();
	}
	
	// API	
	public static Specification<CsrLog> searchByJurisdiction(final String jurisdiction) {
		return new Specification<CsrLog>() {
			@Override
			public final Predicate toPredicate(final Root<CsrLog> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(jurisdiction != null && !jurisdiction.equalsIgnoreCase("") && !jurisdiction.equalsIgnoreCase("ALL")) {
					final Predicate matchingJurisdiction = builder.like(root.get(CsrLog_.jurisdiction), jurisdiction + "%");
					return matchingJurisdiction;
				} else 
					return null;
			}
		};
	}	
	
	public static Specification<CsrLog> searchByMacId(final Integer macId) {
		return new Specification<CsrLog>() {
			@Override
			public final Predicate toPredicate(final Root<CsrLog> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				
				if(macId != null && macId != 0) { 
					final Predicate matchingByMacId = builder.equal(root.get(CsrLog_.macId), macId);
					return matchingByMacId;
				} else 
					return null;			
			}
		};
	}
	
	public static Specification<CsrLog> searchByMacIdList(final ArrayList<Integer> macIdList) {
		return new Specification<CsrLog>() {
			@Override
			public final Predicate toPredicate(final Root<CsrLog> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				
				if(macIdList != null ) {
					
					Expression<Integer> exp = root.get(CsrLog_.macId);
					final Predicate matchingByMacIdList = exp.in(macIdList);
					return matchingByMacIdList;
					
				} else 
					return null;
			}
		};
	}
	
	public static Specification<CsrLog> searchByJurisdictionNameList(final ArrayList<String> jurisdictionNameList) {
		return new Specification<CsrLog>() {
			@Override
			public final Predicate toPredicate(final Root<CsrLog> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				
				if(jurisdictionNameList != null ) {
					
					Expression<String> exp = root.get(CsrLog_.jurisdiction);
					final Predicate matchingByJurisdictionNameList = exp.in(jurisdictionNameList);
					return matchingByJurisdictionNameList;
					
				} else 
					return null;
			}
		};
	}
	
	
	
	public static Specification<CsrLog> searchByUserId(final Long userId) {
		return new Specification<CsrLog>() {
			@Override
			public final Predicate toPredicate(final Root<CsrLog> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(userId != null && userId != 0) {
					final Predicate matchingByUserId = builder.equal(root.get(CsrLog_.userId), userId);
					return matchingByUserId;
				} else 
					return null;				
			}
		};
	}		
		
	
	/*
	 * Select the eps inbetween dates
	 * @query SELECT * FROM cb_cmts_prod.eps where created_date between '2015-05-01' AND '2015-05-06' ;
	 */
	
	public static Specification<CsrLog> findByCreatedDateBetween(final Date fromDate, final Date toDate) {
		return new Specification<CsrLog>() {
			@Override
			public final Predicate toPredicate(final Root<CsrLog> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				Predicate matchingByCreatedDateBetween = null;
				
				if(fromDate != null && toDate != null){					
					matchingByCreatedDateBetween = builder.between(root.get(CsrLog_.createdDate), fromDate, toDate);
				}
				return matchingByCreatedDateBetween;							
			}
		};
	}
	
	
	

}
