package com.sap.csc.service.sales.impl;

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
import org.springframework.transaction.annotation.Transactional;

import com.sap.csc.domain.model.c4c.sales.C4CSalesOrder;
import com.sap.csc.domain.model.c4c.sales.C4CSalesOrderItem;
import com.sap.csc.domain.model.c4c.sales.C4CSalesOrderParty;
import com.sap.csc.domain.model.dto.response.c4c.C4CCollectionEntry;
import com.sap.csc.domain.model.dto.response.c4c.C4CCollectionResponse;
import com.sap.csc.domain.model.dto.response.c4c.C4CEntityResponse;
import com.sap.csc.domain.model.jpa.c4c.C4CUser;
import com.sap.csc.domain.persistence.repository.c4c.C4CUserRepository;
import com.sap.csc.service.GeneralServiceImpl;
import com.sap.csc.service.sales.SalesOrderService;
import com.sap.csc.util.C4CRestTemplate;
import com.sap.csc.util.C4CUUIDUtils;
import com.sap.csc.util.builder.C4CODataUriBuilder;
import com.sap.csc.util.builder.QueryOption;

@Service
public class SalesOrderServiceImpl extends GeneralServiceImpl implements SalesOrderService {

	private final static String ORDER_TYPE_CODE = "2059";

	private final C4CRestTemplate c4cRestTemplate;

	private final C4CUserRepository c4CUserRepository;

	private final C4CODataUriBuilder c4cODataUriBuilder;

	@Autowired
	public SalesOrderServiceImpl(C4CRestTemplate c4cRestTemplate, C4CODataUriBuilder c4cODataUriBuilder,
			C4CUserRepository c4CUserRepository) {
		this.c4cRestTemplate = c4cRestTemplate;
		this.c4cODataUriBuilder = c4cODataUriBuilder;
		this.c4CUserRepository = c4CUserRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<C4CSalesOrder> findByUUID(String openID, String uuid) {
		C4CSalesOrder order = null;
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			String systemUrl = c4cUser.getSystem().getUrl();
			URI uri = c4cODataUriBuilder.buildForCustom(systemUrl,
					"order/SalesOrderCollection('" + C4CUUIDUtils.convertToStandardForm(uuid) + "')", true);
			HttpHeaders headers = new HttpHeaders();

			headers.add("id", c4cUser.getId().toString());
			ResponseEntity<C4CEntityResponse<C4CSalesOrder>> orderRes = c4cRestTemplate.exchange(uri, HttpMethod.GET,
					new HttpEntity<String>(headers),
					new ParameterizedTypeReference<C4CEntityResponse<C4CSalesOrder>>() {
					});

			if (orderRes.getBody().getD().getResult() != null) {
				order = orderRes.getBody().getD().getResult();
			}
		}

		return Optional.ofNullable(order);
	}

	@Override
	public Optional<C4CSalesOrder> findByID(String openID, String id) {
		C4CSalesOrder order = null;
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			String systemUrl = c4cUser.getSystem().getUrl();
			URI uri = c4cODataUriBuilder.buildForCustom(systemUrl, "order/SalesOrderCollection", true,
					new QueryOption<String>("$filter", "TypeCode eq '" + ORDER_TYPE_CODE + "'"),
					new QueryOption<String>("$filter", "InternalID eq '" + id + "'"),
					new QueryOption<String>("$expand", "Items"));
			HttpHeaders headers = new HttpHeaders();

			headers.add("id", c4cUser.getId().toString());
			ResponseEntity<C4CCollectionResponse<C4CSalesOrder>> ordersRes = c4cRestTemplate.exchange(uri,
					HttpMethod.GET, new HttpEntity<String>(headers),
					new ParameterizedTypeReference<C4CCollectionResponse<C4CSalesOrder>>() {
					});

			if (!ordersRes.getBody().getD().getResults().isEmpty()) {
				Iterator<C4CSalesOrder> it = ordersRes.getBody().getD().getResults().iterator();

				while (it.hasNext()) {
					order = it.next();
					break;
				}
			}
		}

