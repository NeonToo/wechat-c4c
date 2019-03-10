package com.sap.csc.domain.model.jpa.c4c;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sap.csc.domain.model.jpa.ModifiableEntity;
import com.sap.csc.domain.model.jpa.wechat.WechatPublicPlatform;

/**
 * @author I326950
 */
@Entity
@Table(name = "SYSTEM")
public class C4CSystem extends ModifiableEntity implements Serializable {

	private static final long serialVersionUID = 8297614541986010780L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "url", unique = true)
	private String url;

	@OneToMany(mappedBy = "system")
	private Set<C4CUser> c4cUsers;

	@OneToMany(mappedBy = "system")
	private Set<WechatPublicPlatform> wechatPublicPlatforms;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<C4CUser> getC4cUsers() {
		return c4cUsers;
	}

	public void setC4cUsers(Set<C4CUser> c4cUsers) {
		this.c4cUsers = c4cUsers;
	}

	public Set<WechatPublicPlatform> getWechatPublicPlatforms() {
		return wechatPublicPlatforms;
	}

	public void setWechatPublicPlatforms(Set<WechatPublicPlatform> wechatPublicPlatforms) {
		this.wechatPublicPlatforms = wechatPublicPlatforms;
	}

}
