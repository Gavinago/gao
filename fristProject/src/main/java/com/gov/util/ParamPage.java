package com.gov.util;

public class ParamPage {
	Integer pageNum;//当前页号，从1开始，默认1
	Integer pageSize;//分页大小，默认15
	String searchText;//模糊搜索关键字，null不搜索
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
