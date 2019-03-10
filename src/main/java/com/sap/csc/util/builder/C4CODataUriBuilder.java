package com.sap.csc.util.builder;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Component;

@Component
public class C4CODataUriBuilder {
	private static final String C4C_STANDARD_ODATA_PATTERN = "/sap/c4c/odata/v1/c4codata/";

	private static final String C4C_CUS_ODATA_PATTERN = "/sap/c4c/odata/cust/v1/";

	private static final String PARAM_PREFIX = "$";

	private static final String JSON_FORMAT_KEY = "format";

	private static final String JSON_FORMAT_VALUE = "json";
	
	public URI build(String url, boolean isJson, QueryOption<?>... queryOptions) {
		return this.buildURI(url, isJson, queryOptions);
	}

	/*
	 * @param url The C4C system URL
	 * @param entityName Name of the requested entity
	 * @param isJson Whether the format type is JSON
	 * @param queryOptions Array of query parameters
	 */
	public URI buildForStandard(String url, String entityName, boolean isJson, QueryOption<?>... queryOptions) {
		return this.buildURI(url + C4C_STANDARD_ODATA_PATTERN + entityName, isJson, queryOptions);
	}

	/*
	 * @param url The C4C system URL
	 * @param entityName Name of the requested entity
	 * @param isJson Whether the format type is JSON
	 * @param queryOptions Array of query parameters
	 */
	public URI buildForCustom(String url, String entityName, boolean isJson, QueryOption<?>... queryOptions) {
		return this.buildURI(url + C4C_CUS_ODATA_PATTERN + entityName, isJson, queryOptions);
	}

	public URI buildURI(String url, boolean isJson, QueryOption<?>... queryOptions) {
		try {
			URIBuilder uriBuilder = new URIBuilder(url);

			if (ArrayUtils.isNotEmpty(queryOptions)) {
				for (QueryOption<?> queryOption : queryOptions) {
					uriBuilder.addParameter(queryOption.getKey(), queryOption.getValue().toString());
				}
			}
			
			if(isJson) {
				return uriBuilder.addParameter(PARAM_PREFIX + JSON_FORMAT_KEY, JSON_FORMAT_VALUE).build();
			}

			return uriBuilder.build();

		} catch (URISyntaxException e) {
//			throw new InvalidRequestUrlException(url);
			// TODO handle URISyntaxException
		}
		
		return null;
	}

}
