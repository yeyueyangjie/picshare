package com.yeyue.pictureshare.controller;

import com.yeyue.pictureshare.dto.PageDto;
import com.yeyue.pictureshare.dto.ResultDto;
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
     * 添加收藏
     * @param collection
     * @return
     */
    @CrossOrigin
    @PostMapping("/collection/add")
    public ResultDto addCollection(@RequestBody CollectionEntity collection){
        ResultDto result = new ResultDto();
        try {
            PageDto page = new PageDto();
            String collectionId = MD5Util.getUUID32();
            collection.setCollectionId(collectionId);
            page.setCount(collectionService.addCollection(collection));
            List<CollectionEntity> list = new ArrayList<>();
            CollectionEntity collectionEntity = collectionService.getCollection(collectionId);
            list.add(collectionEntity);
            page.setList(list);
            result.setCode(200);
            result.setData(page);
        }catch (Exception e){
            result.setCode(300);
            result.setMessage(e.toString());
        }finally {
            return result;
        }
    }

    /**
     * 该表收藏状态
     * @param collection
     * @return
     */
    @CrossOrigin
    @PostMapping("/collection/update")
    public ResultDto updateCollection(@RequestBody CollectionEntity collection){
        ResultDto result = new ResultDto();
        try {
            if(("").equals(collection.getCollectionId())){
                result.setCode(301);
                result.setMessage("collectionId is Empty");
            }else {
                PageDto page = new PageDto();
                page.setCount(collectionService.updateCollection(collection));

                result.setCode(200);
                result.setData(page);
            }
        }catch (Exception e){
            result.setCode(300);
            result.setMessage(e.toString());
        }finally {
            return result;
        }

    }
}
