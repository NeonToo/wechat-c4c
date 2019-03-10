package com.sap.csc.domain.model.dto.response.wechat;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sap.csc.domain.model.jpa.wechat.WechatUser;

/**
 * @author I326950
 */
public class WechatUserResponse implements Serializable {

	private static final long serialVersionUID = -117149850268420672L;

	private Long id;

	private Boolean subscribe;

	private String openId;

	private String nickName;
	
	private Integer sexId;

	private String sex;

	private String language;

	private String city;

	private String province;

	private String country;

	private String headImgUrl;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar subscribeTime;
	// private ZonedDateTime subscribeTime;

	private String unionId;

	private String remark;

	private Integer groupId;
	
	public WechatUserResponse(WechatUser wechatUser) {
		this.id = wechatUser.getId();
		this.openId = wechatUser.getOpenId();
		this.subscribe = wechatUser.getSubscribe();
		this.nickName = wechatUser.getNickname();
		this.sexId = wechatUser.getSexId();
		this.sex = wechatUser.getSex();
		this.language = wechatUser.getLanguage();
		this.city = wechatUser.getCity();
		this.province = wechatUser.getProvince();
		this.country = wechatUser.getCountry();
		this.headImgUrl = wechatUser.getHeadImgUrl();
		this.subscribeTime = wechatUser.getSubscribeTime();
		this.unionId = wechatUser.getUnionId();
		this.remark = wechatUser.getRemark();
		this.groupId = wechatUser.getGroupId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Boolean subscribe) {
		this.subscribe = subscribe;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public Calendar getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(Calendar subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public Integer getSexId() {
		return sexId;
	}

	public void setSexId(Integer sexId) {
		this.sexId = sexId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

}
