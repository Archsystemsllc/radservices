package com.archsystemsinc.qam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.archsystemsinc.qam.model.RadUser;

/**
 */
public interface RadUserRepository extends JpaRepository<RadUser, Long>{
	
	@Query("SELECT r FROM RadUser r where r.firstName = :firstName and r.lastName = :lastName and r.middleName = :middleName") 
    String findByName(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("middleName") String middleName);
}
