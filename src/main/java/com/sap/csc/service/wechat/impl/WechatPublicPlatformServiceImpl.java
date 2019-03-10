package com.sap.csc.service.wechat.impl;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.csc.domain.model.dto.request.wechat.WechatPublicPlatformRequest;
import com.sap.csc.domain.model.jpa.c4c.C4CSystem;
import com.sap.csc.domain.model.jpa.wechat.WechatPublicPlatform;
import com.sap.csc.domain.persistence.repository.system.SystemRepository;
import com.sap.csc.domain.persistence.repository.wechat.WechatPublicPlatformRepository;
import com.sap.csc.service.GeneralServiceImpl;
import com.sap.csc.service.wechat.WechatPublicPlatformService;
import com.sap.csc.service.wechat.WxPublicService;

import me.chanjar.weixin.mp.api.WxMpService;

@Service
public class WechatPublicPlatformServiceImpl extends GeneralServiceImpl implements WechatPublicPlatformService {

	private final WechatPublicPlatformRepository wechatPublicPlatformRepository;

	private final SystemRepository systemRepository;

	private final WxPublicService wxPublicService;

	@Autowired
	public WechatPublicPlatformServiceImpl(WechatPublicPlatformRepository wechatPublicPlatformRepository,
			SystemRepository systemRepository, WxPublicService wxPublicService) {
		this.wechatPublicPlatformRepository = wechatPublicPlatformRepository;
		this.systemRepository = systemRepository;
		this.wxPublicService = wxPublicService;
	}

	@Override
	@Transactional
	public WechatPublicPlatform addWechatPublicPlatform(String origin,
			WechatPublicPlatformRequest wechatPublicPlatformRequest) {
		C4CSystem system = systemRepository.findByURL(wechatPublicPlatformRequest.getSystemUrl());

		if (system == null) {
			C4CSystem newSystem = new C4CSystem();

			newSystem.setUrl(origin);

			system = systemRepository.saveAndFlush(newSystem);
		}

		WechatPublicPlatform wechatPublicPlatform = new WechatPublicPlatform();
		String originId = wechatPublicPlatformRequest.getOriginID();

		wechatPublicPlatform.setOriginID(originId);
		wechatPublicPlatform.setAppID(wechatPublicPlatformRequest.getAppID());
		wechatPublicPlatform.setAppSecret(wechatPublicPlatformRequest.getAppSecret());
		wechatPublicPlatform.setToken(wechatPublicPlatformRequest.getToken());
		wechatPublicPlatform.setAesKey(wechatPublicPlatformRequest.getAesKey());
		wechatPublicPlatform.setPartnerID(wechatPublicPlatformRequest.getPartnerID());
		wechatPublicPlatform.setPartnerKey(wechatPublicPlatformRequest.getPartnerKey());
		wechatPublicPlatform.setSystem(system);

		WechatPublicPlatform newWechatPublicPlatform = wechatPublicPlatformRepository
				.saveAndFlush(wechatPublicPlatform);
		WxMpService wxMpService = wxPublicService.getWxMpService(originId);

		wxPublicService.createMenu(originId, wxMpService);
		Map<String, Integer> tagIdMap = wxPublicService.createPersonlizedMenu(originId, wxMpService);

		newWechatPublicPlatform.setC4cTagID(tagIdMap.get("C4C"));
		newWechatPublicPlatform.setCustomerTagID(tagIdMap.get("CUSTOMER"));
		newWechatPublicPlatform.setSupplierTagID(tagIdMap.get("SUPPLIER"));

		return wechatPublicPlatformRepository.saveAndFlush(newWechatPublicPlatform);
	}

	@Override
	public WechatPublicPlatform updateWechatPublicPlatform(String origin,
			WechatPublicPlatformRequest wechatPublicPlatformRequest) {
		C4CSystem system = systemRepository.findByURL(wechatPublicPlatformRequest.getSystemUrl());

		if (system == null) {
			C4CSystem newSystem = new C4CSystem();

			newSystem.setUrl(origin);

			system = systemRepository.saveAndFlush(newSystem);
		}

		String originId = wechatPublicPlatformRequest.getOriginID();
		WechatPublicPlatform wechatPublicPlatform = wechatPublicPlatformRepository.findByOriginID(originId);

		wechatPublicPlatform.setOriginID(originId);
		wechatPublicPlatform.setAppID(wechatPublicPlatformRequest.getAppID());
		wechatPublicPlatform.setAppSecret(wechatPublicPlatformRequest.getAppSecret());
		wechatPublicPlatform.setToken(wechatPublicPlatformRequest.getToken());
		wechatPublicPlatform.setAesKey(wechatPublicPlatformRequest.getAesKey());
		wechatPublicPlatform.setPartnerID(wechatPublicPlatformRequest.getPartnerID());
		wechatPublicPlatform.setPartnerKey(wechatPublicPlatformRequest.getPartnerKey());
		wechatPublicPlatform.setSystem(system);

		WechatPublicPlatform newWechatPublicPlatform = wechatPublicPlatformRepository
				.saveAndFlush(wechatPublicPlatform);
		WxMpService wxMpService = wxPublicService.getWxMpService(originId);

		wxPublicService.createMenu(originId, wxMpService);
		Map<String, Integer> tagIdMap = wxPublicService.createPersonlizedMenu(originId, wxMpService);

		newWechatPublicPlatform.setC4cTagID(tagIdMap.get("C4C"));
		newWechatPublicPlatform.setCustomerTagID(tagIdMap.get("CUSTOMER"));
		newWechatPublicPlatform.setSupplierTagID(tagIdMap.get("SUPPLIER"));

		return wechatPublicPlatformRepository.saveAndFlush(newWechatPublicPlatform);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<WechatPublicPlatform> findByAppId(String appID) {
		return Optional.ofNullable(wechatPublicPlatformRepository.findByAppID(appID));
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<WechatPublicPlatform> findOne(String originID) {
		return Optional.ofNullable(wechatPublicPlatformRepository.findByOriginID(originID));
	}
}
