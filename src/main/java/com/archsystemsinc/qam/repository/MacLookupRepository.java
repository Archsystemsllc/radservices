package com.archsystemsinc.qam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.archsystemsinc.qam.model.MacLookup;

/**
 */
public interface MacLookupRepository extends JpaRepository<MacLookup, Long>{
	
	@Query("SELECT max(m.id) FROM MacLookup m")
    public Long findMaxId();
	
	
}
