package com.sap.csc.service.party.impl;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.csc.domain.model.c4c.party.C4CEmployee;
import com.sap.csc.domain.model.dto.request.c4c.C4CAssignedCustomerRequest;
import com.sap.csc.domain.model.dto.request.c4c.C4CAssignedOrderRequest;
import com.sap.csc.domain.model.dto.request.c4c.C4CEmployeeRequest;
import com.sap.csc.domain.model.dto.response.c4c.C4CEntityResponse;
import com.sap.csc.domain.model.jpa.c4c.C4CUser;
import com.sap.csc.domain.model.jpa.party.Employee;
import com.sap.csc.domain.persistence.repository.c4c.C4CUserRepository;
import com.sap.csc.domain.persistence.repository.party.EmployeeRepository;
import com.sap.csc.service.GeneralServiceImpl;
import com.sap.csc.service.party.EmployeeService;
import com.sap.csc.service.wechat.WxPublicService;
import com.sap.csc.util.C4CRestTemplate;
import com.sap.csc.util.builder.C4CODataUriBuilder;
import com.sap.csc.util.constant.URLConstants;
import com.sap.csc.web.exception.CommonWebException;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

@Service
public class EmployeeServiceImpl extends GeneralServiceImpl implements EmployeeService {

	private final static String TEMPLATEID_CUSTOMER_ASSIGNED = "NcutSAeSzZcbcx9LixbrBkf4Qy2th1m6iq9mK24GPOk";

	private final static String TEMPLATEID_ORDER_ASSIGNED = "dX9twgK38uU2JrcGduRj29hpUn7ld_l7sBokemRPFDo";

	private final EmployeeRepository employeeRepository;

	private final C4CUserRepository c4CUserRepository;

	private final WxPublicService wxPublicService;

	private final C4CRestTemplate c4cRestTemplate;

	private final C4CODataUriBuilder c4cODataUriBuilder;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository, C4CUserRepository c4CUserRepository,
			WxPublicService wxPublicService, C4CRestTemplate c4cRestTemplate, C4CODataUriBuilder c4cODataUriBuilder) {
		this.employeeRepository = employeeRepository;
		this.c4CUserRepository = c4CUserRepository;
		this.wxPublicService = wxPublicService;
		this.c4cRestTemplate = c4cRestTemplate;
		this.c4cODataUriBuilder = c4cODataUriBuilder;
	}

	@Override
	@Transactional
	public Optional<Employee> saveEmployee(String uuid, String internalID) {
		Employee employee = new Employee();

		employee.setUuid(uuid);
		employee.setInternalID(internalID);

		return Optional.ofNullable(employeeRepository.saveAndFlush(employee));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Employee> findAllByOpenId(String openId) {
		return employeeRepository.findAllByOpenId(openId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Employee> findDetailedAllByOpenId(String openId) {
		return employeeRepository.findDetailedAllByOpenId(openId);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Employee> findOne(Long id) {
		return Optional.ofNullable(employeeRepository.findOne(id));
	}

	@Override
	@Async
	public Future<C4CEmployee> updateC4CEmployee(String systemURL, HttpHeaders headers,
			C4CEmployeeRequest c4cEmployeeRequest) {
		String employeeUUID = c4cEmployeeRequest.getObjectID();
		URI uri = c4cODataUriBuilder.buildForCustom(systemURL, "employee/EmployeeCollection('" + employeeUUID + "')",
				false);

		headers.add("Accept", "application/json");
		ResponseEntity<C4CEntityResponse<C4CEmployee>> c4cEmployeeResponse = c4cRestTemplate.exchange(uri,
				HttpMethod.PUT, new HttpEntity<C4CEmployeeRequest>(c4cEmployeeRequest, headers),
				new ParameterizedTypeReference<C4CEntityResponse<C4CEmployee>>() {
				});

		return new AsyncResult<>(c4cEmployeeResponse.getBody().getD().getResult());
	}

	@Override
	public String notifyAssignedCustomer(C4CAssignedCustomerRequest customerRequest) {
		C4CUser c4cUser = c4CUserRepository.findByEmployeeUUID(customerRequest.getOwnerUUID());

		if (c4cUser != null) {
			WxMpTemplateMessage templateMsg = WxMpTemplateMessage.builder().toUser(c4cUser.getOpenID())
					.templateId(TEMPLATEID_CUSTOMER_ASSIGNED).build();
			WxMpService wxMpService = wxPublicService.getWxMpService(customerRequest.getOriginID());

			templateMsg
					.addWxMpTemplateData(new WxMpTemplateData("first", "新分配" + customerRequest.getCategory() + "客户"));
			templateMsg.addWxMpTemplateData(new WxMpTemplateData("keyword1", customerRequest.getName()));
			templateMsg.addWxMpTemplateData(new WxMpTemplateData("keyword2", customerRequest.getPhone()));
			templateMsg.addWxMpTemplateData(new WxMpTemplateData("remark", "行业：" + customerRequest.getIndustry()));
			templateMsg.setUrl(URLConstants.SERVER_ROOT_URL + "/#/customers/" + customerRequest.getInternalID());

			try {
				return wxMpService.getTemplateMsgService().sendTemplateMsg(templateMsg);
			} catch (WxErrorException e) {
				throw new CommonWebException(String.valueOf(e.getError().getErrorCode()), e.getError().getErrorMsg());
			}
		}

		return null;
	}

	@Override
	public String notifyAssignedOrder(C4CAssignedOrderRequest orderRequest) {
		C4CUser c4cUser = c4CUserRepository.findByEmployeeUUID(orderRequest.getOwnerUUID());

		if (c4cUser != null) {
			WxMpTemplateMessage templateMsg = WxMpTemplateMessage.builder().toUser(c4cUser.getOpenID())
					.templateId(TEMPLATEID_ORDER_ASSIGNED).build();
			WxMpService wxMpService = wxPublicService.getWxMpService(orderRequest.getOriginID());

			templateMsg.addWxMpTemplateData(
					new WxMpTemplateData("first", "来自 " + orderRequest.getCustomerName() + " 的销售订单"));
			templateMsg.addWxMpTemplateData(new WxMpTemplateData("keyword1", orderRequest.getInternalID()));
			templateMsg.addWxMpTemplateData(new WxMpTemplateData("keyword2", orderRequest.getRequestedDate()));

			if (orderRequest.getNetAmount() != null) {
				templateMsg.addWxMpTemplateData(new WxMpTemplateData("keyword3",
						orderRequest.getNetAmount().getContent() + orderRequest.getNetAmount().getCurrencyCode()));
			}

			templateMsg.addWxMpTemplateData(new WxMpTemplateData("keyword4", orderRequest.getDistributionChannel()));
			templateMsg.addWxMpTemplateData(new WxMpTemplateData("remark", "备注：" + orderRequest.getDescription()));
			templateMsg.setUrl(URLConstants.SERVER_ROOT_URL + "/orders/" + orderRequest.getInternalID());

			try {
				return wxMpService.getTemplateMsgService().sendTemplateMsg(templateMsg);
			} catch (WxErrorException e) {
				throw new CommonWebException(String.valueOf(e.getError().getErrorCode()), e.getError().getErrorMsg());
			}
		}

		return null;
	}
}
