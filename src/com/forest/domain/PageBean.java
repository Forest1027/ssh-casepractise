package com.forest.domain;

import java.util.List;

public class PageBean<T> {
	private Integer pageNum;
	private Integer currentCount;
	private long totalCount;
	private Integer totalPage;
	private List<T> content;
	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PageBean(Integer pageNum, Integer currentCount, long totalCount, Integer totalPage, List<T> content) {
		super();
		this.pageNum = pageNum;
		this.currentCount = currentCount;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.content = content;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(Integer currentCount) {
		this.currentCount = currentCount;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getContent() {
		return content;
	}
	public void setContent(List<T> content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "PageBean [pageNum=" + pageNum + ", currentCount=" + currentCount + ", totalCount=" + totalCount
				+ ", totalPage=" + totalPage + ", content=" + content + "]";
	}
	
}
