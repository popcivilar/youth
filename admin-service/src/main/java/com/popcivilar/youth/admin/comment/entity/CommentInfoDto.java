package com.popcivilar.youth.admin.comment.entity;

import com.popcivilar.youth.youthbase.base.entity.EntityBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 评论信息
 * 
 * @author zhangch
 * 
 * @date 2019-08-04
 */
public class CommentInfoDto extends EntityBean<Integer> implements Serializable {
    private Integer id;

    /**
     * 评论文章ID

     */
    private Integer articleId;

    /**
     * 评论日期

     */
    private Date commentDate;

    /**
     * 点赞数

     */
    private Integer thunbNum;

    /**
     * 发表用户

     */
    private Integer userId;

    /**
     * 评论内容

     */
    private String content;

    /**
     * 评论数

     */
    private Integer commentNum;

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

    /**
     * 用户名
     */
    private String userCode;

    private List<CommentInfoDto> childComment;//子评论

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Integer getThunbNum() {
        return thunbNum;
    }

    public void setThunbNum(Integer thunbNum) {
        this.thunbNum = thunbNum;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
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

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public List<CommentInfoDto> getChildComment() {
        return childComment;
    }

    public void setChildComment(List<CommentInfoDto> childComment) {
        this.childComment = childComment;
    }
}