		return Optional.ofNullable(order);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<C4CSalesOrder> findByURI(String openID, String uri) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);
		C4CSalesOrder order = null;

		if (c4cUser != null) {
			HttpHeaders headers = new HttpHeaders();

			headers.add("id", c4cUser.getId().toString());
			ResponseEntity<C4CEntityResponse<C4CSalesOrder>> ordersRes = c4cRestTemplate.exchange(uri, HttpMethod.GET,
					new HttpEntity<String>(headers),
					new ParameterizedTypeReference<C4CEntityResponse<C4CSalesOrder>>() {
					});

			if (ordersRes.getBody().getD().getResult() != null) {
				order = ordersRes.getBody().getD().getResult();
			}
		}

		return Optional.ofNullable(order);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<C4CSalesOrder> findByOwner(String openID) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			String systemUrl = c4cUser.getSystem().getUrl();
			String ownerID = c4cUser.getEmployeeInternalID();
			URI uri = c4cODataUriBuilder.buildForCustom(systemUrl, "order/SalesOrderCollection", true,
					new QueryOption<String>("$filter", "TypeCode eq '" + ORDER_TYPE_CODE + "'"),
					new QueryOption<String>("$filter", "OwnerID eq '" + ownerID + "'"),
					new QueryOption<String>("$expand", "Items"));
			HttpHeaders headers = new HttpHeaders();

			headers.add("id", c4cUser.getId().toString());
			return this.findOrders(uri, headers);
		}

		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public C4CCollectionEntry<C4CSalesOrder> findByOwner(String openID, PageRequest pageRequest) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			String systemUrl = c4cUser.getSystem().getUrl();
			String ownerID = c4cUser.getEmployeeInternalID();
			URI uri = c4cODataUriBuilder.buildForCustom(systemUrl, "order/SalesOrderCollection", false,
					new QueryOption<Integer>("$skip", pageRequest.getOffset()),
					new QueryOption<Integer>("$top", pageRequest.getPageSize()),
					new QueryOption<String>("$inlinecount", "allpages"), new QueryOption<String>("$expand", "Items"),
					new QueryOption<String>("$filter",
							"TypeCode eq '" + ORDER_TYPE_CODE + "' and " + "OwnerID eq '" + ownerID + "'"));
			HttpHeaders headers = new HttpHeaders();

			headers.add("id", c4cUser.getId().toString());
			ResponseEntity<C4CCollectionResponse<C4CSalesOrder>> ordersRes = c4cRestTemplate.exchange(uri,
					HttpMethod.GET, new HttpEntity<String>(headers),
					new ParameterizedTypeReference<C4CCollectionResponse<C4CSalesOrder>>() {
					});
			C4CCollectionEntry<C4CSalesOrder> entry = ordersRes.getBody().getD();

			if (entry != null && entry.getCount() != 0) {
				return entry;
			}
		}

		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Set<C4CSalesOrderItem> findItems(String openID, String objectID) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			String systemUrl = c4cUser.getSystem().getUrl();
			URI uri = c4cODataUriBuilder.buildForCustom(systemUrl,
					"order/SalesOrderCollection('" + objectID + "')/Items", true);
			HttpHeaders headers = new HttpHeaders();

			headers.add("id", c4cUser.getId().toString());
			ResponseEntity<C4CCollectionResponse<C4CSalesOrderItem>> itemsRes = c4cRestTemplate.exchange(uri,
					HttpMethod.GET, new HttpEntity<String>(headers),
					new ParameterizedTypeReference<C4CCollectionResponse<C4CSalesOrderItem>>() {
					});

			if (!itemsRes.getBody().getD().getResults().isEmpty()) {
				Iterator<C4CSalesOrderItem> it = itemsRes.getBody().getD().getResults().iterator();
				Set<C4CSalesOrderItem> items = new LinkedHashSet<>();

				while (it.hasNext()) {
					items.add(it.next());
				}

				return items;
			}
		}

		return null;
	}

	@Override
	public Set<C4CSalesOrderItem> findItemsByURI(String openID, String url) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			HttpHeaders headers = new HttpHeaders();

			headers.add("id", c4cUser.getId().toString());
			ResponseEntity<C4CCollectionResponse<C4CSalesOrderItem>> itemsRes = c4cRestTemplate.exchange(url,
					HttpMethod.GET, new HttpEntity<String>(headers),
					new ParameterizedTypeReference<C4CCollectionResponse<C4CSalesOrderItem>>() {
					});

			if (!itemsRes.getBody().getD().getResults().isEmpty()) {
				Iterator<C4CSalesOrderItem> it = itemsRes.getBody().getD().getResults().iterator();
				Set<C4CSalesOrderItem> items = new LinkedHashSet<>();

				while (it.hasNext()) {
					items.add(it.next());
				}

				return items;
			}
		}

		return null;
	}

	@Override
	public Set<C4CSalesOrderParty> findPartiesByURI(String openID, String url) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			HttpHeaders headers = new HttpHeaders();

			headers.add("id", c4cUser.getId().toString());
			ResponseEntity<C4CCollectionResponse<C4CSalesOrderParty>> partiesRes = c4cRestTemplate.exchange(url,
					HttpMethod.GET, new HttpEntity<String>(headers),
					new ParameterizedTypeReference<C4CCollectionResponse<C4CSalesOrderParty>>() {
					});

			if (!partiesRes.getBody().getD().getResults().isEmpty()) {
				Iterator<C4CSalesOrderParty> it = partiesRes.getBody().getD().getResults().iterator();
				Set<C4CSalesOrderParty> parties = new LinkedHashSet<>();

				while (it.hasNext()) {
					parties.add(it.next());
				}

				return parties;
			}
		}

		return null;
	}

	private Set<C4CSalesOrder> findOrders(URI uri, HttpHeaders headers) {
		ResponseEntity<C4CCollectionResponse<C4CSalesOrder>> ordersRes = c4cRestTemplate.exchange(uri, HttpMethod.GET,
				new HttpEntity<String>(headers),
				new ParameterizedTypeReference<C4CCollectionResponse<C4CSalesOrder>>() {
				});

		if (!ordersRes.getBody().getD().getResults().isEmpty()) {
			Iterator<C4CSalesOrder> it = ordersRes.getBody().getD().getResults().iterator();
			Set<C4CSalesOrder> orders = new LinkedHashSet<>();

			while (it.hasNext()) {
				orders.add(it.next());
			}

			return orders;
		}

		return null;
	}

	@Override
	public Set<C4CSalesOrder> findByCustomerAndOwner(String openID, String customerID) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			String systemUrl = c4cUser.getSystem().getUrl();
			String ownerID = c4cUser.getEmployeeInternalID();
			URI uri = c4cODataUriBuilder.buildForCustom(systemUrl, "order/SalesOrderCollection", true,
					new QueryOption<String>("$filter", "TypeCode eq '" + ORDER_TYPE_CODE + "'"),
					new QueryOption<String>("$filter", "OwnerID eq '" + ownerID + "'"),
					new QueryOption<String>("$filter", "CustomerID eq '" + customerID + "'"),
					new QueryOption<String>("$expand", "Items"));
			HttpHeaders headers = new HttpHeaders();

			headers.add("id", c4cUser.getId().toString());
			return this.findOrders(uri, headers);
		}

		return null;
	}

	@Override
	public C4CCollectionEntry<C4CSalesOrder> findByCustomerAndOwner(String openID, String customerID,
			PageRequest pageRequest) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			String systemUrl = c4cUser.getSystem().getUrl();
			String ownerID = c4cUser.getEmployeeInternalID();
			URI uri = c4cODataUriBuilder.buildForCustom(systemUrl, "order/SalesOrderCollection", false,
					new QueryOption<Integer>("$skip", pageRequest.getOffset()),
					new QueryOption<Integer>("$top", pageRequest.getPageSize()),
					new QueryOption<String>("$inlinecount", "allpages"), new QueryOption<String>("$expand", "Items"),
					new QueryOption<String>("$filter",
							"TypeCode eq '" + ORDER_TYPE_CODE + "' and " + "OwnerID eq '" + ownerID + "'"),
					new QueryOption<String>("$filter", "CustomerID eq '" + customerID + "'"));
			HttpHeaders headers = new HttpHeaders();

			headers.add("id", c4cUser.getId().toString());
			ResponseEntity<C4CCollectionResponse<C4CSalesOrder>> ordersRes = c4cRestTemplate.exchange(uri,
					HttpMethod.GET, new HttpEntity<String>(headers),
					new ParameterizedTypeReference<C4CCollectionResponse<C4CSalesOrder>>() {
					});
			C4CCollectionEntry<C4CSalesOrder> entry = ordersRes.getBody().getD();

			if (entry != null && entry.getCount() != 0) {
				return entry;
			}
		}

		return null;
	}
}
