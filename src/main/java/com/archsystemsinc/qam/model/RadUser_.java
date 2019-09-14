package com.archsystemsinc.qam.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel (RadUser.class)
public abstract class RadUser_ {
	
	public static volatile SingularAttribute<RadUser, Long> id;
	public static volatile SingularAttribute<RadUser, String> userName;
	public static volatile SingularAttribute<RadUser, Long> macId;
	public static volatile SingularAttribute<RadUser, String> jurId;
	public static volatile SingularAttribute<RadUser, Role> role;
	public static volatile SingularAttribute<RadUser, String> lastName;
	public static volatile SingularAttribute<RadUser, OrganizationLookup> organizationLookup;
	public static volatile SingularAttribute<RadUser, Long> status;
}