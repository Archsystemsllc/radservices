package com.archsystemsinc.qam.model;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel (CsrLog.class)
public abstract class CsrLog_ {
	
	public static volatile SingularAttribute<CsrLog, Long> id;
	public static volatile SingularAttribute<CsrLog, String> jurisdiction;
	public static volatile SingularAttribute<CsrLog, Integer> macId;
	public static volatile SingularAttribute<CsrLog, Long> complianceStatus;
	public static volatile SingularAttribute<CsrLog, Long> uploadStatus;
	public static volatile SingularAttribute<CsrLog, Date> createdDate;
	public static volatile SingularAttribute<CsrLog, Integer> userId;	
}