package com.archsystemsinc.qam.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.archsystemsinc.qam.model.ScoreCard;

/**
 */
public interface ScoreCardRepository extends JpaRepository<ScoreCard, Long>, JpaSpecificationExecutor<ScoreCard> {
	
	@Query("SELECT s FROM ScoreCard s WHERE s.callResult= :callResult ")
    public List<ScoreCard> findAllByFailedReason(@Param("callResult") String failureResult);
	
	//AllScoreCardReport
	
	@Query("SELECT s FROM ScoreCard s WHERE STR_TO_DATE(s.qamEnddateTime,'%m/%d/%Y %h:%i:%s %p') between :fromDate and :toDate ")
    public List<ScoreCard> scoreCardReport_AllMacAllJurisAllProgram(@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
	
	@Query("SELECT s FROM ScoreCard s WHERE s.programId = :programId and STR_TO_DATE(s.qamEnddateTime,'%m/%d/%Y %h:%i:%s %p') between :fromDate and :toDate ")
    public List<ScoreCard> scoreCardReport_AllMacAllJuriProgramValue(@Param("programId") Integer programId, @Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
	
	@Query("SELECT s FROM ScoreCard s WHERE s.jurId = :jurId and STR_TO_DATE(s.qamEnddateTime,'%m/%d/%Y %h:%i:%s %p') between :fromDate and :toDate ")
    public List<ScoreCard> scoreCardReport_AllMacValueJuriAllProgram(@Param("jurId") Integer jurId, @Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
	
	@Query("SELECT s FROM ScoreCard s WHERE s.jurId = :jurId and s.programId = :programId and STR_TO_DATE(s.qamEnddateTime,'%m/%d/%Y %h:%i:%s %p') between :fromDate and :toDate ")
    public List<ScoreCard> scoreCardReport_AllMacValueJuriValueProgram(@Param("programId") Integer programId,@Param("jurId") Integer jurId, @Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
	
	@Query("SELECT s FROM ScoreCard s WHERE s.macId = :macId and STR_TO_DATE(s.qamEnddateTime,'%m/%d/%Y %h:%i:%s %p') between :fromDate and :toDate ")
    public List<ScoreCard> scoreCardReport_ValueMacAllJuriAllProgram(@Param("macId") Integer macId, @Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
	
	@Query("SELECT s FROM ScoreCard s WHERE s.jurId = :jurId and s.macId = :macId and STR_TO_DATE(s.qamEnddateTime,'%m/%d/%Y %h:%i:%s %p') between :fromDate and :toDate ")
    public List<ScoreCard> scoreCardReport_ValueMacValueJuriAllProgram(@Param("macId") Integer macId,@Param("jurId") Integer jurId, @Param("fromDate") Date fromDate,@Param("toDate") Date toDate);

	
	@Query("SELECT s FROM ScoreCard s WHERE s.macId = :macId and s.jurId = :jurId and  s.programId = :programId and STR_TO_DATE(s.qamEnddateTime,'%m/%d/%Y %h:%i:%s %p') between :fromDate and :toDate ")
    public List<ScoreCard> scoreCardReport(@Param("macId") Integer macId,@Param("jurId") Integer jurId,@Param("programId") Integer programId, @Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
	
}