package com.sap.csc.service.activity.impl;

import java.net.URI;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.csc.domain.model.c4c.activity.C4CActivity;
import com.sap.csc.domain.model.dto.response.c4c.C4CCollectionEntry;
import com.sap.csc.domain.model.dto.response.c4c.C4CCollectionResponse;
import com.sap.csc.domain.model.jpa.c4c.C4CUser;
import com.sap.csc.domain.persistence.repository.c4c.C4CUserRepository;
import com.sap.csc.service.GeneralServiceImpl;
import com.sap.csc.service.activity.ActivityService;
import com.sap.csc.util.C4CRestTemplate;
import com.sap.csc.util.builder.C4CODataUriBuilder;
import com.sap.csc.util.builder.QueryOption;

@Service
public class ActivityServiceImpl extends GeneralServiceImpl implements ActivityService {

	private final C4CRestTemplate c4cRestTemplate;

	private final C4CUserRepository c4CUserRepository;

	private final C4CODataUriBuilder c4cODataUriBuilder;

	@Autowired
	public ActivityServiceImpl(C4CRestTemplate c4cRestTemplate, C4CODataUriBuilder c4cODataUriBuilder,
			C4CUserRepository c4CUserRepository) {
		this.c4cRestTemplate = c4cRestTemplate;
		this.c4cODataUriBuilder = c4cODataUriBuilder;
		this.c4CUserRepository = c4CUserRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Set<C4CActivity> findByOwner(String openID) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			String systemUrl = c4cUser.getSystem().getUrl();
			String ownerID = c4cUser.getEmployeeInternalID();
			URI uri = c4cODataUriBuilder.buildForCustom(systemUrl, "order/ActivityCollection", true,
					new QueryOption<String>("$filter", "OwnerID eq '" + ownerID + "'"));
			HttpHeaders headers = new HttpHeaders();

			headers.add("token", c4cUser.getPassword().toString());
			return this.findActivities(uri, headers);
		}

		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public C4CCollectionEntry<C4CActivity> findByOwner(String openID, PageRequest pageRequest) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			String systemUrl = c4cUser.getSystem().getUrl();
			String ownerID = c4cUser.getEmployeeInternalID();
			URI uri = c4cODataUriBuilder.buildForCustom(systemUrl, "order/ActivityCollection", false,
					new QueryOption<Integer>("$skip", pageRequest.getOffset()),
					new QueryOption<Integer>("$top", pageRequest.getPageSize()),
					new QueryOption<String>("$inlinecount", "allpages"), new QueryOption<String>("$expand", "Items"),
					new QueryOption<String>("$filter", "OwnerID eq '" + ownerID + "'"));
			HttpHeaders headers = new HttpHeaders();

			headers.add("token", c4cUser.getPassword().toString());
			ResponseEntity<C4CCollectionResponse<C4CActivity>> activitiesRes = c4cRestTemplate.exchange(uri,
					HttpMethod.GET, new HttpEntity<String>(headers),
					new ParameterizedTypeReference<C4CCollectionResponse<C4CActivity>>() {
					});
			C4CCollectionEntry<C4CActivity> entry = activitiesRes.getBody().getD();

			if (entry != null && entry.getCount() != 0) {
				return entry;
			}
		}

		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Set<C4CActivity> findByCustomerAndOwner(String openID, String customerID) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			String systemUrl = c4cUser.getSystem().getUrl();
			String ownerID = c4cUser.getEmployeeInternalID();
			URI uri = c4cODataUriBuilder.buildForCustom(systemUrl, "order/ActivityCollection", true,
					new QueryOption<String>("$filter", "OwnerID eq '" + ownerID + "'"),
					new QueryOption<String>("$filter", "CustomerID eq '" + customerID + "'"));
			HttpHeaders headers = new HttpHeaders();

			headers.add("token", c4cUser.getPassword().toString());
			return this.findActivities(uri, headers);
		}

		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public C4CCollectionEntry<C4CActivity> findByCustomerAndOwner(String openID, String customerID,
			PageRequest pageRequest) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			String systemUrl = c4cUser.getSystem().getUrl();
			String ownerID = c4cUser.getEmployeeInternalID();
			URI uri = c4cODataUriBuilder.buildForCustom(systemUrl, "order/ActivityCollection", false,
					new QueryOption<Integer>("$skip", pageRequest.getOffset()),
					new QueryOption<Integer>("$top", pageRequest.getPageSize()),
					new QueryOption<String>("$inlinecount", "allpages"), new QueryOption<String>("$expand", "Items"),
					new QueryOption<String>("$filter", "OwnerID eq '" + ownerID + "'"),
					new QueryOption<String>("$filter", "CustomerID eq '" + customerID + "'"));
			HttpHeaders headers = new HttpHeaders();

			headers.add("token", c4cUser.getPassword().toString());
			ResponseEntity<C4CCollectionResponse<C4CActivity>> activitiesRes = c4cRestTemplate.exchange(uri,
					HttpMethod.GET, new HttpEntity<String>(headers),
					new ParameterizedTypeReference<C4CCollectionResponse<C4CActivity>>() {
					});
			C4CCollectionEntry<C4CActivity> entry = activitiesRes.getBody().getD();

			if (entry != null && entry.getCount() != 0) {
				return entry;
			}
		}

		return null;
	}

	private Set<C4CActivity> findActivities(URI uri, HttpHeaders headers) {
		ResponseEntity<C4CCollectionResponse<C4CActivity>> activitiesRes = c4cRestTemplate.exchange(uri, HttpMethod.GET,
				new HttpEntity<String>(headers), new ParameterizedTypeReference<C4CCollectionResponse<C4CActivity>>() {
				});

		if (!activitiesRes.getBody().getD().getResults().isEmpty()) {
			Iterator<C4CActivity> it = activitiesRes.getBody().getD().getResults().iterator();
			Set<C4CActivity> activities = new LinkedHashSet<>();

			while (it.hasNext()) {
				activities.add(it.next());
			}

			return activities;
		}

		return null;
	}

}
