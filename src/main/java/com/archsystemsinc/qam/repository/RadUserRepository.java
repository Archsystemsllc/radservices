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
	
	@Query("SELECT r FROM RadUser r where r.macId = :macId and r.jurId = :jurisId") 
	List<RadUser> findByMacIdAndJurisId(@Param("macId") Long macId, @Param("jurisId") Long jurisId);
	
	@Modifying
	@Query("update RadUser c set c.status = :status, c.updateDate = :updatedDate, c.updatedBy = :updatedBy where c.id = :userId")
	int updateStatus(@Param("status") Long status, @Param("userId") Long userId,@Param("updatedDate") Date updatedDate, @Param("updatedBy") String updatedBy);
	
	@Modifying
	@Query("update RadUser c set c.emailId = :emailId, c.updateDate = :updatedDate, c.updatedBy = :updatedBy, c.lastName =:lastName, c.middleName = :middleName, c.firstName = :firstName where c.id = :userId")
	int updateUser(@Param("emailId") String emailId,@Param("firstName") String firstName,@Param("middleName") String middleName,@Param("lastName") String lastName, @Param("userId") Long userId,@Param("updatedDate") Date updatedDate, @Param("updatedBy") String updatedBy);
	
	
	@Modifying
	@Query("update RadUser c set c.password = :password, c.role.id = :roleId , c.organizationLookup.id = :orgId, c.macId = :macId, c.pccId = :pccId, c.emailId = :emailId, c.updateDate = :updatedDate, c.updatedBy = :updatedBy, c.lastName =:lastName, c.middleName = :middleName, c.firstName = :firstName where c.id = :userId")
	int updateUserData(@Param("password") String password, @Param("roleId") Long roleId,@Param("orgId")  Integer orgId, @Param("macId") Long macId,@Param("pccId") Long pccId,@Param("emailId") String emailId,@Param("firstName") String firstName,@Param("middleName") String middleName,@Param("lastName") String lastName, @Param("userId") Long userId,@Param("updatedDate") Date updatedDate, @Param("updatedBy") String updatedBy);
	
	
	@Modifying
	@Query("update RadUser c set c.password = :newPassword where c.id = :userId")
	Integer updateUserPassword(@Param("userId") Long userId, @Param("newPassword") String newPassword);
	
	
	@Modifying
	@Query("update RadUser c set c.lastLoggedinDate= :lastLoggedinDate where c.id = :userId")
	Integer updateUserLastLoggedinDate(@Param("userId") Long userId, @Param("lastLoggedinDate") Date lastLoggedinDate);
	
	
	@Query("SELECT r FROM RadUser r where r.lastName like :lastName and r.role.id = :roleId and r.organizationLookup.id = :orgId") 
	List<RadUser> filterUser(@Param("lastName") String lastName, @Param("roleId") Long roleId,@Param("orgId")  Integer orgId);
	
	@Query("SELECT r FROM RadUser r where r.role.id = :roleId and r.organizationLookup.id = :orgId") 
	List<RadUser> filterUser( @Param("roleId") Long roleId,@Param("orgId")  Integer orgId);
	
	
	@Query("SELECT r FROM RadUser r where r.status != :status") 
	List<RadUser> findUsers( @Param("status") Long status);
	
	/*@Modifying
	@Query("update RadUser c set c.status = :status, c.updateDate = :updatedDate, c.updatedBy = :updatedBy where c.lastLoggedinDate is not null and DATE_ADD(c.lastLoggedinDate, INTERVAL 60 DAY) <= CURDATE()")
	int updateStatus(@Param("status") Long status, @Param("updatedDate") Date updatedDate, @Param("updatedBy") String updatedBy);
	*/
	@Modifying
	@Query(value="update user c set c.record_status = :status,  c.updated_date = :updatedDate, c.updated_by = :updatedBy where c.last_loggedin_date is not null and DATE_ADD(last_loggedin_date, INTERVAL 60 DAY) <= CURDATE()",nativeQuery = true)
	int updateStatus(@Param("status") Long status, @Param("updatedDate") Date updatedDate, @Param("updatedBy") String updatedBy);
	
}
