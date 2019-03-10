package com.sap.csc.web.controller.party;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.csc.domain.model.dto.request.c4c.C4CAssignedCustomerRequest;
import com.sap.csc.domain.model.dto.request.c4c.C4CAssignedOrderRequest;
import com.sap.csc.domain.model.dto.response.party.employee.EmployeeResponse;
import com.sap.csc.domain.model.jpa.party.Employee;
import com.sap.csc.service.party.EmployeeService;
import com.sap.csc.web.controller.GeneralController;

@RestController
@RequestMapping("/employees")
public class EmployeeController extends GeneralController {

	private final EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/{id}")
	public EmployeeResponse getById(@PathVariable Long id) {
		Optional<Employee> employee = employeeService.findOne(id);

		return employee.isPresent() ? new EmployeeResponse(employee.get()) : null;
	}
	
	@PostMapping("/notifications/customer")
	public String notifyAssignedCustomer(@RequestBody C4CAssignedCustomerRequest customerRequest) {
		HttpServletRequest httpRequest = super.getHttpServletRequest();
		String origin = httpRequest.getHeader("Origin");
		
		if(StringUtils.isNotBlank(origin)) {
			return employeeService.notifyAssignedCustomer(customerRequest);
		}
//		String systemURL = String.format("%s://%s",
//				// Schema Name - e.g.: "http" or "https"
//				httpRequest.getScheme(),
//				// Server Name - e.g.: "localhost" or "wechattemplate.com"
//				httpRequest.getServerName());
//		
//		logger.info(" ----- Origin ----- " + origin);
//		logger.info(" ----- System URL ----- " + systemURL);
//		
//		if(StringUtils.equals(origin, systemURL)) {
//			return employeeService.notifyAssignedCustomer(customerRequest);
//		}
		
		return null;
	}
	
	@PostMapping("/notifications/order")
	public String notifyAssignedOrder(@RequestBody C4CAssignedOrderRequest orderRequest) {
		HttpServletRequest httpRequest = super.getHttpServletRequest();
		String origin = httpRequest.getHeader("Origin");
		
		if(StringUtils.isNotBlank(origin)) {
			return employeeService.notifyAssignedOrder(orderRequest);
		}
		
		return null;
	}

}
