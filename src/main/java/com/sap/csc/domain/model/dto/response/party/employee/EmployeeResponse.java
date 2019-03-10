package com.sap.csc.domain.model.dto.response.party.employee;

import java.io.Serializable;

import com.sap.csc.domain.model.jpa.party.Employee;

/**
 * @author I326950
 */
public class EmployeeResponse implements Serializable {

	private static final long serialVersionUID = -1868050972012739285L;

	private Long id;

	private String name;

	private String mobile;

	private String email;

	public EmployeeResponse(Employee employee) {
		this.id = employee.getId();
		this.name = employee.getName();
		this.mobile = employee.getMobile();
		this.email = employee.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
