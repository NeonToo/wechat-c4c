package com.sap.csc.domain.persistence.repository.wechat.impl;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sap.csc.domain.model.jpa.wechat.WechatPublicPlatform;
import com.sap.csc.domain.persistence.repository.wechat.WechatPublicPlatformRepository;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class WechatPublicPlatformRepositoryImpl extends SimpleJpaRepository<WechatPublicPlatform, Long> implements WechatPublicPlatformRepository {

	@Autowired
	public WechatPublicPlatformRepositoryImpl(EntityManager em) {
		super(WechatPublicPlatform.class, em);
	}

	@Override
	public WechatPublicPlatform findByOriginID(String originID) {
		return super.findOne((root, query, cb) -> {
			return cb.equal(root.get("originID"), originID);
		});
	}
	
	@Override
	public WechatPublicPlatform findByAppID(String appID) {
		return super.findOne((root, query, cb) -> {
			return cb.equal(root.get("appID"), appID);
		});
	}

}
