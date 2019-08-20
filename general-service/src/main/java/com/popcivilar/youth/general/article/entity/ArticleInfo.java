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
     * 博文类别

     */
    @Column(name = "TYPE_ID")
    private Integer typeId;
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

    @Column(name = "ATTR_FLAG")
    private String attrFlag;//文章属性 普通为1   隐私为2  置顶 为9

    @Column(name = "RE_PRINT_FLAG")
    private String rePrintFlag; //是否转载 1:转载 0:原创

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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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