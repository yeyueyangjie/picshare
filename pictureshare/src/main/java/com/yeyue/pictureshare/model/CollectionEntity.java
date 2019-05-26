package com.yeyue.pictureshare.model;

public class CollectionEntity {
    /**
     * 喜欢id
     */
    private String collectionId;
    /**
     * pic
     */
    private String picShare;
    /**
     * 收藏者
     */
    private String collected;
    /**
     * 喜欢时间
     */
    private String created;
    /**
     * 是否收藏  1为未收藏 2为收藏
     */
    private String status;

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getPicShare() {
        return picShare;
    }

    public void setPicShare(String picShare) {
        this.picShare = picShare;
    }

    public String getCollected() {
        return collected;
    }

    public void setCollected(String collected) {
        this.collected = collected;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
