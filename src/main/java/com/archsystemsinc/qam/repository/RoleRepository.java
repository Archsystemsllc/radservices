package com.archsystemsinc.qam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.archsystemsinc.qam.model.Role;

/**
 */
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	@Query("SELECT r FROM Role r where r.roleName = :roleName") 
    String findByRoleName(@Param("roleName") String roleName);
}
