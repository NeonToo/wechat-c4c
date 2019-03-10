package com.sap.csc.domain.model.jpa.wechat;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sap.csc.domain.model.enumeration.wechat.WechatUserRole;
import com.sap.csc.domain.model.jpa.ModifiableEntity;

/**
 * @author I326950
 */
@Entity
@Table(name = "WECHAT_USER")
public class WechatUser extends ModifiableEntity implements Serializable {

	private static final long serialVersionUID = 1569486180860914816L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "subscribe")
	private Boolean subscribe;

	@Column(name = "openId")
	private String openId;

	@Column(name = "nickName")
	private String nickName;

	@Column(name = "sex")
	private String sex;

	@Column(name = "language")
	private String language;

	@Column(name = "city")
	private String city;

	@Column(name = "province")
	private String province;

	@Column(name = "country")
	private String country;

	@Column(name = "headImgUrl")
	private String headImgUrl;

	@Column(name = "subscribeTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar subscribeTime;
	// private ZonedDateTime subscribeTime;

	@Column(name = "unionId")
	private String unionId;

	@Column(name = "sexId")
	private Integer sexId;

	@Column(name = "remark")
	private String remark;

	@Column(name = "groupId")
	private Integer groupId;

	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	private WechatUserRole role;

//	@ManyToMany
//	@JoinTable(name = "M_WECHAT_TAGS",
//			// self
//			joinColumns = @JoinColumn(name = "wechatUserId"),
//			// inverse
//			inverseJoinColumns = @JoinColumn(name = "tagId"))
//	private Set<WechatUserTag> tags;

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

	public String getNickname() {
		return nickName;
	}

	public void setNickname(String nickName) {
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public WechatUserRole getRole() {
		return role;
	}

	public void setRole(WechatUserRole role) {
		this.role = role;
	}

//	public Set<WechatUserTag> getTags() {
//		return tags;
//	}
//
//	public void setTags(Set<WechatUserTag> tags) {
//		this.tags = tags;
//	}

}
