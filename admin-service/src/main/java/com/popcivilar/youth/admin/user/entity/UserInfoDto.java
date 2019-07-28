package com.popcivilar.youth.general.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 * 
 *
 * @date 2019-06-30
 */
public class UserInfoDto implements Serializable {
    private Integer id;

    /**
     * 用户IP地址
            
     */
    private String userIpAddr;

    /**
     * 用户名
            
     */
    private String userCode;

    /**
     * 用户昵称
            
     */
    private String nickName;

    /**
     * 用户密码
            
     */
    private String userPwd;

    /**
     * 用户邮箱
            
     */
    private String userMail;

    /**
     * 用户头像
            
     */
    private String userHead;

    /**
     * 用户生日
            
     */
    private Date userBirth;

    /**
     * 用户手机号
            
     */
    private Integer userPhone;

    /**
     * 用户等级
            
     */
    private Integer userLevel;

    private Date createDate;

    private Date modifyDate;

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