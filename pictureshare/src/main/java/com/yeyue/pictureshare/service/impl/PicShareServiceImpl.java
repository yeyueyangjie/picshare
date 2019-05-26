package com.yeyue.pictureshare.service.impl;

import com.yeyue.pictureshare.dao.PicShareDao;
import com.yeyue.pictureshare.dto.SearchDto;
import com.yeyue.pictureshare.model.CollectionEntity;
import com.yeyue.pictureshare.model.PicShareEntity;
import com.yeyue.pictureshare.service.PicShareService;
import com.yeyue.pictureshare.util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service(value = "picShareServiceImpl")
public class PicShareServiceImpl implements PicShareService {
    @Resource
    private PicShareDao picDao;
    @Override
    public List<PicShareEntity> getPicShareList(SearchDto search) {
        List<PicShareEntity> list = picDao.getPicShareList();
        List<PicShareEntity> list2 = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                // 加入距离
                PicShareEntity item = (PicShareEntity) list.get(i);
                long distance = MapUtil.getDistanceByAddress(item.getAddress(), search.getAddress());
                item.setDistance(distance);
                if(distance <= 3000) {
                    list2.add(item);
                }
            }
        }
        return list2;
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
    public List<PicShareEntity> getPicShareListByAuthor(String userId) {
        return picDao.getPicShareListByAuthor(userId);
    }

    @Override
    public List<PicShareEntity> getPicShareListByCollected(String userId) {
        return picDao.getPicShareListByCollected(userId);
    }
}
