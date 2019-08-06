package com.popcivilar.youth.admin.article.entity;

import com.popcivilar.youth.admin.userInfo.entity.UserInfo;

import java.util.Date;

/**
 * 文章信息
 * 
 * @author zhangch
 * 
 * @date 2019-07-07
 */
public class ArticleInfoDto {
    private Integer id;

    /**
     * 发布日期
            
     */
    private Date pubDate;

    /**
     * 发表用户
            
     */
    private Integer userId;

    /**
     * 博文标题
            
     */
    private String title;

    /**
     * 博文类别
            
     */
    private String type;

    private Integer typeId;

    /**
     * 博文内容
            
     */
    private String content;

    /**
     * 点赞数
            
            
     */
    private Integer thumbNum;

    /**
     * 评论数
            
     */
    private Integer commentNum;

    /**
     * 浏览量
            
     */
    private Integer readNum;

    /**
     * 逻辑删除标识;0:未删除,1:已删除
     */
    private String deletedFlag;

    private Date createDate;

    private Date modifyDate;

    private UserInfo userInfo;//用户信息

    private String markdownContent;

    private String userCode;

    private String keyword;//搜索关键词

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

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getMarkdownContent() {
        return markdownContent;
    }

    public void setMarkdownContent(String markdownContent) {
        this.markdownContent = markdownContent;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}