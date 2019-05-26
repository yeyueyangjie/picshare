package com.yeyue.pictureshare.dao;

import com.yeyue.pictureshare.model.CollectionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CollectionDao {
    public List<CollectionEntity> getCollection(@Param("picShareId") String picShareId,@Param("userId") String userId);
    public Integer getCollectedNum(@Param("picShareId") String picShareId);
    public Integer addCollection(@Param("collection") CollectionEntity collection);
    public Integer changeCollection(@Param("status") Integer status,@Param("collectionId") String collectionId);
}
