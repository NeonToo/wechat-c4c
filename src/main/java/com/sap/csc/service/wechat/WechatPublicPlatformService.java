package com.sap.csc.service.wechat;

import java.util.Optional;

import com.sap.csc.domain.model.dto.request.wechat.WechatPublicPlatformRequest;
import com.sap.csc.domain.model.jpa.wechat.WechatPublicPlatform;

public interface WechatPublicPlatformService {

	public WechatPublicPlatform addWechatPublicPlatform(String origin,
			WechatPublicPlatformRequest wechatPublicPlatformRequest);

	public Optional<WechatPublicPlatform> findOne(String originId);

	public Optional<WechatPublicPlatform> findByAppId(String appId);

	public WechatPublicPlatform updateWechatPublicPlatform(String origin,
			WechatPublicPlatformRequest wechatPublicPlatformRequest);

}
