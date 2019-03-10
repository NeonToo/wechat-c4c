package com.sap.csc.service.product.impl;

import java.net.URI;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sap.csc.domain.model.c4c.product.C4CProductCategory;
import com.sap.csc.domain.model.dto.response.c4c.C4CCollectionResponse;
import com.sap.csc.domain.model.jpa.c4c.C4CUser;
import com.sap.csc.domain.persistence.repository.c4c.C4CUserRepository;
import com.sap.csc.service.product.ProductCategoryService;
import com.sap.csc.util.C4CRestTemplate;
import com.sap.csc.util.builder.C4CODataUriBuilder;
import com.sap.csc.util.builder.QueryOption;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	private final C4CUserRepository c4CUserRepository;

	private final C4CRestTemplate c4cRestTemplate;

	private final C4CODataUriBuilder c4cODataUriBuilder;

	@Autowired
	public ProductCategoryServiceImpl(C4CUserRepository c4CUserRepository, C4CRestTemplate c4cRestTemplate,
			C4CODataUriBuilder c4cODataUriBuilder) {
		this.c4CUserRepository = c4CUserRepository;
		this.c4cRestTemplate = c4cRestTemplate;
		this.c4cODataUriBuilder = c4cODataUriBuilder;
	}

	@Override
	public Set<C4CProductCategory> findAll(String openID) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			String systemURL = c4cUser.getSystem().getUrl();
			URI uri = c4cODataUriBuilder.buildForStandard(systemURL, "ProductCategoryHierarchyCollection", true,
					new QueryOption<String>("$expand", "ProductCategoryHierarchyDescription"));
			HttpHeaders headers = new HttpHeaders();

			headers.add("token", "a2F5ZHU6V2VsY29tZTE=");
			return this.findAll(uri, headers);

		}

		return null;
	}

	@Override
	public Set<C4CProductCategory> findTop(String openID) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			String systemURL = c4cUser.getSystem().getUrl();
			URI uri = c4cODataUriBuilder.buildForStandard(systemURL, "ProductCategoryHierarchyCollection", true,
					new QueryOption<String>("$filter", "ParentInternalID eq 'CUSTOMER-05'"),
					new QueryOption<String>("$expand", "ProductCategoryHierarchyDescription"));
			HttpHeaders headers = new HttpHeaders();

			headers.add("token", "S0FZRFU6V2VsY29tZTE=");
			return this.findAll(uri, headers);

		}

		return null;
	}

	@Override
	public Set<C4CProductCategory> findChildrenByParentID(String openID, String id) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			String systemURL = c4cUser.getSystem().getUrl();
			URI uri = c4cODataUriBuilder.buildForStandard(systemURL, "ProductCategoryHierarchyCollection", true,
					new QueryOption<String>("$filter", "ParentInternalID eq '" + id + "'"),
					new QueryOption<String>("$expand", "ProductCategoryHierarchyDescription"));
			HttpHeaders headers = new HttpHeaders();

			headers.add("token", "a2F5ZHU6V2VsY29tZTE=");
			return this.findAll(uri, headers);

		}
		
		return null;
	}

	private Set<C4CProductCategory> findAll(URI uri, HttpHeaders headers) {
		ResponseEntity<C4CCollectionResponse<C4CProductCategory>> categoriesRes = c4cRestTemplate.exchange(uri,
				HttpMethod.GET, new HttpEntity<String>(headers),
				new ParameterizedTypeReference<C4CCollectionResponse<C4CProductCategory>>() {
				});

		if (!categoriesRes.getBody().getD().getResults().isEmpty()) {
			Iterator<C4CProductCategory> it = categoriesRes.getBody().getD().getResults().iterator();
			Set<C4CProductCategory> categories = new LinkedHashSet<>();

			while (it.hasNext()) {
				categories.add(it.next());
			}

			return categories;
		}
		return null;
	}

}
