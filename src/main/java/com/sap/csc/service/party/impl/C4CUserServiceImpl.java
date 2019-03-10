package com.sap.csc.service.party.impl;

import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.csc.domain.exception.CommonBusinessException;
import com.sap.csc.domain.model.c4c.party.C4CLoginUser;
import com.sap.csc.domain.model.dto.request.c4c.C4CEmployeeRequest;
import com.sap.csc.domain.model.dto.request.c4c.C4CUserRequest;
import com.sap.csc.domain.model.dto.response.c4c.C4CCollectionResponse;
import com.sap.csc.domain.model.jpa.c4c.C4CSystem;
import com.sap.csc.domain.model.jpa.c4c.C4CUser;
import com.sap.csc.domain.model.jpa.party.Employee;
import com.sap.csc.domain.persistence.repository.c4c.C4CUserRepository;
import com.sap.csc.domain.persistence.repository.system.SystemRepository;
import com.sap.csc.service.GeneralServiceImpl;
import com.sap.csc.service.party.C4CUserService;
import com.sap.csc.service.party.EmployeeService;
import com.sap.csc.util.C4CRestTemplate;
import com.sap.csc.util.C4CUUIDUtils;
import com.sap.csc.util.builder.C4CODataUriBuilder;

/**
 * @author I326950
 */
@Service
public class C4CUserServiceImpl extends GeneralServiceImpl implements C4CUserService {

	private final C4CRestTemplate c4cRestTemplate;

	private final C4CODataUriBuilder c4cOdataUriBuilder;

	private final C4CUserRepository c4CUserRepository;

	private final SystemRepository systemRepository;

	private final EmployeeService employeeService;

	@Autowired
	public C4CUserServiceImpl(C4CRestTemplate c4cRestTemplate, C4CODataUriBuilder c4cOdataUriBuilder,
			C4CUserRepository c4CUserRepository, EmployeeService employeeService, SystemRepository systemRepository) {
		this.c4cRestTemplate = c4cRestTemplate;
		this.c4cOdataUriBuilder = c4cOdataUriBuilder;
		this.c4CUserRepository = c4CUserRepository;
		this.employeeService = employeeService;
		this.systemRepository = systemRepository;
	}

	@Override
	@Transactional
	public Optional<C4CUser> bindC4CUser(String originID, String openID, C4CUserRequest c4cUserRequest) {
		String systemURL = c4cUserRequest.getSystem().getUrl();
		URI uri = c4cOdataUriBuilder.buildForStandard(systemURL, "GetLoggedInUserInfo", true);
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<C4CCollectionResponse<C4CLoginUser>> loginUsers = null;

		headers.add("token", c4cUserRequest.getPassword());
		loginUsers = c4cRestTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<String>(headers),
				new ParameterizedTypeReference<C4CCollectionResponse<C4CLoginUser>>() {
				});

		if (loginUsers.getStatusCode() == HttpStatus.UNAUTHORIZED) {
			throw new CommonBusinessException("UNAUTHORIZED", "Your username or password invalid");
		}

		if (loginUsers.hasBody() && !loginUsers.getBody().getD().getResults().isEmpty()) {
			C4CLoginUser loginUser = null;
			Iterator<C4CLoginUser> it = loginUsers.getBody().getD().getResults().iterator();

			while (it.hasNext()) {
				loginUser = it.next();
				break;
			}

			if (loginUser != null) {
				C4CUser c4cUser = new C4CUser();
				C4CSystem c4cSystem = systemRepository.findByURL(c4cUserRequest.getSystem().getUrl());

				if (c4cSystem != null) {
					Optional<Employee> employee = employeeService.saveEmployee(
							C4CUUIDUtils.convertToStandardForm(loginUser.getEmployeeUUID()),
							loginUser.getEmployeeInternalID());

					if (employee.isPresent()) {
						C4CEmployeeRequest c4cEmployeeRequest = new C4CEmployeeRequest();
						
						c4cEmployeeRequest.setObjectID(C4CUUIDUtils.convertToStandardForm(loginUser.getEmployeeUUID()));
						c4cEmployeeRequest.setOpenID(openID);
						c4cEmployeeRequest.setIsWeChatUser(true);
						c4cEmployeeRequest.setOriginID(originID);
						
						employeeService.updateC4CEmployee(systemURL, headers, c4cEmployeeRequest);
						
						c4cUser.setEmployeeUUID(C4CUUIDUtils.convertToStandardForm(loginUser.getEmployeeUUID()));
						c4cUser.setEmployeeInternalID(loginUser.getEmployeeInternalID());
						c4cUser.setOpenID(openID);
						c4cUser.setUsername(loginUser.getUsername());
						c4cUser.setPassword(c4cUserRequest.getPassword());
						c4cUser.setIsPrimary(c4cUserRequest.getIsPrimary());
						c4cUser.setEmployee(employee.get());
						c4cUser.setSystem(c4cSystem);

						return Optional.ofNullable(c4CUserRepository.saveAndFlush(c4cUser));
					}
				}
			}

		}

		return null;
	}

	@Override
	public Optional<C4CUser> findOne(Long id) {
		return Optional.ofNullable(c4CUserRepository.findOne(id));
	}

	@Override
	public Optional<C4CUser> updateC4CUser(Long id, C4CUserRequest c4cUserRequest) {
		C4CUser c4cUser = c4CUserRepository.findOne(id);

		c4cUser.setUsername(c4cUserRequest.getUsername());
		c4cUser.setPassword(c4cUserRequest.getPassword());
		c4cUser.setIsPrimary(c4cUserRequest.getIsPrimary());

		return Optional.ofNullable(c4CUserRepository.saveAndFlush(c4cUser));
	}

	@Override
	public Optional<C4CUser> changePriamryStatus(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteC4CUser(Long id) {
		c4CUserRepository.delete(id);
	}

	@Override
	public List<C4CUser> findAllByOpenID(String openID) {
		return c4CUserRepository.findAllByOpenID(openID);
	}

	@Override
	public Optional<C4CUser> findPrimaryOneByOpenID(String openID) {
		return Optional.ofNullable(c4CUserRepository.findPrimaryOneByOpenID(openID));
	}

	// private C4CUser changeOthersPrimaryStauts(String openID, C4CUser c4cUser)
	// {
	// List<C4CUser> c4cUsers = c4CUserRepository.findByOpenID(openID);
	//
	// for(C4CUser user: c4cUsers) {
	// user.setIsPrimary(false);
	// }
	// c4cUser.setIsPrimary(true);
	//
	// return c4cUser;
	// }

}
