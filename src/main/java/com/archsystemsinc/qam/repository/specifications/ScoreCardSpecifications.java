package com.archsystemsinc.qam.repository.specifications;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.archsystemsinc.qam.model.ScoreCard;
import com.archsystemsinc.qam.model.ScoreCard_;
import com.sun.org.apache.xerces.internal.util.Status;

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
	
	public static Specification<ScoreCard> searchByJurId(final Integer jurId) {
		return new Specification<ScoreCard>() {
			@Override
			public final Predicate toPredicate(final Root<ScoreCard> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(jurId != null ) {
					final Predicate matchingByJurId = builder.equal(root.get(ScoreCard_.jurId), jurId);
					return matchingByJurId;
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
	
	public static Specification<ScoreCard> findByQamEndDateBetween(final ScoreCard scoreCard) {
		return new Specification<ScoreCard>() {
			@Override
			public final Predicate toPredicate(final Root<ScoreCard> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				Predicate matchingByQamEndDateTime = null;
				
				if(scoreCard.getFilterFromDate() != null ){
					matchingByQamEndDateTime = builder.between(root.get(ScoreCard_.qamEnddateTime),scoreCard.getFilterFromDate(), scoreCard.getFilterToDate());
				
				}
				return matchingByQamEndDateTime;							
			}
		};
	}
	
	
	

}
