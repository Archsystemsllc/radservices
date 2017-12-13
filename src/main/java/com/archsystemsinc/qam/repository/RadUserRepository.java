package com.archsystemsinc.qam.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.archsystemsinc.qam.model.RadUser;

/**
 */
public interface RadUserRepository extends JpaRepository<RadUser, Long>{
	
	@Query("SELECT r FROM RadUser r where r.firstName = :firstName and r.lastName = :lastName and r.middleName = :middleName") 
    String findByName(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("middleName") String middleName);
	
	@Modifying
	@Query("update RadUser c set c.status = :status, c.updateDate = :updatedDate, c.updatedBy = :updatedBy where c.id = :userId")
	int updateStatus(@Param("status") Long status, @Param("userId") Long userId,@Param("updatedDate") Date updatedDate, @Param("updatedBy") String updatedBy);
	
	@Modifying
	@Query("update RadUser c set c.emailId = :emailId, c.updateDate = :updatedDate, c.updatedBy = :updatedBy, c.lastName =:lastName, c.middleName = :middleName, c.firstName = :firstName where c.id = :userId")
	int updateUser(@Param("emailId") String emailId,@Param("firstName") String firstName,@Param("middleName") String middleName,@Param("lastName") String lastName, @Param("userId") Long userId,@Param("updatedDate") Date updatedDate, @Param("updatedBy") String updatedBy);
}
