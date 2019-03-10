package com.sap.csc.service.party.impl;

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

import com.sap.csc.domain.model.c4c.customer.C4CAccountAddress;
import com.sap.csc.domain.model.c4c.customer.C4CIndividualCustomer;
import com.sap.csc.domain.model.c4c.customer.C4CAccount;
import com.sap.csc.domain.model.dto.response.c4c.C4CCollectionEntry;
import com.sap.csc.domain.model.dto.response.c4c.C4CCollectionResponse;
import com.sap.csc.domain.model.dto.response.c4c.C4CEntityResponse;
import com.sap.csc.domain.model.jpa.c4c.C4CUser;
import com.sap.csc.domain.persistence.repository.c4c.C4CUserRepository;
import com.sap.csc.service.GeneralServiceImpl;
import com.sap.csc.service.party.CustomerService;
import com.sap.csc.util.C4CRestTemplate;
import com.sap.csc.util.C4CUUIDUtils;
import com.sap.csc.util.builder.C4CODataUriBuilder;
import com.sap.csc.util.builder.QueryOption;

@Service
public class CustomerServiceImpl extends GeneralServiceImpl implements CustomerService {

	private final C4CRestTemplate c4cRestTemplate;

	private final C4CUserRepository c4CUserRepository;

	private final C4CODataUriBuilder c4cODataUriBuilder;

	@Autowired
	public CustomerServiceImpl(C4CRestTemplate c4cRestTemplate, C4CODataUriBuilder c4cODataUriBuilder,
			C4CUserRepository c4CUserRepository) {
		this.c4cRestTemplate = c4cRestTemplate;
		this.c4cODataUriBuilder = c4cODataUriBuilder;
		this.c4CUserRepository = c4CUserRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Set<C4CAccount> findAccountsByOwner(String openID) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			String systemUrl = c4cUser.getSystem().getUrl();
			String ownerUUID = c4cUser.getEmployeeUUID();
			URI uri = c4cODataUriBuilder.buildForStandard(systemUrl, "AccountCollection", true, new QueryOption<String>(
					"$filter", "OwnerUUID eq guid'" + C4CUUIDUtils.convertToODataForm(ownerUUID) + "'"));
			HttpHeaders headers = new HttpHeaders();

			headers.add("id", c4cUser.getId().toString());
			ResponseEntity<C4CCollectionResponse<C4CAccount>> customersRes = c4cRestTemplate.exchange(uri,
					HttpMethod.GET, new HttpEntity<String>(headers),
					new ParameterizedTypeReference<C4CCollectionResponse<C4CAccount>>() {
					});

			if (!customersRes.getBody().getD().getResults().isEmpty()) {
				Iterator<C4CAccount> it = customersRes.getBody().getD().getResults().iterator();
				Set<C4CAccount> customers = new LinkedHashSet<>();

				while (it.hasNext()) {
					customers.add(it.next());
				}

				return customers;
			}
		}

		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public C4CCollectionEntry<C4CAccount> findAccountsByOwner(String openID, PageRequest pageRequest) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			String systemUrl = c4cUser.getSystem().getUrl();
			String ownerUUID = c4cUser.getEmployeeUUID();
			URI uri = c4cODataUriBuilder.buildForStandard(systemUrl, "AccountCollection", true,
					new QueryOption<Integer>("$skip", pageRequest.getOffset()),
					new QueryOption<Integer>("$top", pageRequest.getPageSize()),
					new QueryOption<String>("$inlinecount", "allpages"),
					new QueryOption<String>("$filter", "OwnerUUID eq guid'" + ownerUUID + "'"));
			HttpHeaders headers = new HttpHeaders();

			headers.add("id", c4cUser.getId().toString());
			ResponseEntity<C4CCollectionResponse<C4CAccount>> customersRes = c4cRestTemplate.exchange(uri,
					HttpMethod.GET, new HttpEntity<String>(headers),
					new ParameterizedTypeReference<C4CCollectionResponse<C4CAccount>>() {
					});
			C4CCollectionEntry<C4CAccount> entry = customersRes.getBody().getD();

