package com.sap.csc.web.controller;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.csc.domain.model.c4c.party.C4CEmployee;
import com.sap.csc.domain.model.dto.request.c4c.C4CEmployeeRequest;
import com.sap.csc.service.party.EmployeeService;

@RestController
@RequestMapping("/miscellaneous")
public class MiscellaneousController extends GeneralController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/state")
	public String getSystemState() {
		return "Working";
	}

	@PostMapping("/employee")
	public C4CEmployee updateC4CEmployee() throws InterruptedException, ExecutionException {
		Optional<String> openID = super.getCurrentUserOpenID();

		if (openID.isPresent()) {
			C4CEmployeeRequest c4cEmployeeRequest = new C4CEmployeeRequest();
			String systemURL = "https://my500047.c4c.saphybriscloud.cn";
			HttpHeaders headers = new HttpHeaders();

			headers.add("token", "S0FZRFU6V2VsY29tZTE=");
			c4cEmployeeRequest.setObjectID("00163E217B101ED788E7E20977A2819E");
			c4cEmployeeRequest.setOpenID(openID.get());
			c4cEmployeeRequest.setIsWeChatUser(true);

			Future<C4CEmployee> asyncC4CEmployee = employeeService.updateC4CEmployee(systemURL, headers,
					c4cEmployeeRequest);

			while (asyncC4CEmployee.isDone()) {
				return asyncC4CEmployee.get();
			}
		}

		return null;
	}

}
