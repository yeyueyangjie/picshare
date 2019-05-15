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
     * 删除喜欢  1为喜欢 2为不喜欢
     */
    private String del;

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

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }
}
