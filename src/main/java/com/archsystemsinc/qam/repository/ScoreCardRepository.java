package com.archsystemsinc.qam.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.archsystemsinc.qam.model.ScoreCard;

/**
 */
public interface ScoreCardRepository extends JpaRepository<ScoreCard, Long>{
	
	@Query("SELECT s FROM ScoreCard s WHERE s.callResult= :callResult ")
    public List<ScoreCard> findAllByFailedReason(@Param("callResult") String failureResult);
	
	@Query("SELECT s FROM ScoreCard s WHERE s.macId = :macId and s.jurId = :jurisId and STR_TO_DATE(qam_enddate_time,'%m/%d/%Y %h:%i:%s %p') between :fromDate and :toDate ")
    public List<ScoreCard> macJurisReport(@Param("macId") Integer macId,@Param("jurisId") Integer jurisId,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
	
	@Query("SELECT s FROM ScoreCard s WHERE STR_TO_DATE(qam_enddate_time,'%m/%d/%Y %h:%i:%s %p') between :fromDate and :toDate ")
    public List<ScoreCard> macJuriReport_AllMacAllJuris(@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
	
	@Query("SELECT s FROM ScoreCard s WHERE s.macId = :macId and STR_TO_DATE(qam_enddate_time,'%m/%d/%Y %h:%i:%s %p') between :fromDate and :toDate ")
    public List<ScoreCard> macJuriReport_AllJuris(@Param("macId") Integer macId,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
	
	@Query("SELECT s FROM ScoreCard s WHERE s.jurId = :jurisId and STR_TO_DATE(qam_enddate_time,'%m/%d/%Y %h:%i:%s %p') between :fromDate and :toDate ")
    public List<ScoreCard> macJuriReport_AllMac(@Param("jurisId") Integer jurisId,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
	
}