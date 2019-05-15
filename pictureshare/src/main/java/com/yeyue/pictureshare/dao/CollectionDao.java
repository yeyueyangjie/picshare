package com.yeyue.pictureshare.dao;

import com.yeyue.pictureshare.model.CollectionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CollectionDao {
    public CollectionEntity getCollection(@Param("collectionId") String collectionId);
    public Integer addCollection(@Param("collection") CollectionEntity collection);
    public Integer updateCollection(@Param("collection") CollectionEntity collection);
}
