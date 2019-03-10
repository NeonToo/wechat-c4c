package com.sap.csc.domain.persistence.repository.system.impl;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sap.csc.domain.model.jpa.c4c.C4CSystem;
import com.sap.csc.domain.persistence.repository.system.SystemRepository;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class SystemRepositoryImpl extends SimpleJpaRepository<C4CSystem, Long> implements SystemRepository {

	@Autowired
	public SystemRepositoryImpl(EntityManager em) {
		super(C4CSystem.class, em);
	}

	@Override
	public C4CSystem findByURL(String url) {
		return super.findOne((root, query, cb) -> {
			return cb.equal(root.get("url"), url);
		});
	}
	
}
