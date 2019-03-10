package com.sap.csc.domain.model.dto.response.party.customer;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.PageRequest;

import com.sap.csc.domain.model.dto.response.GeneralCollectionResponse;

/**
 * @author I326950
 */
public class AccountAddressPagedResponse extends GeneralCollectionResponse<AccountAddressResponse> implements Serializable {

	private static final long serialVersionUID = 8533759199357916586L;
	
	private Long count;
	
	private Collection<AccountAddressResponse> addresses;
	
	public AccountAddressPagedResponse(List<AccountAddressResponse> addresses, PageRequest pageRequest) {
		super(addresses, pageRequest, addresses.size());
	}
	
//	public AccountAddressPagedResponse(Long count, List<C4CAccountAddress> addresses) {
//		this.count = count;
//		this.addresses = addresses.stream().map(address -> new AccountAddressResponse(address)).collect(Collectors.toList());
//	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Collection<AccountAddressResponse> getAddresses() {
		return addresses;
	}

	public void setAddresses(Collection<AccountAddressResponse> addresses) {
		this.addresses = addresses;
	}

}
