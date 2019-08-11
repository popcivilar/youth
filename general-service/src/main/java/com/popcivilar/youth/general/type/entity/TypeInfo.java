package com.popcivilar.youth.general.type.entity;

import com.popcivilar.youth.youthbase.base.entity.EntityBean;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 类别信息
 * 
 * @author zhangch
 * 
 * @date 2019-07-30
 */
public class TypeInfo extends EntityBean<Integer> {

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

}