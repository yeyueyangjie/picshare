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
    public CollectionEntity getCollection(String collectionId) {
        return cetdao.getCollection(collectionId);
    }

    @Override
    public Integer addCollection(CollectionEntity collection) {
        return cetdao.addCollection(collection);
    }

    @Override
    public Integer updateCollection(CollectionEntity collection) {
        return cetdao.updateCollection(collection);
    }


}
