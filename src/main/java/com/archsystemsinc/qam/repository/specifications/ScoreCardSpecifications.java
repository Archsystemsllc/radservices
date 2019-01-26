package com.archsystemsinc.qam.repository.specifications;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.archsystemsinc.qam.model.RadUser_;
import com.archsystemsinc.qam.model.ScoreCard;
import com.archsystemsinc.qam.model.ScoreCard_;

public final class ScoreCardSpecifications {

	private ScoreCardSpecifications() {
		throw new AssertionError();
	}
	
	public static Specification<ScoreCard> searchById(final Integer id) {
		return new Specification<ScoreCard>() {
			@Override
			public final Predicate toPredicate(final Root<ScoreCard> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				
				if(id != null && id != 0) { 
					final Predicate matchingById = builder.equal(root.get(ScoreCard_.id), id);
					return matchingById;
				} else 
					return null;			
			}
		};
	}
	
	// API
	public static Specification<ScoreCard> searchByFailureReason(final String failureString) {
		return new Specification<ScoreCard>() {
			@Override
			public final Predicate toPredicate(final Root<ScoreCard> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(failureString != null && !failureString.equalsIgnoreCase("")) {
					final Predicate matchingByFailureString = builder.like(root.get(ScoreCard_.finalScoreCardStatus), failureString );
					return matchingByFailureString;
				} else 
					return null;
			}
		};
	}
	
	
	public static Specification<ScoreCard> searchByCallResult(final String callResult) {
		return new Specification<ScoreCard>() {
			@Override
			public final Predicate toPredicate(final Root<ScoreCard> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(callResult != null && !callResult.equalsIgnoreCase("") && !callResult.equalsIgnoreCase("ALL")) {
					final Predicate matchingCallResult = builder.like(root.get(ScoreCard_.finalScoreCardStatus), "%" + callResult + "%");
					return matchingCallResult;
				} else 
					return null;
			}
		};
	}	
	
	
	public static Specification<ScoreCard> searchByCallResultList(final ArrayList<String> callResultList) {
		return new Specification<ScoreCard>() {
			@Override
			public final Predicate toPredicate(final Root<ScoreCard> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				
				if(callResultList != null && callResultList.size() > 0) {
					
					Expression<String> exp = root.get(ScoreCard_.callResult);
					final Predicate matchingByCallResultList = exp.in(callResultList);
					return matchingByCallResultList;
					
				} else 
					return null;
			}
		};
	}
	
	public static Specification<ScoreCard> searchByQamCalibrationStatus(final String qamCalibrationStatus) {
		return new Specification<ScoreCard>() {
			@Override
			public final Predicate toPredicate(final Root<ScoreCard> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(qamCalibrationStatus != null && !qamCalibrationStatus.equalsIgnoreCase("") && !qamCalibrationStatus.equalsIgnoreCase("ALL")) {
					final Predicate matchingQamCalibrationStatus = builder.like(root.get(ScoreCard_.qamCalibrationStatus),"%" + qamCalibrationStatus + "%");
					return matchingQamCalibrationStatus;
				} else 
					return null;
			}
		};
	}	
	
	
	public static Specification<ScoreCard> searchByCmsCalibrationStatus(final String cmsCalibrationStatus) {
		return new Specification<ScoreCard>() {
			@Override
			public final Predicate toPredicate(final Root<ScoreCard> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(cmsCalibrationStatus != null && !cmsCalibrationStatus.equalsIgnoreCase("") && !cmsCalibrationStatus.equalsIgnoreCase("ALL")) {
					final Predicate matchingCmsCalibrationStatus = builder.like(root.get(ScoreCard_.cmsCalibrationStatus),"%" + cmsCalibrationStatus + "%");
					return matchingCmsCalibrationStatus;
				} else 
					return null;
			}
		};
	}	
	
	public static Specification<ScoreCard> searchByFinalScoreCardStatus(final String finalScoreCardStatus) {
		return new Specification<ScoreCard>() {
			@Override
			public final Predicate toPredicate(final Root<ScoreCard> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(finalScoreCardStatus != null && !finalScoreCardStatus.equalsIgnoreCase("") && !finalScoreCardStatus.equalsIgnoreCase("ALL")) {
					final Predicate matchingFinalScoreCardStatus = builder.like(root.get(ScoreCard_.finalScoreCardStatus), "%" + finalScoreCardStatus + "%");
					return matchingFinalScoreCardStatus;
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
					final Predicate matchingByQamFullName = builder.like(root.get(ScoreCard_.qamFullName),"%" + qamFullName + "%");
					return matchingByQamFullName;
				} else 
					return null;
			}
		};
	}
	
	public static Specification<ScoreCard> searchByMacId(final Integer macId) {
		return new Specification<ScoreCard>() {
			@Override
			public final Predicate toPredicate(final Root<ScoreCard> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				
				if(macId != null && macId != 0) { 
					final Predicate matchingByMacId = builder.equal(root.get(ScoreCard_.macId), macId);
					return matchingByMacId;
				} else 
					return null;			
			}
		};
	}
	
