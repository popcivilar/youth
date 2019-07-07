package com.popcivilar.youth.general.article.entity;

import com.popcivilar.youth.youthbase.base.entity.EntityBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 文章信息
 * 
 * @author wcyong
 * 
 * @date 2019-07-07
 */
@Entity
public class ArticleInfo extends EntityBean<Integer> implements Serializable {
    @Id
    @Column(name = "ID")
    private Integer id;

    /**
     * 发布日期
            
     */
    @Column(name = "PUB_DATE")
    private Date pubDate;

    /**
     * 发表用户
            
     */
    @Column(name = "USER_ID")
    private Integer userId;

    /**
     * 博文标题
            
     */
    @Column(name = "TITLE")
    private String title;

    /**
     * 博文类别
            
     */
    @Column(name = "TYPE")
    private String type;

    /**
     * 博文内容
            
     */
    @Column(name = "CONTENT")
    private String content;

    /**
     * 点赞数
            
            
     */
    @Column(name = "THUMB_NUM")
    private Integer thumbNum;

    /**
     * 评论数
            
     */
    @Column(name = "COMMENT_NUM")
    private Integer commentNum;

    /**
     * 浏览量
            
     */
    @Column(name = "READ_NUM")
    private Integer readNum;

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

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getThumbNum() {
        return thumbNum;
    }

    public void setThumbNum(Integer thumbNum) {
        this.thumbNum = thumbNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
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