package com.sap.csc.domain.persistence.repository.c4c.impl;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sap.csc.domain.model.jpa.c4c.C4CUser;
import com.sap.csc.domain.persistence.repository.c4c.C4CUserRepository;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class C4CUserRepositoryImpl extends SimpleJpaRepository<C4CUser, Long> implements C4CUserRepository {

	public C4CUserRepositoryImpl(EntityManager em) {
		super(C4CUser.class, em);
	}

	@Override
	@Transactional(readOnly = true)
	public List<C4CUser> findAllByOpenID(String openID) {
		return super.findAll((root, query, cb) -> {
			return cb.equal(root.get("openID"), openID);
		});
	}

	@Override
	public C4CUser findPrimaryOneByOpenID(String openID) {
		return super.findOne((root, query, cb) -> {
			return cb.and(cb.equal(root.get("isPrimary"), true), cb.equal(root.get("openID"), openID));
		});
	}

	@Override
	public C4CUser findByEmployeeUUID(String employeeUUID) {
		return super.findOne((root, query, cb) -> {
			return cb.equal(root.get("employeeUUID"), employeeUUID);
		});
	}

}
