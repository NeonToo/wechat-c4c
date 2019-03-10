package com.sap.csc.web.controller.wechat;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;

import javax.servlet.http.Cookie;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.csc.domain.exception.CommonApplicationException;
import com.sap.csc.domain.model.dto.wechat.WxJssdkSignature;
import com.sap.csc.service.wechat.WxPublicService;
import com.sap.csc.util.constant.SessionConstants;
import com.sap.csc.web.controller.GeneralController;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

@RestController
@RequestMapping("/wechat/jssdk")
public class WxJssdkController extends GeneralController {

	private final WxPublicService wxPublicService;

	@Autowired
	public WxJssdkController(WxPublicService wxPublicService) {
		this.wxPublicService = wxPublicService;
	}

	/**
	 * @param url
	 *            need to sign
	 * @return entire signature object
	 */
	@GetMapping("/sign/{originId}")
	public WxJssdkSignature signForJssdk(@RequestParam(value = "url", required = true) URL url,
			@PathVariable String originId) {
		if (StringUtils.isBlank(originId)) {
			final Cookie[] cookies = super.getHttpServletRequest().getCookies();

			if (ArrayUtils.isNotEmpty(cookies)) {
				originId = IterableUtils
						.find(Arrays.asList(cookies),
								c -> StringUtils.equalsIgnoreCase(c.getName(), SessionConstants.WECHAT_ORIGIN_ID))
						.getValue();
			}
		}

		WxMpService wxMpService = wxPublicService.getWxMpService(originId);

		try {
			return new WxJssdkSignature(wxMpService.createJsapiSignature(URLDecoder.decode(url.toString(), "utf-8")));
		} catch (UnsupportedEncodingException ex) {
			String errorMessage = "Something wrong on decoding the url";
			logger.error(errorMessage, ex);
			throw new CommonApplicationException("UNSUPPORTED_ENCODING", errorMessage, ex);
		} catch (WxErrorException ex) {
			String errorMessage = "Something wrong on signature generation of jssdk";
			logger.error(errorMessage, ex);
			throw new CommonApplicationException("JSSDK_SIGNATURE_ERROR", errorMessage, ex);
		}
	}
}
