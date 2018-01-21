package com.archsystemsinc.qam.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the program_lookup database table.
 * 
 */
@Entity
@Table(name="pcc_location")
@NamedQuery(name="PccLocation.findAll", query="SELECT p FROM PccLocation p")
public class PccLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="city")
	private String pccLocationName;	

	public PccLocation() {
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPccLocationName() {
		return pccLocationName;
	}
	public void setPccLocationName(String pccLocationName) {
		this.pccLocationName = pccLocationName;
	}
}