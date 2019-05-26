package com.yeyue.pictureshare.dao;

import com.yeyue.pictureshare.model.CollectionEntity;
import com.yeyue.pictureshare.model.PicShareEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PicShareDao {

    public List<PicShareEntity> getPicShareList();
    public PicShareEntity getPicShare(@Param("picShareId") String picShareId);
    public Integer addPicShare(@Param("pic") PicShareEntity pic);
    public Integer updatePicShare(@Param("pic") PicShareEntity pic);
    public Integer deletePicShare(@Param("picShareId") String picShareId);
    public List<PicShareEntity> getPicShareListByAuthor(@Param("userId") String userId);
    public List<PicShareEntity> getPicShareListByCollected(@Param("userId") String userId);
}
