package com.sap.csc.web.controller.wechat;

import java.io.InputStream;
import java.io.IOException;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.csc.domain.model.dto.request.wechat.WechatUserTagRequest;
import com.sap.csc.domain.model.dto.response.wechat.WechatUserResponse;
import com.sap.csc.domain.model.jpa.wechat.WechatUser;
import com.sap.csc.service.wechat.WechatUserService;
import com.sap.csc.web.controller.GeneralController;

@RestController
@RequestMapping("/wechat/users/user")
public class WechatUserController extends GeneralController {

	private final WechatUserService wechatUserService;

	@Autowired
	public WechatUserController(WechatUserService wechatUserService) {
		this.wechatUserService = wechatUserService;
	}

	@GetMapping("/openid")
	public String getWechatUserOpenId() {
		return super.getCurrentUserOpenID().get();
	}

	@GetMapping
	public WechatUserResponse findByOpenId(@RequestParam(required = false) boolean force) {
		Optional<String> openID = super.getCurrentUserOpenID();

		if (openID.isPresent()) {
			Optional<WechatUser> wechatUser = null;
			
			if(force) {
				Optional<String> originID = super.getCurrentOriginID();
				
				if(originID.isPresent()) {
					wechatUser = wechatUserService.findByOpenIDAndForceUpdate(originID.get(), openID.get());
				}
			} else {
				wechatUser = wechatUserService.findByOpenID(openID.get());
			}

			return wechatUser.isPresent() ? new WechatUserResponse(wechatUser.get()) : null;
		}

		return null;
	}

	@GetMapping("/configuration")
	public void getUserConfig() {
		Optional<String> openId = this.getCurrentUserOpenID();

		if (openId.isPresent()) {
			try {
				InputStream is = wechatUserService.getUserConfig("", openId.get());
				
				if(is != null) {
					IOUtils.copy(is, super.getHttpServletResponse().getOutputStream());
				} else {
					//TODO
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@PutMapping("/tag")
	public WechatUserResponse updateUserTag(@RequestBody WechatUserTagRequest wechatUserTagRequest) {
		Optional<WechatUser> wechatUser = wechatUserService.updateUserTag(wechatUserTagRequest.getType(),
				wechatUserTagRequest.getOriginId(), wechatUserTagRequest.getOpenId());

		if (!wechatUser.isPresent()) {
			return null;
		}

		return new WechatUserResponse(wechatUser.get());
	}

}