			if (entry != null && entry.getCount() != 0) {
				return entry;
			}
		}

		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<C4CAccount> findAccountByID(String openID, String id) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			String systemUrl = c4cUser.getSystem().getUrl();

			URI uri = c4cODataUriBuilder.buildForStandard(systemUrl, "AccountCollection", true,
					new QueryOption<String>("$filter", "AccountID eq '" + id + "'"));
			HttpHeaders headers = new HttpHeaders();

			headers.add("id", c4cUser.getId().toString());
			ResponseEntity<C4CCollectionResponse<C4CAccount>> customersRes = c4cRestTemplate.exchange(uri,
					HttpMethod.GET, new HttpEntity<String>(headers),
					new ParameterizedTypeReference<C4CCollectionResponse<C4CAccount>>() {
					});
			C4CCollectionEntry<C4CAccount> entry = customersRes.getBody().getD();

			if (entry != null && !entry.getResults().isEmpty()) {
				Iterator<C4CAccount> it = entry.getResults().iterator();

				while (it.hasNext()) {
					return Optional.ofNullable(it.next());
				}
			}
		}

		return Optional.ofNullable(null);
	}
	


	@Override
	@Transactional(readOnly = true)
	public Optional<C4CIndividualCustomer> findIndividualCustomerByID(String openID, String id) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			String systemUrl = c4cUser.getSystem().getUrl();

			URI uri = c4cODataUriBuilder.buildForStandard(systemUrl, "IndividualCustomerCollection", true,
					new QueryOption<String>("$filter", "CustomerID eq '" + id + "'"));
			HttpHeaders headers = new HttpHeaders();

			headers.add("id", c4cUser.getId().toString());
			ResponseEntity<C4CCollectionResponse<C4CIndividualCustomer>> customersRes = c4cRestTemplate.exchange(uri,
					HttpMethod.GET, new HttpEntity<String>(headers),
					new ParameterizedTypeReference<C4CCollectionResponse<C4CIndividualCustomer>>() {
					});
			C4CCollectionEntry<C4CIndividualCustomer> entry = customersRes.getBody().getD();

			if (entry != null && !entry.getResults().isEmpty()) {
				Iterator<C4CIndividualCustomer> it = entry.getResults().iterator();

				while (it.hasNext()) {
					return Optional.ofNullable(it.next());
				}
			}
		}

		return Optional.ofNullable(null);
	}

	@Override
	public Optional<C4CAccount> findAccountByUUID(String openID, String uuid) {
		C4CAccount customer = null;
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			String systemUrl = c4cUser.getSystem().getUrl();
			String objectID = uuid.replace("-", "");
			URI uri = c4cODataUriBuilder.buildForStandard(systemUrl, "AccountCollection('" + objectID + "')", true);
			HttpHeaders headers = new HttpHeaders();

			headers.add("id", c4cUser.getId().toString());
			ResponseEntity<C4CEntityResponse<C4CAccount>> customerRes = c4cRestTemplate.exchange(uri, HttpMethod.GET,
					new HttpEntity<String>(headers), new ParameterizedTypeReference<C4CEntityResponse<C4CAccount>>() {
					});

			if (customerRes.getBody().getD().getResult() != null) {
				customer = customerRes.getBody().getD().getResult();
			}
		}

		return Optional.ofNullable(customer);
	}

	@Override
	public Optional<C4CAccount> findAccountByURI(String openID, String uri) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);
		C4CAccount customer = null;

		if (c4cUser != null) {
			HttpHeaders headers = new HttpHeaders();

			headers.add("id", c4cUser.getId().toString());
			ResponseEntity<C4CEntityResponse<C4CAccount>> customerRes = c4cRestTemplate.exchange(
					c4cODataUriBuilder.build(uri, true), HttpMethod.GET, new HttpEntity<String>(headers),
					new ParameterizedTypeReference<C4CEntityResponse<C4CAccount>>() {
					});

			if (customerRes.getBody().getD().getResult() != null) {
				customer = customerRes.getBody().getD().getResult();
			}
		}

		return Optional.ofNullable(customer);
	}

	@Override
	public Set<C4CAccountAddress> findAccountAddressByURI(String openID, String uri) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			HttpHeaders headers = new HttpHeaders();

			headers.add("id", c4cUser.getId().toString());
			ResponseEntity<C4CCollectionResponse<C4CAccountAddress>> addressesRes = c4cRestTemplate.exchange(
					c4cODataUriBuilder.build(uri, true), HttpMethod.GET, new HttpEntity<String>(headers),
					new ParameterizedTypeReference<C4CCollectionResponse<C4CAccountAddress>>() {
					});

			if (!addressesRes.getBody().getD().getResults().isEmpty()) {
				Iterator<C4CAccountAddress> it = addressesRes.getBody().getD().getResults().iterator();
				Set<C4CAccountAddress> addresses = new LinkedHashSet<>();

				while (it.hasNext()) {
					addresses.add(it.next());
				}

				return addresses;
			}
		}

		return null;
	}

	@Override
	public C4CCollectionEntry<C4CAccountAddress> pagedQueryAccountAddressByURI(String openID, String uri) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			HttpHeaders headers = new HttpHeaders();

			headers.add("id", c4cUser.getId().toString());
			ResponseEntity<C4CCollectionResponse<C4CAccountAddress>> addressesRes = c4cRestTemplate.exchange(
					c4cODataUriBuilder.build(uri, true, new QueryOption<String>("$inlinecount", "allpages")),
					HttpMethod.GET, new HttpEntity<String>(headers),
					new ParameterizedTypeReference<C4CCollectionResponse<C4CAccountAddress>>() {
					});
			C4CCollectionEntry<C4CAccountAddress> entry = addressesRes.getBody().getD();

			if (entry != null && entry.getCount() != 0) {
				return entry;
			}
		}

		return null;
	}

	@Override
	public Set<C4CIndividualCustomer> findIndividualsByOwner(String openID) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			String systemUrl = c4cUser.getSystem().getUrl();
			String ownerUUID = c4cUser.getEmployeeUUID();

			URI uri = c4cODataUriBuilder.buildForStandard(systemUrl, "IndividualCustomerCollection", true, new QueryOption<String>(
					"$filter", "OwnerUUID eq guid'" + C4CUUIDUtils.convertToODataForm(ownerUUID) + "'"));
			HttpHeaders headers = new HttpHeaders();

			headers.add("id", c4cUser.getId().toString());
			ResponseEntity<C4CCollectionResponse<C4CIndividualCustomer>> customersRes = c4cRestTemplate.exchange(uri,
					HttpMethod.GET, new HttpEntity<String>(headers),
					new ParameterizedTypeReference<C4CCollectionResponse<C4CIndividualCustomer>>() {
					});

			if (!customersRes.getBody().getD().getResults().isEmpty()) {
				Iterator<C4CIndividualCustomer> it = customersRes.getBody().getD().getResults().iterator();
				Set<C4CIndividualCustomer> customers = new LinkedHashSet<>();

				while (it.hasNext()) {
					customers.add(it.next());
				}

				return customers;
			}
		}

		return null;
	}

	@Override
	public C4CCollectionEntry<C4CIndividualCustomer> findIndividualsByOwner(String openID, PageRequest pageRequest) {
		C4CUser c4cUser = c4CUserRepository.findPrimaryOneByOpenID(openID);

		if (c4cUser != null) {
			String systemUrl = c4cUser.getSystem().getUrl();
			String ownerUUID = c4cUser.getEmployeeUUID();

			URI uri = c4cODataUriBuilder.buildForStandard(systemUrl, "IndividualCustomerCollection", true,
					new QueryOption<Integer>("$skip", pageRequest.getOffset()),
					new QueryOption<Integer>("$top", pageRequest.getPageSize()),
					new QueryOption<String>("$inlinecount", "allpages"),
					new QueryOption<String>("$filter", "OwnerUUID eq guid'" + ownerUUID + "'"));
			HttpHeaders headers = new HttpHeaders();

			headers.add("id", c4cUser.getId().toString());
			ResponseEntity<C4CCollectionResponse<C4CIndividualCustomer>> customersRes = c4cRestTemplate.exchange(uri,
					HttpMethod.GET, new HttpEntity<String>(headers),
					new ParameterizedTypeReference<C4CCollectionResponse<C4CIndividualCustomer>>() {
					});
			C4CCollectionEntry<C4CIndividualCustomer> entry = customersRes.getBody().getD();

			if (entry != null && entry.getCount() != 0) {
				return entry;
			}
		}

		return null;
	}

}
