package com.sap.csc.domain.model.dto.response.party.customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;

import com.sap.csc.domain.model.c4c.customer.C4CAccount;
import com.sap.csc.domain.model.dto.response.GeneralCollectionResponse;
import com.sap.csc.domain.model.dto.response.c4c.C4CCollectionEntry;

/**
 * @author I326950
 */
public class AccountCollectionResponse extends GeneralCollectionResponse<AccountResponse> {

	private static final long serialVersionUID = -2235101547215835716L;
	
	public AccountCollectionResponse(@SuppressWarnings("rawtypes") Collection emptyCollection) {
		super(new ArrayList<>());
	}
	
	public AccountCollectionResponse(Set<C4CAccount> customers) {
		super(customers.stream().map(customer -> new AccountResponse(customer)).collect(Collectors.toList()));
	}

	public AccountCollectionResponse(List<C4CAccount> customers) {
		super(customers.stream().map(customer -> new AccountResponse(customer)).collect(Collectors.toList()));
	}

	public AccountCollectionResponse(C4CCollectionEntry<C4CAccount> entry, PageRequest pageRequest) {
		super(entry.getResults().stream().map(customer -> new AccountResponse(customer)).collect(Collectors.toList()),
				pageRequest, entry.getCount());
	}

}
