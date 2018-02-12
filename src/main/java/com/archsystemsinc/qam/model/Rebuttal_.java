package com.archsystemsinc.qam.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel (Rebuttal.class)
public abstract class Rebuttal_ {
	
	public static volatile SingularAttribute<Rebuttal, Long> id;
	public static volatile SingularAttribute<Rebuttal, Integer> userId;
	public static volatile SingularAttribute<Rebuttal, Integer> macId;
	public static volatile SingularAttribute<Rebuttal, Integer> jurisId;
}