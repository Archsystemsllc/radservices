package com.archsystemsinc.qam.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.archsystemsinc.qam.model.RadUser;

/**
 */
public interface RadUserRepository extends JpaRepository<RadUser, Long>{
	
	RadUser findByUserName(String userName);
	@Query("SELECT r FROM RadUser r where r.firstName = :firstName and r.lastName = :lastName and r.middleName = :middleName") 
    String findByName(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("middleName") String middleName);
	
	@Modifying
	@Query("update RadUser c set c.status = :status, c.updateDate = :updatedDate, c.updatedBy = :updatedBy where c.id = :userId")
	int updateStatus(@Param("status") Long status, @Param("userId") Long userId,@Param("updatedDate") Date updatedDate, @Param("updatedBy") String updatedBy);
	
	@Modifying
	@Query("update RadUser c set c.emailId = :emailId, c.updateDate = :updatedDate, c.updatedBy = :updatedBy, c.lastName =:lastName, c.middleName = :middleName, c.firstName = :firstName where c.id = :userId")
	int updateUser(@Param("emailId") String emailId,@Param("firstName") String firstName,@Param("middleName") String middleName,@Param("lastName") String lastName, @Param("userId") Long userId,@Param("updatedDate") Date updatedDate, @Param("updatedBy") String updatedBy);
	
	@Modifying
	@Query("update RadUser c set c.password = :newPassword where c.id = :userId")
	Integer updateUserPassword(@Param("userId") Long userId, @Param("newPassword") String newPassword);
	
	
	@Query("SELECT r FROM RadUser r where r.lastName like :lastName and r.role.id = :roleId and r.organizationLookup.id = :orgId") 
	List<RadUser> filterUser(@Param("lastName") String lastName, @Param("roleId") Long roleId,@Param("orgId")  Integer orgId);
	
	@Query("SELECT r FROM RadUser r where r.role.id = :roleId and r.organizationLookup.id = :orgId") 
	List<RadUser> filterUser( @Param("roleId") Long roleId,@Param("orgId")  Integer orgId);
	
	
	@Query("SELECT r FROM RadUser r where r.status != :status") 
	List<RadUser> findUsers( @Param("status") Long status);
}
