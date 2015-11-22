package org.jeedevframework.core.common.domain;

import java.util.Date;

import org.jeedevframework.core.util.DateUtil;

/**
 * 含int型主键的实体标记接口
 */

public class IDEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	protected Integer id;
	
	protected Date createDate;
	
	protected Date updateDate;
	
	protected boolean isDelete = false;
	
	protected int reOrder = 0;
	
	protected PageBean pageBean;
	
	protected int page = 0;
	
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
	public int getReOrder() {
		return reOrder;
	}
	public void setReOrder(int reOrder) {
		this.reOrder = reOrder;
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
	
	public String getCreateDateString(){
		if(this.getCreateDate() != null){
			return DateUtil.format(this.getCreateDate(), DateUtil.C_TIME_PATTON_DEFAULT);
		}
		return null;
	}
	
	public String getUpdateDateString(){
		if(this.getUpdateDate() != null){
			return DateUtil.format(this.getUpdateDate(), DateUtil.C_TIME_PATTON_DEFAULT);
		}
		return null;
	}
}
