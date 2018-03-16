package com.lvdao.common.pagination;

import java.io.Serializable;

import com.lvdao.common.CommonConst;

public class Page implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1838188739376515457L;
	protected int pageNo = CommonConst.DIGIT_ONE;
	protected int pageSize = CommonConst.DIGIT_TEN;
	protected long totalCount = CommonConst.DIGIT_MINUS_ONE;
	
	public Page() {
		
	}

	public Page(int pageSize) {
		this.pageSize = pageSize;
	}

	public Page(int pageNo, int pageSize) {
		if (pageNo > CommonConst.DIGIT_ZERO) {
			this.pageNo = pageNo;
		}
		if (pageSize > CommonConst.DIGIT_ZERO)
			this.pageSize = pageSize;
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		if (pageNo < CommonConst.DIGIT_ZERO)
			this.pageNo = CommonConst.DIGIT_ONE;
	}

	public int getPageSize(){
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getStartIndex() {
		return (this.pageNo - CommonConst.DIGIT_ONE) * this.pageSize ;
	}

	public long getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
  
	public long getTotalPages() {
		if(this.totalCount < CommonConst.DIGIT_ZERO) {
			return CommonConst.DIGIT_MINUS_ONE;
		}
		
		long totalPages = totalCount / pageSize;

		if(this.totalCount % pageSize > CommonConst.DIGIT_ZERO) {
			totalPages++;
		}
    	return totalPages;
	}
	
	public boolean isHasNext() {
		return pageNo + CommonConst.DIGIT_ONE <= getTotalPages();
	}
	
	public int getNextPage() {
		if(isHasNext()){
			return pageNo + CommonConst.DIGIT_ONE;
		}
		return pageNo;
	}

	public boolean isHasPre() {
		return pageNo - CommonConst.DIGIT_ONE >= CommonConst.DIGIT_ONE;
	}

	public int getPrePage() {
		if(isHasPre()){
			return pageNo - CommonConst.DIGIT_ONE;
		}
		return pageNo;
	}

}
