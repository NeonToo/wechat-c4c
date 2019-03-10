package com.sap.csc.domain.model.jpa.party;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sap.csc.domain.model.jpa.ModifiableEntity;

/**
 * @author I326950
 */
@Entity
@Table(name = "EMPLOYEE")
public class Employee extends ModifiableEntity implements Serializable {

	private static final long serialVersionUID = 4244898319137834138L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "uuid")
	private String uuid;

	@Column(name = "internalID")
	private String internalID;

	@Column(name = "name")
	private String name;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "email")
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getInternalID() {
		return internalID;
	}

	public void setInternalID(String internalID) {
		this.internalID = internalID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
