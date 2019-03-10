package com.sap.csc.service.party;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.PageRequest;

import com.sap.csc.domain.model.c4c.customer.C4CAccountAddress;
import com.sap.csc.domain.model.c4c.customer.C4CIndividualCustomer;
import com.sap.csc.domain.model.c4c.customer.C4CAccount;
import com.sap.csc.domain.model.dto.response.c4c.C4CCollectionEntry;

public interface CustomerService {

	public Set<C4CAccount> findAccountsByOwner(String openID);

	public Optional<C4CAccount> findAccountByUUID(String openID, String uuid);

	public Optional<C4CAccount> findAccountByURI(String openID, String uri);

	public Set<C4CAccountAddress> findAccountAddressByURI(String openID, String uri);

	public C4CCollectionEntry<C4CAccountAddress> pagedQueryAccountAddressByURI(String openID, String uri);

	public C4CCollectionEntry<C4CAccount> findAccountsByOwner(String openID, PageRequest pageRequest);

	public Optional<C4CAccount> findAccountByID(String openID, String id);

	public Set<C4CIndividualCustomer> findIndividualsByOwner(String openID);

	public C4CCollectionEntry<C4CIndividualCustomer> findIndividualsByOwner(String openID, PageRequest pageRequest);

	public Optional<C4CIndividualCustomer> findIndividualCustomerByID(String openID, String id);

}
