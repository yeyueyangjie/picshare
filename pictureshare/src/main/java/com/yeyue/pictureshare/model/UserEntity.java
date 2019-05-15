package com.yeyue.pictureshare.model;

public class UserEntity {
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 手机号
     */
    private String phoneNum;
    /**
     * 性别
     */
    private String sex;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 头像
     */
    private String portrait;
    /**
     * 个签
     */
    private String summary;
    /**
     * 地址
     */
    private String address;
    /**
     * 用户状态 默认1
     */
    private String status;
    /**
     * 删除标志 默认1 删除2
     */
    private String del;
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }
}
