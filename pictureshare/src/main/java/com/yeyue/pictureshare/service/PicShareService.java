package com.yeyue.pictureshare.service;

import com.yeyue.pictureshare.dto.SearchDto;
import com.yeyue.pictureshare.model.CollectionEntity;
import com.yeyue.pictureshare.model.PicShareEntity;

import java.util.List;

public interface PicShareService {
    public List<PicShareEntity> getPicShareList(SearchDto search);
    public PicShareEntity getPicShare(String picShareId);
    public Integer addPicShare(PicShareEntity pic);
    public Integer updatePicShare(PicShareEntity pic);
    public Integer deleteShare(String picShareId);
    public List<PicShareEntity> getPicShareListByAuthor(String userId);
    public List<PicShareEntity> getPicShareListByCollected(String userId);
}
