package com.popcivilar.youth.youthbase.base.entity;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;

/**
 * @desc 通用结果集封装
 * @author
 * @time
 */
public class UniPage<T> implements Serializable {
	/**
	 * 页码，从1开始
	 */
	private int pageNum;
	/**
	 * 页面大小
	 */
	private int pageSize;
	/**
	 * 起始行
	 */
	private int startRow;
	/**
	 * 末行
	 */
	private int endRow;
	/**
	 * 总数
	 */
	private Long total;
	/**
	 * 总页数
	 */
	private int pages;


	private List<T> data;

	public UniPage(Page page) {
		this.pageNum = page.getPageNum();
		this.endRow = page.getEndRow();
		this.startRow = page.getStartRow();
		this.pages = page.getPages();
		this.pageSize = page.getPageSize();
		this.total = page.getTotal();
		this.data = page.getResult();
	}

	public UniPage() {

	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}


}