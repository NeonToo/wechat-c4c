package com.sap.csc.service.product.impl;

import java.net.URI;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sap.csc.domain.model.c4c.product.C4CProduct;
import com.sap.csc.domain.model.dto.response.c4c.C4CCollectionEntry;
import com.sap.csc.domain.model.dto.response.c4c.C4CCollectionResponse;
import com.sap.csc.domain.model.jpa.c4c.C4CUser;
import com.sap.csc.domain.persistence.repository.c4c.C4CUserRepository;
import com.sap.csc.service.product.ProductService;
import com.sap.csc.util.C4CRestTemplate;
import com.sap.csc.util.builder.C4CODataUriBuilder;
import com.sap.csc.util.builder.QueryOption;

@Service
public class ProductServiceImpl implements ProductService {

	private final C4CUserRepository c4CUserRepository;

	private final C4CRestTemplate c4cRestTemplate;

	private final C4CODataUriBuilder c4cODataUriBuilder;

	@Autowired
	public ProductServiceImpl(C4CUserRepository c4CUserRepository, C4CRestTemplate c4cRestTemplate,
			C4CODataUriBuilder c4cODataUriBuilder) {
		this.c4CUserRepository = c4CUserRepository;
		this.c4cRestTemplate = c4cRestTemplate;
		this.c4cODataUriBuilder = c4cODataUriBuilder;
	}

	@Override
	public Set<C4CProduct> findAll(String openID) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			String systemURL = c4cUser.getSystem().getUrl();
			URI uri = c4cODataUriBuilder.buildForStandard(systemURL, "ProductCollection", true,
					new QueryOption<String>("$expand", "ProductCategoryAssignment"));
			HttpHeaders headers = new HttpHeaders();

			headers.add("id", c4cUser.getId().toString());
			return this.findAll(uri, headers);
		}

		return null;
	}

	@Override
	public C4CCollectionEntry<C4CProduct> findAll(String openID, PageRequest pageRequest) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			String systemURL = c4cUser.getSystem().getUrl();
			URI uri = c4cODataUriBuilder.buildForStandard(systemURL, "ProductCollection", true,
					new QueryOption<Integer>("$skip", pageRequest.getOffset()),
					new QueryOption<Integer>("$top", pageRequest.getPageSize()),
					new QueryOption<String>("$inlinecount", "allpages"),
					new QueryOption<String>("$expand", "ProductCategoryAssignment"));
			HttpHeaders headers = new HttpHeaders();

			headers.add("id", c4cUser.getId().toString());
			ResponseEntity<C4CCollectionResponse<C4CProduct>> productsRes = c4cRestTemplate.exchange(uri, HttpMethod.GET,
					new HttpEntity<String>(headers), new ParameterizedTypeReference<C4CCollectionResponse<C4CProduct>>() {
					});
			C4CCollectionEntry<C4CProduct> entry = productsRes.getBody().getD();

			if (entry != null && entry.getCount() != 0) {
				return entry;
			}
		}

		return null;
	}

	@Override
	public Optional<C4CProduct> findByID(String openID, String id) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			String systemUrl = c4cUser.getSystem().getUrl();
			URI uri = c4cODataUriBuilder.buildForStandard(systemUrl, "ProductCollection", true,
					new QueryOption<String>("$filter", "ID eq '" + id + "'"),
					new QueryOption<String>("$expand", "ProductCategoryAssignment"));
			HttpHeaders headers = new HttpHeaders();

			headers.add("id", c4cUser.getId().toString());
			ResponseEntity<C4CCollectionResponse<C4CProduct>> productsRes = c4cRestTemplate.exchange(uri,
					HttpMethod.GET, new HttpEntity<String>(headers),
					new ParameterizedTypeReference<C4CCollectionResponse<C4CProduct>>() {
					});

			if (!productsRes.getBody().getD().getResults().isEmpty()) {
				Iterator<C4CProduct> it = productsRes.getBody().getD().getResults().iterator();

				while (it.hasNext()) {
					return Optional.ofNullable(it.next());
				}
			}
		}

		return Optional.ofNullable(null);
	}

	@Override
	public Set<C4CProduct> finaByCategory(String openID, String category) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			String systemURL = c4cUser.getSystem().getUrl();
			URI uri = c4cODataUriBuilder.buildForStandard(systemURL, "ProductFindByCategory", true,
					new QueryOption<String>("CategoryID", "'" + category + "'"),
					new QueryOption<String>("$expand", "ProductCategoryAssignment"));
			HttpHeaders headers = new HttpHeaders();

			headers.add("token", "a2F5ZHU6V2VsY29tZTE=");
			return this.findAll(uri, headers);
		}

		return null;
	}

	@Override
	public C4CCollectionEntry<C4CProduct> finaByCategory(String openID, PageRequest pageRequest, String category) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			String systemURL = c4cUser.getSystem().getUrl();
			URI uri = c4cODataUriBuilder.buildForStandard(systemURL, "ProductFindByCategory", true,
					new QueryOption<String>("CategoryID", "'" + category + "'"),
					new QueryOption<Integer>("$skip", pageRequest.getOffset()),
					new QueryOption<Integer>("$top", pageRequest.getPageSize()),
					new QueryOption<String>("$inlinecount", "allpages"),
					new QueryOption<String>("$expand", "ProductCategoryAssignment"));
			HttpHeaders headers = new HttpHeaders();

			headers.add("token", "a2F5ZHU6V2VsY29tZTE=");
			ResponseEntity<C4CCollectionResponse<C4CProduct>> productsRes = c4cRestTemplate.exchange(uri, HttpMethod.GET,
					new HttpEntity<String>(headers), new ParameterizedTypeReference<C4CCollectionResponse<C4CProduct>>() {
					});
			C4CCollectionEntry<C4CProduct> entry = productsRes.getBody().getD();

			if (entry != null && (entry.getCount() != 0 || entry.getResults().size() != 0)) {
				return entry;
			}
		}

		return null;
	}

	private Set<C4CProduct> findAll(URI uri, HttpHeaders headers) {
		ResponseEntity<C4CCollectionResponse<C4CProduct>> productsRes = c4cRestTemplate.exchange(uri, HttpMethod.GET,
				new HttpEntity<String>(headers), new ParameterizedTypeReference<C4CCollectionResponse<C4CProduct>>() {
				});

		if (!productsRes.getBody().getD().getResults().isEmpty()) {
			Iterator<C4CProduct> it = productsRes.getBody().getD().getResults().iterator();
			Set<C4CProduct> products = new LinkedHashSet<>();

			while (it.hasNext()) {
				products.add(it.next());
			}

			return products;
		}

		return null;
	}

}
