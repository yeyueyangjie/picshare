package com.yeyue.pictureshare.service;

import com.yeyue.pictureshare.model.CollectionEntity;

import java.util.List;

public interface CollectionService {
    public CollectionEntity getCollection(String collectionId);
    public Integer addCollection(CollectionEntity collection);
    public Integer updateCollection(CollectionEntity collection);
}
