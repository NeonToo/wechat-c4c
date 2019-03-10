package com.sap.csc.web.controller;

import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sap.csc.util.constant.SessionConstants;

/**
 * @author i071053
 */
public abstract class GeneralController {

	private static final int[] HIDDEN_PORTS = new int[] { 80, 443 };

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@Autowired
	private HttpServletResponse httpServletResponse;

	protected HttpSession getHttpSession() {
		return this.httpSession;
	}

	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}

	protected HttpServletRequest getHttpServletRequest() {
		return this.httpServletRequest;
	}

	public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}

	protected HttpServletResponse getHttpServletResponse() {
		return this.httpServletResponse;
	}

	public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
		this.httpServletResponse = httpServletResponse;
	}

	public String getServerRootUrl() {
		return String.format("%s://%s%s%s",
				// Schema Name - e.g.: "http" or "https"
				httpServletRequest.getScheme(),
				// Server Name - e.g.: "localhost" or "wechattemplate.com"
				httpServletRequest.getServerName(),
				// Server Port - e.g.: 80
				ArrayUtils.contains(HIDDEN_PORTS, httpServletRequest.getServerPort()) ? ""
						: (":" + httpServletRequest.getServerPort()),
				// Context Path - e.g.: "wechat/template/xxx" if has
				httpServletRequest.getContextPath());
	}

	public String getOAuthCallbackUrl() {
		return this.getServerRootUrl() + "/wechat/oauth/callback";
	}

	public Optional<String> getCurrentUserOpenID() {
		String openId = null;
		final HttpSession session = this.getHttpSession();

		if (session.getAttribute(SessionConstants.WECHAT_OPEN_ID) != null) {
			openId = (String) session.getAttribute(SessionConstants.WECHAT_OPEN_ID);
			logger.info(" ----- Get Wechat User Open ID from Session ----- " + openId);
			
			return Optional.ofNullable(openId);
		} else {
			final Cookie[] cookies = this.getHttpServletRequest().getCookies();

			if (ArrayUtils.isNotEmpty(cookies)) {
				final Cookie openIdCookie = IterableUtils.find(Arrays.asList(cookies),
						c -> StringUtils.equalsIgnoreCase(c.getName(), SessionConstants.WECHAT_OPEN_ID));

				if (openIdCookie != null) {
					openId = openIdCookie.getValue();
					logger.info(" ----- Get Wechat User Open ID from Cookie ----- " + openId);
					
					return Optional.ofNullable(openId);
				}
			}
			// logger.error(" ----- Error -- Get Wechat User Open ID ----- ");
			// throw new InvalidWechatUserException();
		}
		
		return Optional.of("oxugwv2W2hE863CGBm-vFA8DF5qM");
	}
	
	public Optional<String> getCurrentOriginID() {
		String originID = null;
		final HttpSession session = this.getHttpSession();

		if (session.getAttribute(SessionConstants.WECHAT_ORIGIN_ID) != null) {
			originID = (String) session.getAttribute(SessionConstants.WECHAT_ORIGIN_ID);
			logger.info(" ----- Get Origin ID from Session ----- " + originID);
			
			return Optional.ofNullable(originID);
		} else {
			final Cookie[] cookies = this.getHttpServletRequest().getCookies();

			if (ArrayUtils.isNotEmpty(cookies)) {
				final Cookie openIdCookie = IterableUtils.find(Arrays.asList(cookies),
						c -> StringUtils.equalsIgnoreCase(c.getName(), SessionConstants.WECHAT_ORIGIN_ID));

				if (openIdCookie != null) {
					originID = openIdCookie.getValue();
					logger.info(" ----- Get Origin ID from Cookie ----- " + originID);
					
					return Optional.ofNullable(originID);
				}
			}
			// logger.error(" ----- Error -- Get Wechat User Open ID ----- ");
			// throw new InvalidWechatUserException();
		}
		
		return Optional.of("gh_863e2dbddc10");
	}

	public String getClientIp() {
		return httpServletRequest.getRemoteAddr();
	}
}
