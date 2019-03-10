package com.sap.csc.service.party;

import java.util.List;
import java.util.Optional;

import com.sap.csc.domain.model.dto.request.c4c.C4CUserRequest;
import com.sap.csc.domain.model.jpa.c4c.C4CUser;

public interface C4CUserService {

	public Optional<C4CUser> bindC4CUser(String originID, String openID, C4CUserRequest c4cUserRequest);

	public Optional<C4CUser> findOne(Long id);
	
	public Optional<C4CUser> findPrimaryOneByOpenID(String openID);

	public Optional<C4CUser> updateC4CUser(Long id, C4CUserRequest c4cUserRequest);

	public Optional<C4CUser> changePriamryStatus(Long id);

	public void deleteC4CUser(Long id);
	
	public List<C4CUser> findAllByOpenID(String openID);
	
}
