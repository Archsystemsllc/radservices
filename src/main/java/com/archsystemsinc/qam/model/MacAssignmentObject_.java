package com.archsystemsinc.qam.model;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel (MacAssignmentObject.class)
public abstract class MacAssignmentObject_ {
	
	public static volatile SingularAttribute<MacAssignmentObject, Integer> id;
	
	public static volatile SingularAttribute<MacAssignmentObject, String> jurisdictionName;
	public static volatile SingularAttribute<MacAssignmentObject, String> macName;
	public static volatile SingularAttribute<MacAssignmentObject, String> programName;
	public static volatile SingularAttribute<MacAssignmentObject, String> assignedMonthYear;
	public static volatile SingularAttribute<MacAssignmentObject, Date> createdDate;
	
}