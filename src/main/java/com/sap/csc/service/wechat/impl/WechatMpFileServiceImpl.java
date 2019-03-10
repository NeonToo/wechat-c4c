package com.sap.csc.service.wechat.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sap.csc.domain.exception.storage.StorageException;
import com.sap.csc.service.wechat.WechatMpFileService;

@Service
public class WechatMpFileServiceImpl implements WechatMpFileService {

	private final Path rootPath;

	private final ResourceLoader resourceLoader;

	@Autowired
	public WechatMpFileServiceImpl(ResourceLoader resourceLoader) {
		this.rootPath = Paths.get("src/main/resources/mp/");
		this.resourceLoader = resourceLoader;
	}

	// @PostConstruct
	// public void initFolder() {
	// try {
	// if (!Files.isDirectory(rootPath)) {
	// Files.createDirectory(rootPath);
	// }
	// } catch (IOException e) {
	// throw new StorageException();
	// }
	// }

	@Override
	public void uploadMpFile(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), rootPath.resolve(file.getOriginalFilename()));
		} catch (IOException e) {
			throw new StorageException("INVALID_FILE", "Failed to store file" + file.getOriginalFilename());
		}
	}

	@Override
	public Resource loadMpFile(String filename) {
		Resource resource = resourceLoader.getResource("classpath:/mp/" + filename);

		if (resource.exists() && resource.isReadable()) {
			return resource;
		}

		return null;
	}
}
