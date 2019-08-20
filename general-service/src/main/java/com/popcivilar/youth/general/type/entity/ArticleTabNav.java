package com.popcivilar.youth.general.type.entity;

import com.popcivilar.youth.general.article.entity.ArticleView;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 类别信息
 * 
 * @author zhangch
 * 
 * @date 2019-07-30
 */
public class ArticleTabNav implements Serializable {

    private Integer id;

    /**
     * 分类名称

     */
    private String typeName;

    /**
     * 分类描述

     */
    private String typeDesc;

    /**
     * 父评论ID

     */
    private Integer partentId;

    /**
     * 逻辑删除标识;0:未删除,1:已删除
     */
    private String deletedFlag;

    private Date createDate;

    private Date modifyDate;

    private List<String> picUrlList;

    private List<ArticleView> articleViewList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc == null ? null : typeDesc.trim();
    }

    public Integer getPartentId() {
        return partentId;
    }

    public void setPartentId(Integer partentId) {
        this.partentId = partentId;
    }

    public String getDeletedFlag() {
        return deletedFlag;
    }

    public void setDeletedFlag(String deletedFlag) {
        this.deletedFlag = deletedFlag == null ? null : deletedFlag.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public List<String> getPicUrlList() {
        return picUrlList;
    }

    public void setPicUrlList(List<String> picUrlList) {
        this.picUrlList = picUrlList;
    }

    public List<ArticleView> getArticleViewList() {
        return articleViewList;
    }

    public void setArticleViewList(List<ArticleView> articleViewList) {
        this.articleViewList = articleViewList;
    }
}