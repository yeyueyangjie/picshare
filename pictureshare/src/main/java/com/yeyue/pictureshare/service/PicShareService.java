package com.yeyue.pictureshare.service;

import com.yeyue.pictureshare.model.CollectionEntity;
import com.yeyue.pictureshare.model.PicShareEntity;

import java.util.List;

public interface PicShareService {
    public List<PicShareEntity> getPicShareList();
    public PicShareEntity getPicShare(String picShareId);
    public Integer addPicShare(PicShareEntity pic);
    public Integer updatePicShare(PicShareEntity pic);
    public Integer deleteShare(String picShareId);
    public Integer getCollectCount(String picShareId);
    public List<CollectionEntity> getCollectedCount(String userId,String picShareId);
    public List<CollectionEntity> getCollectedList(String userId);
    public List<PicShareEntity> getPicShareListByAuthor(String userId);
}
