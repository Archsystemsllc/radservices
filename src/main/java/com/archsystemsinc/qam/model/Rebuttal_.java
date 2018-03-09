package com.archsystemsinc.qam.model;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel (Rebuttal.class)
public abstract class Rebuttal_ {
	
	public static volatile SingularAttribute<Rebuttal, Long> id;
	public static volatile SingularAttribute<Rebuttal, Integer> userId;
	public static volatile SingularAttribute<Rebuttal, Integer> macId;
	public static volatile SingularAttribute<Rebuttal, Integer> jurisId;
	public static volatile SingularAttribute<Rebuttal, String> callCategory;
	public static volatile SingularAttribute<Rebuttal, String> rebuttalStatus;
	public static volatile SingularAttribute<Rebuttal, Date> datePosted;
	
	
}