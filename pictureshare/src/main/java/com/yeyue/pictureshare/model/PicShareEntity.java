package com.yeyue.pictureshare.model;

public class PicShareEntity {
    /**
     * id
     */
    private String picShareId;
    /**
     * pic 地址
     */
    private String target;
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String summary;
    /**
     * 作者
     */
    private String author;
    /**
     * 创建时间
     */
    private String created;
    /**
     * 地址
     */
    private String address;
    /**
     * 状态 默认1
     */
    private String status;
    /**
     * 删除标志 默认1  删除2
     */
    private String del;
    /**
     * 作者头像
     */
    private String authorportrait;
    /**
     * 和当前位置距离
     */
    private long distance;

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public String getAuthorportrait() {
        return authorportrait;
    }

    public void setAuthorportrait(String authorportrait) {
        this.authorportrait = authorportrait;
    }

    /**
     * 收藏是否 是为1
     */
    private Integer collect;

    private String collection;

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public Integer getCollect() {
        return collect;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
    }

    public String getPicShareId() {
        return picShareId;
    }

    public void setPicShareId(String picShareId) {
        this.picShareId = picShareId;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
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
