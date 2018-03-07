package com.archsystemsinc.qam.model;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel (ScoreCard.class)
public abstract class ScoreCard_ {
	
	public static volatile SingularAttribute<ScoreCard, Long> id;
	public static volatile SingularAttribute<ScoreCard, String> callResult;
	public static volatile SingularAttribute<ScoreCard, String> qamFullName;
	public static volatile SingularAttribute<ScoreCard, Integer> jurId;
	public static volatile SingularAttribute<ScoreCard, Integer> macId;
	public static volatile SingularAttribute<ScoreCard, Integer> programId;
	public static volatile SingularAttribute<ScoreCard, Integer> recordStatus;
	public static volatile SingularAttribute<ScoreCard, String> macCallReferenceNumber;
	public static volatile SingularAttribute<ScoreCard, String> scorecardType;
	public static volatile SingularAttribute<ScoreCard, Date> qamEnddateTime;	
	public static volatile SingularAttribute<ScoreCard, Date> qamStartdateTime;
	public static volatile SingularAttribute<ScoreCard, Integer> userId;
	public static volatile SingularAttribute<ScoreCard, String> scorecardStatus;
	public static volatile SingularAttribute<ScoreCard, String> qamCalibrationStatus;
	public static volatile SingularAttribute<ScoreCard, String> cmsCalibrationStatus;
	
}