package com.sap.csc.domain.persistence.repository.wechat.impl;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sap.csc.domain.model.jpa.wechat.WechatUser;
import com.sap.csc.domain.persistence.repository.wechat.WechatUserRepository;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class WechatUserRepositoryImpl extends SimpleJpaRepository<WechatUser, Long> implements WechatUserRepository {

	public WechatUserRepositoryImpl(EntityManager em) {
		super(WechatUser.class, em);
	}
	

	@Override
	@Transactional(readOnly = true)
	public WechatUser findByOpenId(String openId) {
		return super.findOne((root, query, cb) -> {
			return cb.equal(root.get("openId"), openId);
		});
	}

	@Override
	@Transactional(readOnly = true)
	public boolean openIdExist(String openId) {
		return this.findByOpenId(openId) != null;
	}

}
