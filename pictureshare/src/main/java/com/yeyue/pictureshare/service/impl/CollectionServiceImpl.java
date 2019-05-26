package com.yeyue.pictureshare.service.impl;

import com.yeyue.pictureshare.dao.CollectionDao;
import com.yeyue.pictureshare.model.CollectionEntity;
import com.yeyue.pictureshare.service.CollectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "collectionServiceImpl")
public class CollectionServiceImpl implements CollectionService {
    @Resource
    private CollectionDao cetdao;

    @Override
    public List<CollectionEntity> getCollection(String picShareId, String userId) {
        return cetdao.getCollection(picShareId, userId);
    }

    @Override
    public Integer getCollectedNum(String picShareId) {
        return cetdao.getCollectedNum(picShareId);
    }

    @Override
    public Integer addCollection(CollectionEntity collection) {
        return cetdao.addCollection(collection);
    }

    @Override
    public Integer changeCollection(Integer status, String collectionId) {
        return cetdao.changeCollection(status, collectionId);
    }
}
