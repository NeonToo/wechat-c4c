package com.sap.csc.web.controller.wechat;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.csc.domain.exception.CommonApplicationException;
import com.sap.csc.domain.model.jpa.wechat.WechatUser;
import com.sap.csc.service.wechat.WechatUserService;
import com.sap.csc.service.wechat.WxPublicService;
import com.sap.csc.util.constant.SessionConstants;
import com.sap.csc.web.controller.GeneralController;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@RestController
@RequestMapping("/wechat/oauth")
public class WxAuthController extends GeneralController {

	private final WxPublicService wxPublicService;
	
	private final WechatUserService wechatUserService;

	@Autowired
	public WxAuthController(WxPublicService wxPublicService, WechatUserService wechatUserService) {
		this.wxPublicService = wxPublicService;
		this.wechatUserService = wechatUserService;
	}

	/**
	 * OAuth Login
	 * 
	 * @param originId
	 * @param redirectUrl
	 */
	@GetMapping
	public void authLogin(@RequestParam("origin_id") String originId, @RequestParam("redirect_url_component") String urlComponnet) {
		WxMpService wxMpService = wxPublicService.getWxMpService(originId);

		// store essential data in session
		super.getHttpSession().setAttribute(SessionConstants.WECHAT_ORIGIN_ID, originId);
		super.getHttpSession().setAttribute("callback_url_component", urlComponnet);
		
		logger.info("Redirect URL: " + super.getServerRootUrl() + urlComponnet);

		try {
			super.getHttpServletResponse().sendRedirect(wxMpService.oauth2buildAuthorizationUrl(
					// OAuth Callback URL
					this.getOAuthCallbackUrl(),
					// Scope - Collect user profile purpose
					WxConsts.OAUTH2_SCOPE_USER_INFO,
					// State - null
					null));
		} catch (IOException ex) {
			String errorMessage = "Something wrong on redirect to wechat oauth login api";
			logger.error(errorMessage, ex);
			throw new CommonApplicationException("OAUTH_REDIRECT_ERROR", errorMessage, ex);
		}
	}

	/**
	 * OAuth Callback
	 * 
	 * @param authorizationCode
	 * @param state
	 */
	@GetMapping("/callback")
	public void oauthLoginCallback(
			// Authorization Code
			@RequestParam(value = "code", required = true) String code,
			// State
			@RequestParam(value = "state", required = false) String state) {
		final String callbackComponent = (String) super.getHttpSession().getAttribute("callback_url_component");
		final String originId = (String) super.getHttpSession().getAttribute(SessionConstants.WECHAT_ORIGIN_ID);
		WxMpService wxMpService = wxPublicService.getWxMpService(originId);

		try {
			WxMpOAuth2AccessToken oAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
			String openId = oAuth2AccessToken.getOpenId();
			WxMpUser wxMpUser = wxMpService.getUserService().userInfo(openId);
			Optional<WechatUser> wxUser = wechatUserService.findByOpenID(openId);
			
			if(wxMpUser != null && wxMpUser.getSubscribe()) {
				if(!wxUser.isPresent()) { // store into DB
					wechatUserService.addWechatUser(wxMpUser);
				}
				
				final Cookie originIdCookie = new Cookie(SessionConstants.WECHAT_ORIGIN_ID, originId);
				final Cookie openIdCookie = new Cookie(SessionConstants.WECHAT_OPEN_ID, openId);

				logger.info(" ----- Wechat User OpenId: " + openId);
				logger.info(" ----- Wechat OAuth Complete, redirect to component: " + callbackComponent);
				
				// store essential data in session
				super.getHttpSession().setAttribute(SessionConstants.WECHAT_ORIGIN_ID, originId);
				super.getHttpSession().setAttribute(SessionConstants.WECHAT_OPEN_ID, openId);

				// store essential data in cookie
				super.getHttpServletResponse().addCookie(originIdCookie);
				super.getHttpServletResponse().addCookie(openIdCookie);
				
				super.getHttpServletResponse().sendRedirect(super.getServerRootUrl() + callbackComponent);
			} else {
				// TODO: operation when not subscribe
				return;
			}
		} catch (WxErrorException | IOException ex) {
			String errorMessage = "Something wrong on handle wechat oauth callback api";
			logger.error(errorMessage, ex);
			throw new CommonApplicationException("OAUTH_CALLBACK_ERROR", errorMessage, ex);
		}
	}

}
