package com.sap.csc.domain.model.dto.response.party.customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;

import com.sap.csc.domain.model.c4c.customer.C4CIndividualCustomer;
import com.sap.csc.domain.model.dto.response.GeneralCollectionResponse;
import com.sap.csc.domain.model.dto.response.c4c.C4CCollectionEntry;

public class IndividualCustomerCollectionResponse extends GeneralCollectionResponse<IndividualCustomerResponse> {

	private static final long serialVersionUID = 1350503939583988728L;
	
	public IndividualCustomerCollectionResponse(@SuppressWarnings("rawtypes") Collection emptyCollection) {
		super(new ArrayList<>());
	}

	public IndividualCustomerCollectionResponse(Set<C4CIndividualCustomer> customers) {
		super(customers.stream().map(customer -> new IndividualCustomerResponse(customer)).collect(Collectors.toList()));
	}

	public IndividualCustomerCollectionResponse(List<C4CIndividualCustomer> customers) {
		super(customers.stream().map(customer -> new IndividualCustomerResponse(customer)).collect(Collectors.toList()));
	}

	public IndividualCustomerCollectionResponse(C4CCollectionEntry<C4CIndividualCustomer> entry, PageRequest pageRequest) {
		super(entry.getResults().stream().map(customer -> new IndividualCustomerResponse(customer)).collect(Collectors.toList()),
				pageRequest, entry.getCount());
	}
}
