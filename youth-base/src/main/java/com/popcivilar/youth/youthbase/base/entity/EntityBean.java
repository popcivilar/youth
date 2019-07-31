package com.popcivilar.youth.youthbase.base.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * 数据库实体类的基础类，凡是要存入数据库的bean均要继承此类，按照规范后台所有表中一定要有相应的字段
 */
public abstract class EntityBean<K extends Serializable> implements
		Serializable {
	private static final long serialVersionUID = 2741604299092756219L;

	public abstract K getId();

	public abstract void setId(K id);

	@Column(name = "DELETED_FLAG")
	private String deletedFlag;

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name = "CREATE_DATE")
	private Date createDate;

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name = "MODIFY_DATE")
	private Date modifyDate;


	public String getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(String deletedFlag) {
		this.deletedFlag = deletedFlag;
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
