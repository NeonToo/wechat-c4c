package com.sap.csc.domain.persistence.repository.wechat;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.csc.domain.model.jpa.wechat.WechatPublicPlatform;

public interface WechatPublicPlatformRepository extends JpaRepository<WechatPublicPlatform, Long> {
	
	public WechatPublicPlatform findByOriginID(String originID);
	
	public WechatPublicPlatform findByAppID(String appID);
	
}
