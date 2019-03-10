package com.sap.csc.domain.persistence.repository.wechat;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.csc.domain.model.jpa.wechat.WechatUser;

public interface WechatUserRepository extends JpaRepository<WechatUser, Long> {
	
	public WechatUser findByOpenId(String openId);
	
	public boolean openIdExist(String openId);
	
}
