package com.archsystemsinc.qam.repository.specifications;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.archsystemsinc.qam.model.OrganizationLookup;
import com.archsystemsinc.qam.model.RadUser;
import com.archsystemsinc.qam.model.RadUser_;
import com.archsystemsinc.qam.model.Role;

public final class RadUserSpecifications {

	private RadUserSpecifications() {
		throw new AssertionError();
	}
	
	// API
	
	public static Specification<RadUser> searchById(final Long id) {
		return new Specification<RadUser>() {
			@Override
			public final Predicate toPredicate(final Root<RadUser> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				
				if(id != null && id != 0) { 
					final Predicate matchingById = builder.equal(root.get(RadUser_.id), id);
					return matchingById;
				} else 
					return null;			
			}
		};
	}
	public static Specification<RadUser> searchByUserName(final String userName) {
		return new Specification<RadUser>() {
			@Override
			public final Predicate toPredicate(final Root<RadUser> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(userName != null && !userName.equalsIgnoreCase("")) {
					final Predicate matchingByUserNameString = builder.like(root.get(RadUser_.userName), userName );
					return matchingByUserNameString;
				} else 
					return null;
			}
		};
	}
	
	public static Specification<RadUser> searchByMacId(final Long macId) {
		return new Specification<RadUser>() {
			@Override
			public final Predicate toPredicate(final Root<RadUser> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				
				if(macId != null && macId != 0) { 
					final Predicate matchingByMacId = builder.equal(root.get(RadUser_.macId), macId);
					return matchingByMacId;
				} else 
					return null;			
			}
		};
	}
	
	public static Specification<RadUser> searchByJurIdList(final ArrayList<String> jurIdList) {
		return new Specification<RadUser>() {
			@Override
			public final Predicate toPredicate(final Root<RadUser> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				
				if(jurIdList != null ) {
					
					Expression<String> exp = root.get(RadUser_.jurId);
					final Predicate matchingByJurIdList = exp.in(jurIdList);
					return matchingByJurIdList;
					
				} else 
					return null;
			}
		};
	}
	
	
	public static Specification<RadUser> searchByRole(final Role role) {
		return new Specification<RadUser>() {
			@Override
			public final Predicate toPredicate(final Root<RadUser> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(role != null ) {
					final Predicate matchingByRole = builder.equal(root.get(RadUser_.role), role);
					return matchingByRole;
				} else 
					return null;
			}
		};
	}	
	
	public static Specification<RadUser> searchByLastName(final String lastName) {
		return new Specification<RadUser>() {
			@Override
			public final Predicate toPredicate(final Root<RadUser> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(lastName != null && !lastName.equalsIgnoreCase("")) {
					final Predicate matchingByLastName = builder.like(root.get(RadUser_.lastName), lastName + "%");
					return matchingByLastName;
				} else 
					return null;
			}
		};
	}
	
	
	
	public static Specification<RadUser> searchByOrganizationLookup(final OrganizationLookup organizationLookup) {
		return new Specification<RadUser>() {
			@Override
			public final Predicate toPredicate(final Root<RadUser> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				if(organizationLookup != null ) {
					final Predicate matchingByOrganizationLookup = builder.equal(root.get(RadUser_.organizationLookup), organizationLookup);
					return matchingByOrganizationLookup;
				} else 
					return null;
			}
		};
	}

}
