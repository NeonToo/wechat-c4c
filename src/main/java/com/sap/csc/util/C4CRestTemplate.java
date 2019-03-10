package com.sap.csc.util;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class C4CRestTemplate extends RestTemplate {
	
	@Autowired(required = false)
	protected List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors;
	
	@PostConstruct
	public void addInterceptors() {
		super.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient()));
		super.setInterceptors(clientHttpRequestInterceptors);
	}

	public HttpClient httpClient() {
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		
		connectionManager.setDefaultMaxPerRoute(1);
		connectionManager.setMaxTotal(20);
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().setConnectionManager(connectionManager).build();
		
		return httpClient;
	}
}
