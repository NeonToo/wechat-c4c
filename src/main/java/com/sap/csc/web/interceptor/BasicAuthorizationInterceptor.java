package com.sap.csc.web.interceptor;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.sap.csc.domain.model.jpa.c4c.C4CUser;
import com.sap.csc.domain.persistence.repository.c4c.C4CUserRepository;

@Component
public class BasicAuthorizationInterceptor implements ClientHttpRequestInterceptor {

	@Autowired
	private C4CUserRepository c4CUserRepository;

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		String token = null;
		List<String> tokenHeaders = request.getHeaders().get("token");
		List<String> idHeaders = request.getHeaders().get("id");

		if (CollectionUtils.isNotEmpty(tokenHeaders) && StringUtils.isNotBlank(tokenHeaders.get(0))) {
			token = tokenHeaders.get(0);
			request.getHeaders().remove("token");
		}

		if (CollectionUtils.isNotEmpty(idHeaders) && StringUtils.isNotBlank(idHeaders.get(0))) {
			C4CUser c4cUser = c4CUserRepository.findOne(Long.parseLong(idHeaders.get(0)));
			
			token = c4cUser.getPassword();
			request.getHeaders().remove("id");
		}

		request.getHeaders().add("Authorization", "Basic " + token);
		request.getHeaders().add("Accept-Language", "zh");
		
		return execution.execute(request, body);
	}

}
