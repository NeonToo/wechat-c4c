package com.sap.csc.domain.model.c4c.custom;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.csc.domain.model.c4c.datatype.C4CDateTime;

/**
 * @author I326950
 */
public class C4CWechatUser implements Serializable {

	private static final long serialVersionUID = 1920786367477877580L;

	@JsonProperty("ObjectID")
	private String objectID;

	@JsonProperty("EmployeeID")
	private String employeeID;

	@JsonProperty("OpenID")
	private String openID;

	@JsonProperty("NickName")
	private String nickname;

	@JsonProperty("Gender")
	private String genderID;

	@JsonProperty("GenderText")
	private String gender;

	@JsonProperty("Language")
	private String language;

	@JsonProperty("City")
	private String city;

	@JsonProperty("Province")
	private String province;

	@JsonProperty("Country")
	private String country;

	@JsonProperty("HeadImageURL")
	private String headImgUrl;

	@JsonProperty("SubscriptionStatus")
	private String subscribeStatusId;

	@JsonProperty("SubscriptionStatusText")
	private String subscribeStatus;

	@JsonProperty("SubscribeTime")
	private C4CDateTime subscribeTime;

	@JsonProperty("UnionID")
	private String unionID;

	@JsonProperty("Remark")
	private String remark;

	@JsonProperty("GroupID")
	private Integer groupID;

	public String getObjectID() {
		return objectID;
	}

	public void setObjectID(String objectID) {
		this.objectID = objectID;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGenderID() {
		return genderID;
	}

	public void setGenderID(String genderID) {
		this.genderID = genderID;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getSubscribeStatusId() {
		return subscribeStatusId;
	}

	public void setSubscribeStatusId(String subscribeStatusId) {
		this.subscribeStatusId = subscribeStatusId;
	}

	public String getSubscribeStatus() {
		return subscribeStatus;
	}

	public void setSubscribeStatus(String subscribeStatus) {
		this.subscribeStatus = subscribeStatus;
	}

	public C4CDateTime getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(C4CDateTime subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public String getUnionID() {
		return unionID;
	}

	public void setUnionID(String unionID) {
		this.unionID = unionID;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getGroupID() {
		return groupID;
	}

	public void setGroupID(Integer groupID) {
		this.groupID = groupID;
	}

	// TODO add OriginID

}
