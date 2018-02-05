package com.archsystemsinc.qam.model;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel (CsrLists.class)
public abstract class CsrLists_ {
	
	public static volatile SingularAttribute<CsrLists, Long> id;
	public static volatile SingularAttribute<CsrLists, Long> macLookupId;	
	public static volatile SingularAttribute<CsrLists, String> jurisdiction;
	public static volatile SingularAttribute<CsrLists, String> program;
	public static volatile SingularAttribute<CsrLists, Date> createdDate;
	public static volatile SingularAttribute<CsrLists, Long> recordStatus;
	
	
}