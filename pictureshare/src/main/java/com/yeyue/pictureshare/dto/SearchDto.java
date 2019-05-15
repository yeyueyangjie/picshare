package com.yeyue.pictureshare.dto;

/**
 *  搜索条件  数据流
 */
public class SearchDto {
    private String userId;
    private String picShareId;
    private String collectionId;
    private String search;
    private double locationx;
    private double locationy;
    private Integer created;
    private Integer limit;
    private Integer start;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPicShareId() {
        return picShareId;
    }

    public void setPicShareId(String picShareId) {
        this.picShareId = picShareId;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public double getLocationx() {
        return locationx;
    }

    public void setLocationx(double locationx) {
        this.locationx = locationx;
    }

    public double getLocationy() {
        return locationy;
    }

    public void setLocationy(double locationy) {
        this.locationy = locationy;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }
}
