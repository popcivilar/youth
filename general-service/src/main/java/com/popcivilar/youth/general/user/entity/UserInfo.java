package com.popcivilar.youth.general.user.entity;

import com.popcivilar.youth.youthbase.base.entity.EntityBean;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 * 
 *
 * @date 2019-06-30
 */
@Table(name = "user_info")
public class UserInfo extends EntityBean<Integer> implements Serializable {
    @Id
    @Column(name = "ID")
    private Integer id;

    /**
     * 用户IP地址
            
     */
    @Column(name = "USER_IP_ADDR")
    private String userIpAddr;

    /**
     * 用户名
            
     */
    @Column(name = "USER_CODE")
    private String userCode;

    /**
     * 用户昵称
            
     */
    @Column(name = "NICK_NAME")
    private String nickName;

    /**
     * 用户密码
            
     */
    @Column(name = "USER_PWD")
    private String userPwd;

    /**
     * 用户邮箱
            
     */
    @Column(name = "USER_MAIL")
    private String userMail;

    /**
     * 用户头像
            
     */
    @Column(name = "USER_HEAD")
    private String userHead;

    /**
     * 用户生日
            
     */
    @Column(name = "USER_BIRTH")
    private Date userBirth;

    /**
     * 用户手机号
            
     */
    @Column(name = "USER_PHONE")
    private Integer userPhone;

    /**
     * 用户等级
            
     */
    @Column(name = "USER_LEVEL")
    private Integer userLevel;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "MODIFY_DATE")
    private Date modifyDate;

    @Column(name = "DELETED_FLAG")
    private String deletedFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserIpAddr() {
        return userIpAddr;
    }

    public void setUserIpAddr(String userIpAddr) {
        this.userIpAddr = userIpAddr;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail == null ? null : userMail.trim();
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead == null ? null : userHead.trim();
    }

    public Date getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(Date userBirth) {
        this.userBirth = userBirth;
    }

    public Integer getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Integer userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
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

    @Override
    public String getDeletedFlag() {
        return deletedFlag;
    }

    @Override
    public void setDeletedFlag(String deletedFlag) {
        this.deletedFlag = deletedFlag;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userIpAddr='" + userIpAddr + '\'' +
                ", userCode='" + userCode + '\'' +
                ", nickName='" + nickName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userMail='" + userMail + '\'' +
                ", userHead='" + userHead + '\'' +
                ", userBirth=" + userBirth +
                ", userPhone=" + userPhone +
                ", userLevel=" + userLevel +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}