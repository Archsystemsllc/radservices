/**
 * 
 */
package com.archsystemsinc.qam.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.archsystemsinc.qam.model.MacAssignmentObject;
import com.archsystemsinc.qam.repository.MacAssignmentObjectRepository;
import com.archsystemsinc.qam.repository.specifications.MacAssignmentObjectSpecifications;

/**
 * @author Abdul Nissar S
 *
 */
@Service
@Transactional
public class MacAssignmentObjectService {
	private static final Logger log = Logger.getLogger(MacAssignmentObject.class);
	@Autowired
	private MacAssignmentObjectRepository macAssignmentObjectRepository;	
	
	public List<MacAssignmentObject> findAll(){
		return macAssignmentObjectRepository.findAll();
	}	
	
	public MacAssignmentObject saveOrUpdateMacAssignmentObject(MacAssignmentObject macAssignmentObject) {
		MacAssignmentObject macAssignmentObjectReturn= macAssignmentObjectRepository.save(macAssignmentObject);
		return macAssignmentObjectReturn;
	}
	
	public List< MacAssignmentObject > searchWithCurrentMonthYear( MacAssignmentObject macAssignmentObject ){	
		
		Specifications< MacAssignmentObject > specifications = Specifications.where					
							(MacAssignmentObjectSpecifications.findByCurrentMonthYear(macAssignmentObject.getAssignedMonthYear()));	
							
		if(macAssignmentObject.getAssignedMonthYear() == null || macAssignmentObject.getAssignedMonthYear().equalsIgnoreCase("")) {
			return null;
		} else {
			return macAssignmentObjectRepository.findAll(specifications);
		}
		
	}
	
	public List<Object[]> searchMacAssignmentObjectForList( MacAssignmentObject macAssignmentObject ){	
		
		String[] toMonthyear = macAssignmentObject.getAssignedMonthYear().split("_");
		
		String toMonth = toMonthyear[0];
		String toYear = toMonthyear[1];
		log.debug("toMonth::"+toMonth);
		log.debug("fromYear::"+toYear);
		List<Object[]> resultsList = null;
		resultsList = macAssignmentObjectRepository.findMonthsByMonthYearRange(new Integer(toYear+toMonth));
												
		return resultsList;
	}
}

