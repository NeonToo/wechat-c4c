package com.sap.csc.domain.model.dto.response.party.employee;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.sap.csc.domain.model.dto.response.wechat.WechatUserResponse;
import com.sap.csc.domain.model.jpa.party.Employee;
import com.sap.csc.domain.model.jpa.wechat.WechatUser;

/**
 * @author I326950
 */
public class EmployeeWechatUserDetailResponse implements Serializable {

	private static final long serialVersionUID = 7035865411816226658L;

	private WechatUserResponse wechatUser;

	private Collection<EmployeeResponse> employees;

	public EmployeeWechatUserDetailResponse(WechatUser wechatUser, List<Employee> employees) {
		this.wechatUser = new WechatUserResponse(wechatUser);
		this.employees = employees.stream().map(employee -> new EmployeeResponse(employee))
				.collect(Collectors.toList());
	}

	public WechatUserResponse getWechatUser() {
		return wechatUser;
	}

	public void setWechatUser(WechatUserResponse wechatUser) {
		this.wechatUser = wechatUser;
	}

	public Collection<EmployeeResponse> getEmployees() {
		return employees;
	}

	public void setEmployees(Collection<EmployeeResponse> employees) {
		this.employees = employees;
	}
}
