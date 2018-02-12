package com.archsystemsinc.qam.repository.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.archsystemsinc.qam.model.Rebuttal;
import com.archsystemsinc.qam.model.Rebuttal_;
import com.archsystemsinc.qam.model.ScoreCard;
import com.archsystemsinc.qam.model.ScoreCard_;

public final class RebuttalSpecifications {

	private RebuttalSpecifications() {
		throw new AssertionError();
	}
	
	// API	
	public static Specification<Rebuttal> searchByUserId(final Integer userId) {
		return new Specification<Rebuttal>() {
			@Override
			public final Predicate toPredicate(final Root<Rebuttal> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(userId != null && userId != 0) {
					final Predicate matchingByUserId = builder.equal(root.get(Rebuttal_.userId), userId);
					return matchingByUserId;
				} else 
					return null;				
			}
		};
	}
	
	public static Specification<Rebuttal> searchByJurId(final Integer jurisId) {
		return new Specification<Rebuttal>() {
			@Override
			public final Predicate toPredicate(final Root<Rebuttal> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(jurisId != null && jurisId != 0) {
					final Predicate matchingByJurId =  builder.equal(root.get(Rebuttal_.jurisId), jurisId);
					return matchingByJurId;
				} else 
					return null;
			}
		};
	}
	
	public static Specification<Rebuttal> searchByMacId(final Integer macId) {
		return new Specification<Rebuttal>() {
			@Override
			public final Predicate toPredicate(final Root<Rebuttal> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(macId != null && macId != 0) {
					final Predicate matchingByMacId =  builder.equal(root.get(Rebuttal_.macId), macId);
					return matchingByMacId;
				} else 
					return null;
			}
		};
	}

}