package com.sap.csc.service.party;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

import org.springframework.http.HttpHeaders;

import com.sap.csc.domain.model.c4c.party.C4CEmployee;
import com.sap.csc.domain.model.dto.request.c4c.C4CAssignedCustomerRequest;
import com.sap.csc.domain.model.dto.request.c4c.C4CAssignedOrderRequest;
import com.sap.csc.domain.model.dto.request.c4c.C4CEmployeeRequest;
import com.sap.csc.domain.model.jpa.party.Employee;

public interface EmployeeService {

	public Optional<Employee> saveEmployee(String uuid, String internalID);

	public Optional<Employee> findOne(Long id);

	public List<Employee> findAllByOpenId(String openId);

	public List<Employee> findDetailedAllByOpenId(String openId);
	
	public Future<C4CEmployee> updateC4CEmployee(String systemURL, HttpHeaders headers, C4CEmployeeRequest c4cEmployeeRequest);

	public String notifyAssignedCustomer(C4CAssignedCustomerRequest customerRequest);

	public String notifyAssignedOrder(C4CAssignedOrderRequest orderRequest);
}
