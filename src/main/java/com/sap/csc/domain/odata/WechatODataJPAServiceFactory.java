package com.sap.csc.domain.odata;

import javax.persistence.EntityManagerFactory;

import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.api.exception.ODataJPARuntimeException;

import com.sap.csc.domain.WechatApplicationContext;

public class WechatODataJPAServiceFactory extends org.apache.olingo.odata2.jpa.processor.api.ODataJPAServiceFactory {

	private static final String PERSISTENCE_UNIT_NAME = "wechat";
	
	@Override
	public ODataJPAContext initializeODataJPAContext() throws ODataJPARuntimeException {
		ODataJPAContext oDataJPAContext = getODataJPAContext();

		oDataJPAContext.setEntityManagerFactory(WechatApplicationContext.getBean(EntityManagerFactory.class));
		oDataJPAContext.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
		oDataJPAContext.setODataContext(oDataJPAContext.getODataContext());

		return oDataJPAContext;
	}

}
