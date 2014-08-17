package org.jeedevframework.core.common.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.jeedevframework.core.util.DateUtil;

/**
 * 含int型主键的实体标记接口
 */

@MappedSuperclass
public class IDEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date", nullable = false, updatable=false)
	protected Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date", nullable = false)
	protected Date updateDate;
	
	@Column(name = "is_delete")
	protected boolean isDelete = false;
	
	@Column(name = "re_order")
	protected int order = 0;
	
	@Transient
	protected PageBean pageBean;
	
	@Transient
	protected int page = 0;
	
	@Transient
	protected int rows = PageBean.DEFAULT_ROWS;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public boolean isDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	@Transient
	public String getCreateDateString(){
		if(this.getCreateDate() != null){
			return DateUtil.format(this.getCreateDate(), DateUtil.C_TIME_PATTON_DEFAULT);
		}
		return null;
	}
	
	@Transient
	public String getUpdateDateString(){
		if(this.getUpdateDate() != null){
			return DateUtil.format(this.getUpdateDate(), DateUtil.C_TIME_PATTON_DEFAULT);
		}
		return null;
	}
}
