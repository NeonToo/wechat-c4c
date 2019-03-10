package com.sap.csc.web.controller.party;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.csc.domain.exception.CommonApplicationException;
import com.sap.csc.domain.model.c4c.customer.C4CAccountAddress;
import com.sap.csc.domain.model.c4c.customer.C4CIndividualCustomer;
import com.sap.csc.domain.model.c4c.customer.C4CAccount;
import com.sap.csc.domain.model.dto.response.c4c.C4CCollectionEntry;
import com.sap.csc.domain.model.dto.response.party.customer.AccountAddressPagedResponse;
import com.sap.csc.domain.model.dto.response.party.customer.AccountAddressResponse;
import com.sap.csc.domain.model.dto.response.party.customer.AccountCollectionResponse;
import com.sap.csc.domain.model.dto.response.party.customer.AccountResponse;
import com.sap.csc.domain.model.dto.response.party.customer.IndividualCustomerCollectionResponse;
import com.sap.csc.domain.model.dto.response.party.customer.IndividualCustomerResponse;
import com.sap.csc.service.party.CustomerService;
import com.sap.csc.web.controller.GeneralController;

@RestController
public class CustomerController extends GeneralController {

	private final static String DECODE_NAME = "UTF-8";

	private final CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/individuals")
	public IndividualCustomerCollectionResponse findIndividualsByOwner(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {
		Optional<String> openID = super.getCurrentUserOpenID();

		if (openID.isPresent()) {
			if (page == null) {
				Set<C4CIndividualCustomer> customers = customerService.findIndividualsByOwner(openID.get());

				if (CollectionUtils.isNotEmpty(customers)) {
					return new IndividualCustomerCollectionResponse(customers);
				}
			} else {
				final PageRequest pageRequest = new PageRequest(page, size);
				C4CCollectionEntry<C4CIndividualCustomer> entry = customerService.findIndividualsByOwner(openID.get(),
						pageRequest);

				if (entry != null) {
					return new IndividualCustomerCollectionResponse(entry, pageRequest);
				}
			}
		}

		return new IndividualCustomerCollectionResponse(CollectionUtils.EMPTY_COLLECTION);
	}

	@GetMapping("/accounts")
	public AccountCollectionResponse findAccountsByOwner(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {
		Optional<String> openID = super.getCurrentUserOpenID();

		if (openID.isPresent()) {
			if (page == null) {
				Set<C4CAccount> customers = customerService.findAccountsByOwner(openID.get());

				if (CollectionUtils.isNotEmpty(customers)) {
					return new AccountCollectionResponse(customers);
				}
			} else {
				final PageRequest pageRequest = new PageRequest(page, size);
				C4CCollectionEntry<C4CAccount> entry = customerService.findAccountsByOwner(openID.get(), pageRequest);

				if (entry != null) {
					return new AccountCollectionResponse(entry, pageRequest);
				}
			}
		}

		return new AccountCollectionResponse(CollectionUtils.EMPTY_COLLECTION);
	}

	@GetMapping("/accounts/{id}")
	public AccountResponse findAccountByID(@PathVariable String id) {
		Optional<String> openID = super.getCurrentUserOpenID();

		if (openID.isPresent()) {
			Optional<C4CAccount> customer = customerService.findAccountByID(openID.get(), id);

			if (customer.isPresent()) {
				return new AccountResponse(customer.get());
			}
		}

		return null;
	}
	
	@GetMapping("/individuals/{id}")
	public IndividualCustomerResponse findIndividualCustomerByID(@PathVariable String id) {
		Optional<String> openID = super.getCurrentUserOpenID();

		if (openID.isPresent()) {
			Optional<C4CIndividualCustomer> customer = customerService.findIndividualCustomerByID(openID.get(), id);

			if (customer.isPresent()) {
				return new IndividualCustomerResponse(customer.get());
			}
		}

		return null;
	}

	@GetMapping("/accounts/account")
	public AccountResponse findAccountByUuidOrURI(@RequestParam(required = false) String uuid,
			@RequestParam(required = false) String uri) {
		Optional<String> openID = super.getCurrentUserOpenID();

		if (openID.isPresent()) {
			Optional<C4CAccount> customer = null;

			if (StringUtils.isNotBlank(uuid)) {
				customer = customerService.findAccountByUUID(openID.get(), uuid);
			}

			if (StringUtils.isBlank(uuid) && StringUtils.isNotBlank(uri)) {
				try {
					customer = customerService.findAccountByURI(openID.get(), URLDecoder.decode(uri, DECODE_NAME));
				} catch (UnsupportedEncodingException e) {
					throw new CommonApplicationException("UNSUPPORTED_ENCODING", "The encoding is unsupported");
				}
			}

			if (customer.isPresent()) {
				return new AccountResponse(customer.get());
			}
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/accounts/{id}/addresses")
	public Collection<AccountAddressResponse> findAccountAddressByURI(@PathVariable String id, @RequestParam String uri) {
		Optional<String> openID = super.getCurrentUserOpenID();

		if (openID.isPresent()) {
			try {
				Set<C4CAccountAddress> addresses = customerService.findAccountAddressByURI(openID.get(),
						URLDecoder.decode(uri, DECODE_NAME));

				if (!CollectionUtils.isEmpty(addresses)) {
					return addresses.stream().map(address -> new AccountAddressResponse(address))
							.collect(Collectors.toList());
				}
			} catch (UnsupportedEncodingException e) {
				throw new CommonApplicationException("UNSUPPORTED_ENCODING", "The encoding is unsupported");
			}
		}

		return CollectionUtils.EMPTY_COLLECTION;
	}

	@GetMapping("/customer/addresses/paged")
	public AccountAddressPagedResponse pagedQueryAccountAddressByURI(@RequestParam String uri) {
		Optional<String> openID = super.getCurrentUserOpenID();

		if (openID.isPresent()) {
			try {
				C4CCollectionEntry<C4CAccountAddress> entry = customerService.pagedQueryAccountAddressByURI(openID.get(),
						URLDecoder.decode(uri, DECODE_NAME));

				if (entry != null) {
					// return new AccountAddressPagedResponse(entry.getCount(),
					// entry.getResults());
				}
			} catch (UnsupportedEncodingException e) {
				throw new CommonApplicationException("UNSUPPORTED_ENCODING", "The encoding is unsupported");
			}
		}

		return null;
	}

}
