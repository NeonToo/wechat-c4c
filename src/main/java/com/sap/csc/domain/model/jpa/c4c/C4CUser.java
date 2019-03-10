package com.sap.csc.domain.model.jpa.c4c;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sap.csc.domain.model.jpa.party.Employee;
import com.sap.csc.domain.model.jpa.wechat.WechatUser;

/**
 * @author I326950
 */
// @Embeddable
@Entity
@Table(name = "C4C_USER")
public class C4CUser implements Serializable {

	private static final long serialVersionUID = 3754501158728252155L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "employeeUUID")
	private String employeeUUID;

	@Column(name = "employeeInternalID")
	private String employeeInternalID;
	
	@Column(name = "openID")
	private String openID;

	@Column(name = "isPrimary")
	private Boolean isPrimary;

	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeId")
	private Employee employee;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wechatUserId")
	private WechatUser wechatUser;

	@ManyToOne
	@JoinColumn(name = "systemId")
	private C4CSystem system;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmployeeUUID() {
		return employeeUUID;
	}

	public void setEmployeeUUID(String employeeUUID) {
		this.employeeUUID = employeeUUID;
	}

	public String getEmployeeInternalID() {
		return employeeInternalID;
	}

	public void setEmployeeInternalID(String employeeInternalID) {
		this.employeeInternalID = employeeInternalID;
	}

	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}

	public Boolean getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public WechatUser getWechatUser() {
		return wechatUser;
	}

	public void setWechatUser(WechatUser wechatUser) {
		this.wechatUser = wechatUser;
	}

	public C4CSystem getSystem() {
		return system;
	}

	public void setSystem(C4CSystem system) {
		this.system = system;
	}

}
