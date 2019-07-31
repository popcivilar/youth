package com.popcivilar.youth.youthbase.base.entity;

import java.io.Serializable;

public class UniParam<T> implements Serializable {

    int page;

    int pageSize;

    T inParam;

    String[]  sort;

    String[]  sortName;

    String isMultiLang;//N是非多语言，Y是多语言
    
    String languageCode;

    public String getIsMultiLang() {
        return isMultiLang;
    }

    public void setIsMultiLang(String isMultiLang) {
        this.isMultiLang = isMultiLang;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public T getInParam() {
        return inParam;
    }

    public void setInParam(T inParam) {
        this.inParam = inParam;
    }

    public String[] getSort() {
        return sort;
    }

    public void setSort(String[] sort) {
        this.sort = sort;
    }

    public String[] getSortName() {
        return sortName;
    }

    public void setSortName(String[] sortName) {
        this.sortName = sortName;
    }

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		languageCode = (languageCode!=null && languageCode.indexOf("-")>0)?languageCode.replace("-", "_"):languageCode;
		this.languageCode = languageCode;
	}
    
}