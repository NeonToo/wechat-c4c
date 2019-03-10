package com.sap.csc.domain.model.jpa.wechat;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sap.csc.domain.model.jpa.ModifiableEntity;
import com.sap.csc.domain.model.jpa.c4c.C4CSystem;

/**
 * @author I326950
 */
@Entity
@Table(name = "WECHAT_PUB_PLATFORM")
public class WechatPublicPlatform extends ModifiableEntity implements Serializable {
	private static final long serialVersionUID = 4620588699079569484L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "originID", unique = true)
	private String originID;

	@Column(name = "appID", unique = true)
	private String appID;

	@Column(name = "appSecret")
	private String appSecret;

	@Column(name = "token")
	private String token;

	@Column(name = "aesKey")
	private String aesKey;

	@Column(name = "partnerID", unique = true)
	private String partnerID;

	@Column(name = "partnerKey")
	private String partnerKey;

	@Column(name = "c4cTagID")
	private Integer c4cTagID;

	@Column(name = "customerTagID")
	private Integer customerTagID;

	@Column(name = "supplierTagID")
	private Integer supplierTagID;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "systemId")
	private C4CSystem system;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOriginID() {
		return originID;
	}

	public void setOriginID(String originID) {
		this.originID = originID;
	}

	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAesKey() {
		return aesKey;
	}

	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}

	public String getPartnerID() {
		return partnerID;
	}

	public void setPartnerID(String partnerID) {
		this.partnerID = partnerID;
	}

	public String getPartnerKey() {
		return partnerKey;
	}

	public void setPartnerKey(String partnerKey) {
		this.partnerKey = partnerKey;
	}

	public Integer getC4cTagID() {
		return c4cTagID;
	}

	public void setC4cTagID(Integer c4cTagID) {
		this.c4cTagID = c4cTagID;
	}

	public Integer getCustomerTagID() {
		return customerTagID;
	}

	public void setCustomerTagID(Integer customerTagID) {
		this.customerTagID = customerTagID;
	}

	public Integer getSupplierTagID() {
		return supplierTagID;
	}

	public void setSupplierTagID(Integer supplierTagID) {
		this.supplierTagID = supplierTagID;
	}

	public C4CSystem getSystem() {
		return system;
	}

	public void setSystem(C4CSystem system) {
		this.system = system;
	}

}
