package com.yeyue.pictureshare.controller;

import com.yeyue.pictureshare.dto.PageDto;
import com.yeyue.pictureshare.dto.ResultDto;
import com.yeyue.pictureshare.dto.SearchDto;
import com.yeyue.pictureshare.model.CollectionEntity;
import com.yeyue.pictureshare.service.CollectionService;
import com.yeyue.pictureshare.util.MD5Util;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CollectionController {
    @Resource(name = "collectionServiceImpl")
    private CollectionService collectionService;

    /**
     * 添加用户和图片分享之间的联系信息
     *
     * @param collection
     * @return
     */
    @CrossOrigin
    @PostMapping("/collection/add")
    public ResultDto addCollection(@RequestBody CollectionEntity collection) {
        ResultDto result = new ResultDto();
        try {
            PageDto page = new PageDto();
            String collectionId = MD5Util.getUUID32();
            collection.setCollectionId(collectionId);
            page.setCount(collectionService.addCollection(collection));
            page.setList(collectionService.getCollection(collection.getPicShare(), collection.getCollected()));
            result.setCode(200);
            result.setData(page);
        } catch (Exception e) {
            result.setCode(300);
            result.setMessage(e.toString());
        } finally {
            return result;
        }
    }

    /**
     * 改变收藏状态
     *
     * @param search
     * @return
     */
    @CrossOrigin
    @PostMapping("/collection/change")
    public ResultDto updateCollection(@RequestBody SearchDto search) {
        ResultDto result = new ResultDto();
        try {
            if (("").equals(search.getCollectionId())) {
                result.setCode(301);
                result.setMessage("collectionId is Empty");
            } else {
                PageDto page = new PageDto();
                page.setCount(collectionService.changeCollection(search.getStatus(), search.getCollectionId()));

                result.setCode(200);
                result.setData(page);
            }
        } catch (Exception e) {
            result.setCode(300);
            result.setMessage(e.toString());
        } finally {
            return result;
        }

    }

    /**
     * 获取单个图片的收藏数量 count
     * 获取用户对当前图片的收藏信息 list
     *
     * @param search
     * @return
     */
    @CrossOrigin
    @PostMapping("/collection/count")
    public ResultDto getCollectedNum(@RequestBody SearchDto search) {
        ResultDto result = new ResultDto();
        try {
            if (("").equals(search.getPicShareId())) {
                result.setCode(301);
                result.setMessage("picShareId is Empty");
            } else {
                PageDto page = new PageDto();
                page.setCount(collectionService.getCollectedNum(search.getPicShareId()));
                page.setList(collectionService.getCollection(search.getPicShareId(), search.getUserId() ));
                result.setCode(200);
                result.setData(page);
            }
        } catch (Exception e) {
            result.setCode(300);
            result.setMessage(e.toString());
        } finally {
            return result;
        }
    }
}
