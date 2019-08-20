package com.popcivilar.youth.general.website.entity;

import com.popcivilar.youth.youthbase.base.entity.EntityBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 网站信息
 * 
 * @author wcyong
 * 
 * @date 2019-08-14
 */
@Entity
public class WebSiteInfo extends EntityBean<Integer> implements Serializable {
    @Id
    @Column(name = "ID")
    private Integer id;

    /**
     * 网站标题

     */
    @Column(name = "SITE_TITLE")
    private String siteTitle;

    /**
     * 网站副标题

     */
    @Column(name = "SITE_SUB_TITLE")
    private String siteSubTitle;

    /**
     * 网站描述

     */
    @Column(name = "SITE_DESC")
    private String siteDesc;

    /**
     * 网站ico图标

     */
    @Column(name = "SITE_ICO")
    private String siteIco;

    /**
     * 网站关键词

     */
    @Column(name = "SITE_KEYWORD")
    private String siteKeyword;

    /**
     * 网站网址

     */
    @Column(name = "SITE_URL")
    private String siteUrl;

    /**
     * 逻辑删除标识;0:未删除,1:已删除
     */

    @Column(name = "DELETED_FLAG")
    private String deletedFlag;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "MODIFY_DATE")
    private Date modifyDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSiteTitle() {
        return siteTitle;
    }

    public void setSiteTitle(String siteTitle) {
        this.siteTitle = siteTitle == null ? null : siteTitle.trim();
    }

    public String getSiteSubTitle() {
        return siteSubTitle;
    }

    public void setSiteSubTitle(String siteSubTitle) {
        this.siteSubTitle = siteSubTitle == null ? null : siteSubTitle.trim();
    }

    public String getSiteDesc() {
        return siteDesc;
    }

    public void setSiteDesc(String siteDesc) {
        this.siteDesc = siteDesc == null ? null : siteDesc.trim();
    }

    public String getSiteIco() {
        return siteIco;
    }

    public void setSiteIco(String siteIco) {
        this.siteIco = siteIco == null ? null : siteIco.trim();
    }

    public String getSiteKeyword() {
        return siteKeyword;
    }

    public void setSiteKeyword(String siteKeyword) {
        this.siteKeyword = siteKeyword == null ? null : siteKeyword.trim();
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl == null ? null : siteUrl.trim();
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
}