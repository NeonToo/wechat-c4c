package com.sap.csc.service.wechat.impl;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.csc.domain.model.enumeration.wechat.WechatUserRole;
import com.sap.csc.domain.model.jpa.wechat.WechatUser;
import com.sap.csc.domain.persistence.repository.wechat.WechatPublicPlatformRepository;
import com.sap.csc.domain.persistence.repository.wechat.WechatUserRepository;
import com.sap.csc.service.wechat.WechatUserService;
import com.sap.csc.service.wechat.WxPublicService;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Service
public class WechatUserServiceImpl implements WechatUserService {

	private final static String MENU_FILE_PATH = "/menu/";

	private final WechatUserRepository wechatUserRepository;

	private final WxPublicService wxPublicService;

	private final WechatPublicPlatformRepository wechatPublicPlatformRepository;

	@Autowired
	public WechatUserServiceImpl(WechatUserRepository wechatUserRepository, WxPublicService wxPublicService,
			WechatPublicPlatformRepository wechatPublicPlatformRepository) {
		this.wechatUserRepository = wechatUserRepository;
		this.wxPublicService = wxPublicService;
		this.wechatPublicPlatformRepository = wechatPublicPlatformRepository;
	}

	@Override
	public InputStream getUserConfig(String originId, String openId) {
		WechatUser wechatUser = wechatUserRepository.findByOpenId(openId);

		if (wechatUser != null) {
			String menuFilename = StringUtils.lowerCase(wechatUser.getRole().toString()) + ".json";

			return this.getClass().getResourceAsStream(MENU_FILE_PATH + menuFilename);
		}

		return null;
	}

	@Override
	@Transactional
	public Optional<WechatUser> addWechatUser(WxMpUser wxMpUser) {
		WechatUser wechatUser = new WechatUser();
		Calendar subscribeTime = Calendar.getInstance();

		subscribeTime.setTimeInMillis(wxMpUser.getSubscribeTime());
		wechatUser.setOpenId(wxMpUser.getOpenId());
		wechatUser.setSubscribe(wxMpUser.getSubscribe());
		wechatUser.setNickname(wxMpUser.getNickname());
		wechatUser.setSexId(wxMpUser.getSexId());
		wechatUser.setSex(wxMpUser.getSex());
		wechatUser.setLanguage(wxMpUser.getLanguage());
		wechatUser.setCity(wxMpUser.getCity());
		wechatUser.setProvince(wxMpUser.getProvince());
		wechatUser.setCountry(wxMpUser.getCountry());
		wechatUser.setHeadImgUrl(wxMpUser.getHeadImgUrl());
		wechatUser.setSubscribeTime(subscribeTime);
		wechatUser.setUnionId(wxMpUser.getUnionId());
		wechatUser.setRemark(wxMpUser.getRemark());
		wechatUser.setGroupId(wxMpUser.getGroupId());
		wechatUser.setRole(WechatUserRole.UNKNOWN);

		return Optional.ofNullable(wechatUserRepository.saveAndFlush(wechatUser));
	}

	@Override
	@Transactional(readOnly = true)
	public boolean openIDExist(String openId) {
		return wechatUserRepository.openIdExist(openId);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<WechatUser> findByOpenID(String openId) {
		return Optional.ofNullable(wechatUserRepository.findByOpenId(openId));
	}

	@Override
	public Optional<WechatUser> findByOpenIDAndForceUpdate(String originID, String openID) {
		WxMpService wxMpService = wxPublicService.getWxMpService(originID);
		
		if(wxMpService != null) {
			WxMpUser wxMpUser;
			try {
				wxMpUser = wxMpService.getUserService().userInfo(openID);
				
				if(wxMpUser != null) {
					WechatUser wechatUser = wechatUserRepository.findByOpenId(openID);
					
					if(wechatUser != null) {
						Calendar subscribeTime = Calendar.getInstance();

						subscribeTime.setTimeInMillis(wxMpUser.getSubscribeTime());
						wechatUser.setOpenId(wxMpUser.getOpenId());
						wechatUser.setSubscribe(wxMpUser.getSubscribe());
						wechatUser.setNickname(wxMpUser.getNickname());
						wechatUser.setSexId(wxMpUser.getSexId());
						wechatUser.setSex(wxMpUser.getSex());
						wechatUser.setLanguage(wxMpUser.getLanguage());
						wechatUser.setCity(wxMpUser.getCity());
						wechatUser.setProvince(wxMpUser.getProvince());
						wechatUser.setCountry(wxMpUser.getCountry());
						wechatUser.setHeadImgUrl(wxMpUser.getHeadImgUrl());
						wechatUser.setSubscribeTime(subscribeTime);
						wechatUser.setUnionId(wxMpUser.getUnionId());
						wechatUser.setRemark(wxMpUser.getRemark());
						wechatUser.setGroupId(wxMpUser.getGroupId());
						
						return Optional.ofNullable(wechatUserRepository.saveAndFlush(wechatUser)); 
					}
				}
			} catch (WxErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return Optional.ofNullable(null);
	}

	@Override
	public Optional<WechatUser> updateUserTag(String type, String originID, String openID) {
		WechatUser wechatUser = wechatUserRepository.findByOpenId(openID);

		if (wechatUser != null) {
			try {
				switch (type) {
				case "c4c":
					wxPublicService.getWxUserTagService(originID)
							.batchTagging(new Long(
									(long) wechatPublicPlatformRepository.findByOriginID(originID).getC4cTagID()),
									new String[] { openID });
					wechatUser.setRole(WechatUserRole.C4C);
					break;
				case "customer":
					wxPublicService.getWxUserTagService(originID)
							.batchTagging(new Long(
									(long) wechatPublicPlatformRepository.findByOriginID(originID).getCustomerTagID()),
									new String[] { openID });
					wechatUser.setRole(WechatUserRole.CUSTOMER);
					break;
				case "supplier":
					wxPublicService.getWxUserTagService(originID)
							.batchTagging(new Long(
									(long) wechatPublicPlatformRepository.findByOriginID(originID).getSupplierTagID()),
									new String[] { openID });
					wechatUser.setRole(WechatUserRole.SUPPLIER);
					break;
				}

				wechatUser = wechatUserRepository.saveAndFlush(wechatUser);
			} catch (WxErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return Optional.ofNullable(wechatUser);
	}

}
