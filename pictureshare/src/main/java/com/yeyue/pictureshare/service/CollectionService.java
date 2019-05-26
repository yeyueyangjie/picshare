package com.yeyue.pictureshare.service;

import com.yeyue.pictureshare.model.CollectionEntity;

import java.util.List;

public interface CollectionService {
    public List<CollectionEntity> getCollection(String picShareId,String userId);
    public Integer getCollectedNum(String picShareId);
    public Integer addCollection(CollectionEntity collection);
    public Integer changeCollection(Integer status,String collectionId);
}
