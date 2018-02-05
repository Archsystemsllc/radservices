package com.archsystemsinc.qam.repository.specifications;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.archsystemsinc.qam.model.ScoreCard;
import com.archsystemsinc.qam.model.ScoreCard_;

public final class ScoreCardSpecifications {

	private ScoreCardSpecifications() {
		throw new AssertionError();
	}
	
	// API
	public static Specification<ScoreCard> searchByCallResult(final String callResult) {
		return new Specification<ScoreCard>() {
			@Override
			public final Predicate toPredicate(final Root<ScoreCard> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(callResult != null && !callResult.equalsIgnoreCase("") && !callResult.equalsIgnoreCase("ALL")) {
					final Predicate matchingCallResult = builder.like(root.get(ScoreCard_.callResult), callResult + "%");
					return matchingCallResult;
				} else 
					return null;
			}
		};
	}	
	
	public static Specification<ScoreCard> searchByQamFullName(final String qamFullName) {
		return new Specification<ScoreCard>() {
			@Override
			public final Predicate toPredicate(final Root<ScoreCard> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(qamFullName != null && !qamFullName.equalsIgnoreCase("")) {
					final Predicate matchingByQamFullName = builder.like(root.get(ScoreCard_.qamFullName), qamFullName + "%");
					return matchingByQamFullName;
				} else 
					return null;
			}
		};
	}
	
	public static Specification<ScoreCard> searchByMacId(final String macIdReportString) {
		return new Specification<ScoreCard>() {
			@Override
			public final Predicate toPredicate(final Root<ScoreCard> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				
				if(macIdReportString != null && !macIdReportString.equalsIgnoreCase("") && !macIdReportString.equalsIgnoreCase("ALL")) {
					final Predicate matchingByMacId = builder.equal(root.get(ScoreCard_.macId), Integer.valueOf(macIdReportString));
					return matchingByMacId;
				} else 
					return null;			
			}
		};
	}
	
	public static Specification<ScoreCard> searchByMacIdList(final ArrayList<Integer> macIdList) {
		return new Specification<ScoreCard>() {
			@Override
			public final Predicate toPredicate(final Root<ScoreCard> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				
				if(macIdList != null ) {
					
					Expression<Integer> exp = root.get(ScoreCard_.macId);
					final Predicate matchingByMacIdList = exp.in(macIdList);
					return matchingByMacIdList;
					
				} else 
					return null;
			}
		};
	}
	
	public static Specification<ScoreCard> searchByJurId(final String jurIdReportString) {
		return new Specification<ScoreCard>() {
			@Override
			public final Predicate toPredicate(final Root<ScoreCard> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(jurIdReportString != null && !jurIdReportString.equalsIgnoreCase("") && !jurIdReportString.equalsIgnoreCase("ALL")) {
					final Predicate matchingByJurId =  builder.equal(root.get(ScoreCard_.jurId), Integer.valueOf(jurIdReportString));
					return matchingByJurId;
				} else 
					return null;
			}
		};
	}
	
	public static Specification<ScoreCard> searchByProgramId(final String programIdString) {
		return new Specification<ScoreCard>() {
			@Override
			public final Predicate toPredicate(final Root<ScoreCard> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(programIdString != null && !programIdString.equalsIgnoreCase("") && !programIdString.equalsIgnoreCase("ALL")) {				
					final Predicate matchingByProgramId = builder.equal(root.get(ScoreCard_.programId), Integer.valueOf(programIdString));
					return matchingByProgramId;
				} else 
					return null;
			}
		};
	}
	
	
	public static Specification<ScoreCard> searchByScorecardType(final String scorecardType) {
		return new Specification<ScoreCard>() {
			@Override
			public final Predicate toPredicate(final Root<ScoreCard> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(scorecardType != null && !scorecardType.equalsIgnoreCase("") && !scorecardType.equalsIgnoreCase("ALL")) {
					final Predicate matchingByScorecardType = builder.like(root.get(ScoreCard_.scorecardType), scorecardType + "%");
					return matchingByScorecardType;
				} else 
					return null;				
			}
		};
	}		
		
	
	/*
	 * Select the eps inbetween dates
	 * @query SELECT * FROM cb_cmts_prod.eps where created_date between '2015-05-01' AND '2015-05-06' ;
	 */
	
	public static Specification<ScoreCard> findByQamEnddateTimeBetween(final Date filterFromDate, final Date filterToDate) {
		return new Specification<ScoreCard>() {
			@Override
			public final Predicate toPredicate(final Root<ScoreCard> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				Predicate matchingByQamEndDateTime = null;
				
				if(filterFromDate != null && filterToDate != null){					
					matchingByQamEndDateTime = builder.between(root.get(ScoreCard_.qamEnddateTime), filterFromDate, filterToDate);
				}
				return matchingByQamEndDateTime;							
			}
		};
	}
	
	
	

}
