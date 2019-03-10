package com.sap.csc.domain.persistence.repository.c4c;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.csc.domain.model.jpa.c4c.C4CUser;

public interface C4CUserRepository extends JpaRepository<C4CUser, Long> {
	
	public List<C4CUser> findAllByOpenID(String openID);

	public C4CUser findPrimaryOneByOpenID(String openID);

	public C4CUser findByEmployeeUUID(String employeeUUID);

}
