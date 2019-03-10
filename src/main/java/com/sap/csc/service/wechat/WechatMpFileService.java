package com.sap.csc.service.wechat;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface WechatMpFileService {

	public void uploadMpFile(MultipartFile file);

	public Resource loadMpFile(String string);

}