	public static Specification<ScoreCard> searchByJurisdictionId(final Integer jurisdictionId) {
		return new Specification<ScoreCard>() {
			@Override
			public final Predicate toPredicate(final Root<ScoreCard> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				
				if(jurisdictionId != null && jurisdictionId != 0) { 
					final Predicate matchingByMacId = builder.equal(root.get(ScoreCard_.jurId), jurisdictionId);
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
				
				if(macIdList != null && macIdList.size() > 0) {
					
					Expression<Integer> exp = root.get(ScoreCard_.macId);
					final Predicate matchingByMacIdList = exp.in(macIdList);
					return matchingByMacIdList;
					
				} else 
					return null;
			}
		};
	}	
	
	public static Specification<ScoreCard> searchByJurIdList(final ArrayList<Integer> jurIdList) {
		return new Specification<ScoreCard>() {
			@Override
			public final Predicate toPredicate(final Root<ScoreCard> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				
				if(jurIdList != null && jurIdList.size() > 0) {
					
					Expression<Integer> exp = root.get(ScoreCard_.jurId);
					final Predicate matchingByJurIdList = exp.in(jurIdList);
					return matchingByJurIdList;
					
				} else 
					return null;
			}
		};
	}
	
	public static Specification<ScoreCard> searchByProgramIdList(final ArrayList<Integer> programIdList) {
		return new Specification<ScoreCard>() {
			@Override
			public final Predicate toPredicate(final Root<ScoreCard> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				
				if(programIdList != null && programIdList.size() > 0) {
					
					Expression<Integer> exp = root.get(ScoreCard_.programId);
					final Predicate matchingByProgramIdList = exp.in(programIdList);
					return matchingByProgramIdList;
					
				} else 
					return null;
			}
		};
	}
	
	public static Specification<ScoreCard> searchByProgramId(final Integer programId) {
		return new Specification<ScoreCard>() {
			@Override
			public final Predicate toPredicate(final Root<ScoreCard> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				
				if(programId != null && programId != 0) { 
					final Predicate matchingByProgramId = builder.equal(root.get(ScoreCard_.programId), programId);
					return matchingByProgramId;
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
					final Predicate matchingByScorecardType = builder.equal(root.get(ScoreCard_.scorecardType),scorecardType);
					return matchingByScorecardType;
				} else 
					return null;				
			}
		};
	}		
	
	public static Specification<ScoreCard> searchByUserId(final Integer userId) {
		return new Specification<ScoreCard>() {
			@Override
			public final Predicate toPredicate(final Root<ScoreCard> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(userId != null && userId != 0) {
					final Predicate matchingByUserId = builder.equal(root.get(ScoreCard_.userId), userId);
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
	
	/*public static Specification<ScoreCard> findByQamStartdateTimeBetween(final Date filterFromDate, final Date filterToDate) {
		return new Specification<ScoreCard>() {
			@Override
			public final Predicate toPredicate(final Root<ScoreCard> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				Predicate matchingByQamStartdateTime = null;
				
				if(filterFromDate != null && filterToDate != null){					
					matchingByQamStartdateTime = builder.between(root.get(ScoreCard_.qamStartdateTime), filterFromDate, filterToDate);
				}
				return matchingByQamStartdateTime;							
			}
		};
	}*/
	
	/*
	 * Select the eps inbetween dates
	 * @query SELECT * FROM cb_cmts_prod.eps where created_date between '2015-05-01' AND '2015-05-06' ;
	 */
	
	public static Specification<ScoreCard> findByCallMonitoringDateBetween(final Date filterFromDate, final Date filterToDate) {
		return new Specification<ScoreCard>() {
			@Override
			public final Predicate toPredicate(final Root<ScoreCard> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				Predicate matchingByQamStartdateTime = null;
				final List<Predicate> matchingByQamMonitoringDates = new ArrayList<>();
				
				if(filterFromDate != null && filterToDate != null){			
					Expression<Date> dateStringExpr = builder.function("STR_TO_DATE", Date.class, root.get(ScoreCard_.callMonitoringDate), builder.literal("%m/%d/%Y"));
					//matchingByQamStartdateTime = builder.between(dateStringExpr, filterFromDate, filterToDate);
					Predicate startPredicate = builder.greaterThanOrEqualTo(dateStringExpr, filterFromDate);
					Predicate endPredicate = builder.lessThanOrEqualTo(dateStringExpr, filterToDate);
					matchingByQamMonitoringDates.add(startPredicate);
					matchingByQamMonitoringDates.add(endPredicate);
				}
				
				return builder.and(matchingByQamMonitoringDates.toArray(new Predicate[matchingByQamMonitoringDates.size()]));
				//return matchingByQamStartdateTime;				
			}
		};
	}
	
	
	
	/*
	 * Select the eps inbetween dates
	 * @query SELECT * FROM cb_cmts_prod.eps where created_date between '2015-05-01' AND '2015-05-06' ;
	 */
	
	/*public static Specification<ScoreCard> findByUptoFilterToDate(final Date filterToDate) {
		return new Specification<ScoreCard>() {
			@Override
			public final Predicate toPredicate(final Root<ScoreCard> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				Predicate matchingByQamStartdateTime = null;
				
				if(filterToDate != null){					
					matchingByQamStartdateTime = builder.lessThanOrEqualTo(root.get(ScoreCard_.qamStartdateTime), filterToDate);
				}
				return matchingByQamStartdateTime;							
			}
		};
	}*/
}
