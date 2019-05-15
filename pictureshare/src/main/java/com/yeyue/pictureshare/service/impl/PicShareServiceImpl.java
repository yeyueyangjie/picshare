package com.yeyue.pictureshare.service.impl;

import com.yeyue.pictureshare.dao.PicShareDao;
import com.yeyue.pictureshare.model.CollectionEntity;
import com.yeyue.pictureshare.model.PicShareEntity;
import com.yeyue.pictureshare.service.PicShareService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service(value = "picShareServiceImpl")
public class PicShareServiceImpl implements PicShareService {
    @Resource
    private PicShareDao picDao;
    @Override
    public List<PicShareEntity> getPicShareList() {
        return picDao.getPicShareList();
    }

    @Override
    public PicShareEntity getPicShare(String picShareId) {
        return picDao.getPicShare(picShareId);
    }

    @Override
    public Integer addPicShare(PicShareEntity pic) {
        return picDao.addPicShare(pic);
    }

    @Override
    public Integer updatePicShare(PicShareEntity pic) {
        return picDao.updatePicShare(pic);
    }

    @Override
    public Integer deleteShare(String picShareId) {
        return picDao.deletePicShare(picShareId);
    }

    @Override
    public Integer getCollectCount(String picShareId) {
        return picDao.getCollectCount(picShareId);
    }

    @Override
    public List<CollectionEntity> getCollectedCount(String userId,String picShareId) {
        return picDao.getCollectedCount(userId,picShareId);
    }

    @Override
    public List<CollectionEntity> getCollectedList(String userId) {
        return picDao.getCollectedList(userId);
    }

    @Override
    public List<PicShareEntity> getPicShareListByAuthor(String userId) {
        return picDao.getPicShareListByAuthor(userId);
    }
}
