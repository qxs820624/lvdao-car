package com.lvdao.common.pagination;

import java.io.Serializable;
import java.util.List;

public class PageList<T> implements Serializable {

	private static final long serialVersionUID = 2736216261663068694L;
	private List<T> list;
	private Page page;

	public PageList(List<T> list, Page page) {
		this.list = list;
		this.page = page;
	}

	public List<T> getList() {
		return this.list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Page getPage() {
		return this.page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String toString() {
		return "PageList [list=" + this.list + ", page=" + this.page + "]";
	}
	
}
