package com.popcivilar.youth.general.type.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * 类别信息
 * 
 * @author zhangch
 * 
 * @date 2019-07-30
 */
public class TypeInfoDto {

    @Id
    @Column(name = "ID")
    private Integer id;

    /**
     * 分类名称

     */
    @Column(name = "TYPE_NAME")
    private String typeName;

    /**
     * 分类描述

     */
    @Column(name = "TYPE_DESC")
    private String typeDesc;

    /**
     * 父评论ID

     */
    @Column(name = "PARTENT_ID")
    private Integer partentId;

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
}