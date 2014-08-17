package org.jeedevframework.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jeedevframework.core.common.domain.IDEntity;

import org.jeedevframework.core.Constant;

@Entity
@Table(name=Constant.DB_PREFIX + "app")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class App extends IDEntity {
	private static final long serialVersionUID = 1L;

	@Column(name="app_code",nullable=false,unique=true)
	private String appCode;
	
	@Column(name="app_name",nullable=false)
	private String appName;
	
	@Temporal(TemporalType.DATE)
	@Column(name="publish_date")
	private Date publishDate;
	
	
	public String getAppCode() {
		return appCode;
	}


	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}


	public String getAppName() {
		return appName;
	}


	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Date getPublishDate() {
		return publishDate;
	}


	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}


	@Override
	public String toString() {
		return "App [id=" + id + ", createDate=" + createDate + ", updateDate="
				+ updateDate + ", isDelete=" + isDelete + ", order=" + order
				+ ", pageBean=" + pageBean + ", page=" + page + ", rows="
				+ rows + ", appCode=" + appCode + ", appName=" + appName
				+ ", publishDate=" + publishDate + "]";
	}

	
}
