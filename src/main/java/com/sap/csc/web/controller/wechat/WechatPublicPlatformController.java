package com.sap.csc.web.controller.wechat;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.csc.domain.model.dto.request.wechat.WechatPublicPlatformRequest;
import com.sap.csc.domain.model.dto.response.wechat.WechatPublicPlatformResponse;
import com.sap.csc.domain.model.jpa.wechat.WechatPublicPlatform;
import com.sap.csc.service.wechat.WechatPublicPlatformService;
import com.sap.csc.web.controller.GeneralController;

@RestController
@RequestMapping("/wechat/platforms")
public class WechatPublicPlatformController extends GeneralController {

	private final WechatPublicPlatformService wechatPublicPlatformService;

	@Autowired
	public WechatPublicPlatformController(WechatPublicPlatformService wechatPublicPlatformService) {
		this.wechatPublicPlatformService = wechatPublicPlatformService;
	}

	@PostMapping
	public WechatPublicPlatformResponse addWechatPublicPlatform(
			@RequestBody WechatPublicPlatformRequest wechatPublicPlatformRequest) {
		logger.info(" ----- New Wechat Public Platform ----- ");
		HttpServletRequest httpRequest = super.getHttpServletRequest();
		String origin = httpRequest.getHeader("Origin");

		logger.info(" ----- Origin ----- " + origin);

		if (StringUtils.isNotBlank(origin)) {
			WechatPublicPlatform wechatPublicPlatform = wechatPublicPlatformService.addWechatPublicPlatform(origin,
					wechatPublicPlatformRequest);

			return new WechatPublicPlatformResponse(wechatPublicPlatform);
		}

		return null;
	}

	@PutMapping
	public WechatPublicPlatformResponse updateWechatPublicPlatform(
			@RequestBody WechatPublicPlatformRequest wechatPublicPlatformRequest) {
		logger.info(" ----- Update Wechat Public Platform ----- ");
		HttpServletRequest httpRequest = super.getHttpServletRequest();
		String origin = httpRequest.getHeader("Origin");

		logger.info(" ----- Origin ----- " + origin);

		if (StringUtils.isNotBlank(origin)) {
			WechatPublicPlatform wechatPublicPlatform = wechatPublicPlatformService.updateWechatPublicPlatform(origin,
					wechatPublicPlatformRequest);

			return new WechatPublicPlatformResponse(wechatPublicPlatform);
		}

		return null;
	}

}
