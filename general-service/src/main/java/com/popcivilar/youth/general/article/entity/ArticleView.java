package com.popcivilar.youth.general.article.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 文章信息视图
 * 
 * @author zhangch
 * 
 * @date 2019-07-07
 */
@Entity
public class ArticleView implements Serializable {


    @Column(name = "TYPE_ID")
    private Integer typeId;

    @Column(name = "TYPE_NAME")
    private String typeName;

    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_HEAD")
    private String userHead;

    @Column(name = "ARTICLE_ID")
    private Integer articleId;

    @Column(name = "ARTICLE_NAME")
    private String articleName;

    @Column(name = "ARTICLE_ABSTRACT")
    private String articleAbstract;

    @Column(name = "PUB_DATE")
    private Date pubDate;

    private List<String> picShowList;//图片集合

    @Column(name = "ATTR_FLAG")
    private String attrFlag;//文章属性 普通为1   隐私为2  置顶 为9

    @Column(name = "RE_PRINT_FLAG")
    private String rePrintFlag; //是否转载 1:转载 0:原创

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleAbstract() {
        return articleAbstract;
    }

    public void setArticleAbstract(String articleAbstract) {
        this.articleAbstract = articleAbstract;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public List<String> getPicShowList() {
        return picShowList;
    }

    public void setPicShowList(List<String> picShowList) {
        this.picShowList = picShowList;
    }

    public String getAttrFlag() {
        return attrFlag;
    }

    public void setAttrFlag(String attrFlag) {
        this.attrFlag = attrFlag;
    }

    public String getRePrintFlag() {
        return rePrintFlag;
    }

    public void setRePrintFlag(String rePrintFlag) {
        this.rePrintFlag = rePrintFlag;
    }
}