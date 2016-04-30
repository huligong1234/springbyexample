package org.jeedevframework.core.common.entity;

/**
 * 排序辅助
 * */
public class QuerySort {
	public static String ORDER_ASC= "asc";
	public static String ORDER_DESC = "desc";
	private String column;//待排序字段
	private String order;//asc,desc
	
	
	public QuerySort() {
		super();
	}

	public QuerySort(String column, String order) {
		super();
		this.column = column;
		this.order = order;
	}
	
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
}