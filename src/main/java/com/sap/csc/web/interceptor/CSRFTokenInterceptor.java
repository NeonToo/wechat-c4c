package com.sap.csc.web.interceptor;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.sap.csc.util.C4CRestTemplate;


@Component
public class CSRFTokenInterceptor implements ClientHttpRequestInterceptor {
	
	private static final String C4C_STANDARD_ODATA_PATTERN = "/sap/c4c/odata/v1/c4codata/";
	
	private static final String C4C_CUS_ODATA_PATTERN = "/sap/c4c/odata/cust/v1/";
	
	private static final String CSRF_TOKEN_HEADER = "X-CSRF-Token";
	
	private static final String CSRF_TOKEN_FETCH = "Fetch";

	private static final HttpMethod[] NON_CSRF_REQUIRED_METHODS = new HttpMethod[] { HttpMethod.GET, HttpMethod.OPTIONS,
			HttpMethod.HEAD, HttpMethod.TRACE };

	private static final HttpMethod[] CSRF_REQUIRED_METHODS = new HttpMethod[] { HttpMethod.POST, HttpMethod.PUT,
			HttpMethod.PATCH, HttpMethod.DELETE };
	
	private final ConcurrentHashMap<String, String> csrfTokenMap = new ConcurrentHashMap<String, String>(1);
	
	private volatile long expiresTime;
	
	@Autowired
	private C4CRestTemplate c4cRestTemplate;

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		String url = request.getURI().toString().trim();
		
		if(url.contains(C4C_STANDARD_ODATA_PATTERN) || url.contains(C4C_CUS_ODATA_PATTERN)) {
			if(ArrayUtils.contains(NON_CSRF_REQUIRED_METHODS, request.getMethod())) {
				request.getHeaders().add(CSRF_TOKEN_HEADER, CSRF_TOKEN_FETCH);
			}
			if(ArrayUtils.contains(CSRF_REQUIRED_METHODS, request.getMethod())) {
				request.getHeaders().add(CSRF_TOKEN_HEADER, getCsrfToken(request.getURI(), request.getHeaders()));
			}
		}

		return execution.execute(request, body);
	}

	private String getCsrfToken(URI uri, HttpHeaders headers) {
		String uriToString = uri.toString();
		
		if(csrfTokenMap.containsKey(uriToString) && !isExpired()) {
			return csrfTokenMap.get(uriToString);
		} else {
			String token = this.fetchCsrfToken(uri);
			
			csrfTokenMap.put(uriToString, token);
			this.refreshExpiredTime();
			
			return token;
		}
	}

	private void refreshExpiredTime() {
		this.expiresTime = System.currentTimeMillis() + 3600 * 1000;// 2 hours
	}

	private String fetchCsrfToken(URI uri) {
		ResponseEntity<String> csrfResponse = c4cRestTemplate.getForEntity(uri, String.class);

		return csrfResponse.getHeaders().getFirst(CSRF_TOKEN_HEADER);
	}

	private boolean isExpired() {
		return System.currentTimeMillis() > this.expiresTime;
	}

}
