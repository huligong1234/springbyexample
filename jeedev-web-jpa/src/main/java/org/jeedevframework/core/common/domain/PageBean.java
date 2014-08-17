package org.jeedevframework.core.common.domain;

import javax.servlet.http.HttpServletRequest;

import org.jeedevframework.core.util.RequestUtil;

public class PageBean {
	public static int DEFAULT_ROWS = 10;
	private int page;
	private int rows;
	private int total;
	
	public PageBean() {
		super();
	}

	public PageBean(int page, int rows) {
		super();
		this.page = page;
		this.rows = rows;
	}

	public static PageBean getInstance(HttpServletRequest request){
		int curPage = RequestUtil.getInt(request, "page", 1);
		int curRows = RequestUtil.getInt(request, "rows", DEFAULT_ROWS);
		return new PageBean(curPage,curRows);
	}
	
	/**
	 * <p>偏移量</p>
	 * @return 偏移量
	 */
	public int getOffset() {
		return (this.page-1) * this.rows;
	}
	
	/**
	 * 
	 * @return from
	 */
	public int getFrom() {
		if ((this.page <= 0) || (this.rows == -1)) {
			return 0;
		} else {
			return (this.page - 1) * this.rows;
		}
	}
	

	/**
	 * @return to
	 */
	public int getTo() {
		if ((this.page <= 0) || (this.rows == -1)) {
			return this.total - 1;
		} else {
			return this.page * this.rows;
		}
	}
	
	/**
	 * 
	 * @return totalPage
	 */
	public int getTotalPage() {
		int ret = 0;
		if (this.rows <= 0) {
			return ret;
		}
		ret = this.total / this.rows;
		if (this.total > ret * this.rows) {
			ret++;
		}
		return ret;
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

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
