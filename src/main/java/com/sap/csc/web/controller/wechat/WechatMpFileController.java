package com.sap.csc.web.controller.wechat;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.helpers.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sap.csc.service.wechat.WechatMpFileService;
import com.sap.csc.web.controller.GeneralController;

@RestController
public class WechatMpFileController extends GeneralController {
	
	private final WechatMpFileService wechatMpFileService;

	@Autowired
	public WechatMpFileController(WechatMpFileService wechatMpFileService) {
		this.wechatMpFileService = wechatMpFileService;
	}
	
	@GetMapping("/{filename}{extension:\\.txt+}")
	public void loadFromRoot(@PathVariable String filename, @PathVariable String extension) {
		this.loadMpFile(filename + extension);
	}
	
	@GetMapping("/mp/{filename}{extension:\\.txt+}")
	public void loadFromMpFolder(@PathVariable String filename, @PathVariable String extension) {
		this.loadMpFile(filename + extension);
	}
	
	@PostMapping("/wechat/mp")
	public void uploadMpFile(@RequestBody MultipartFile file) {
		if (!file.isEmpty()) {
			wechatMpFileService.uploadMpFile(file);
		}
	}
	
	private void loadMpFile(String filename) {
		HttpServletResponse response = super.getHttpServletResponse();
		
		try {
			Resource resource = wechatMpFileService.loadMpFile(filename);
			
			if(resource != null) {
				response.setContentLengthLong(resource.contentLength());
				response.setContentType("text/plain");
				response.addDateHeader("Last-Modified", resource.lastModified());
				IOUtils.copy(resource.getInputStream(), super.getHttpServletResponse().getOutputStream());
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
