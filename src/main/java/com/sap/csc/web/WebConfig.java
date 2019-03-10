package com.sap.csc.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.jaxrs.servlet.CXFNonSpringJaxrsServlet;
import org.apache.olingo.odata2.core.rest.app.ODataApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sap.csc.domain.odata.WechatODataJPAServiceFactory;
import com.sap.csc.web.interceptor.CustomizedHandlerInterceptor;

/*
 * @author I326950
 */
@Configuration
@CrossOrigin
public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired(required = false)
	protected List<CustomizedHandlerInterceptor> customizedHandlerInterceptors;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		if (!CollectionUtils.isEmpty(this.customizedHandlerInterceptors)) {
			for (final CustomizedHandlerInterceptor customizedHandlerInterceptor : this.customizedHandlerInterceptors) {
				registry.addInterceptor(customizedHandlerInterceptor);
			}
		}
		super.addInterceptors(registry);
	}

	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();

		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);

		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);

		return bean;
	}

	@Bean
	public ServletRegistrationBean oDataServletRegistration() {
		ServletRegistrationBean odataServletRegistrationBean = new ServletRegistrationBean(
				new CXFNonSpringJaxrsServlet(), "/odata.svc/*");
		Map<String, String> initParameters = new HashMap<String, String>();

		initParameters.put("javax.ws.rs.Application", ODataApplication.class.getName());
		initParameters.put("org.apache.olingo.odata2.service.factory", WechatODataJPAServiceFactory.class.getName());
		odataServletRegistrationBean.setInitParameters(initParameters);

		return odataServletRegistrationBean;
	}
}
