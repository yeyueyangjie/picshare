package com.yeyue.pictureshare.controller;

import com.qiniu.util.Auth;
import com.yeyue.pictureshare.config.QiniuConfiguration;
import com.yeyue.pictureshare.dto.PageDto;
import com.yeyue.pictureshare.dto.ResultDto;
import com.yeyue.pictureshare.dto.SearchDto;
import com.yeyue.pictureshare.dto.UpTokenDto;
import com.yeyue.pictureshare.model.CollectionEntity;
import com.yeyue.pictureshare.model.PicShareEntity;
import com.yeyue.pictureshare.service.PicShareService;
import com.yeyue.pictureshare.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class PicShareController {

    @Resource(name = "picShareServiceImpl")
    private PicShareService picService;
    @Autowired
    private QiniuConfiguration qiniuConfig;

    /**
     * 返回客户端图片上传所需token
     *
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/pic/uploadtoken", method = RequestMethod.GET)
    private UpTokenDto getPicToken() {
        UpTokenDto upTokenDto = new UpTokenDto();
        try {
            String accesskey = qiniuConfig.getAccesskey();
            String secretkey = qiniuConfig.getSecretkey();
            String bucket = qiniuConfig.getBucket();

            Auth auth = Auth.create(accesskey, secretkey);
            String uptoken = auth.uploadToken(bucket);
            System.out.println(uptoken);
            upTokenDto.setUptoken(uptoken);
            return upTokenDto;
        } catch (Exception e) {
            return upTokenDto;
        }
    }

    /**
     * 获取列表
     *
     * @return
     */
    @CrossOrigin
    @GetMapping("/pic/listbyauthor")
    public ResultDto getPicShareListByAuthor(String userId) {
        ResultDto result = new ResultDto();
        try {
            PageDto page = new PageDto();
            List<PicShareEntity> list = picService.getPicShareListByAuthor(userId);
            page.setCount(list.size());
            page.setList(list);
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
     * 获取列表
     *
     * @return
     */
    @CrossOrigin
    @PostMapping("/pic/list")
    public ResultDto getPicShareList(@RequestBody SearchDto search) {
        ResultDto result = new ResultDto();
        try {
            PageDto page = new PageDto();
            List<PicShareEntity> list = picService.getPicShareList(search);
            page.setCount(list.size());
            page.setList(list);
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
     * 获取pic 详情
     *
     * @param picShareId
     * @return
     */
    @CrossOrigin
    @GetMapping("/pic/detail")
    public ResultDto getPicShare(String picShareId) {
        ResultDto result = new ResultDto();
        try {
            PageDto page = new PageDto();
            if (("").equals(picShareId)) {
                result.setCode(301);
                result.setMessage("picShareId is Empty");
            } else {
                PicShareEntity pic = picService.getPicShare(picShareId);
                if (pic != null) {
                    List<PicShareEntity> piclist = new ArrayList<>();
                    piclist.add(pic);
                    page.setList(piclist);
                    page.setCount(1);
                    result.setCode(200);
                    result.setData(page);
                } else {
                    result.setCode(302);
                    result.setMessage("PicShare is Empty");
                }
            }
        } catch (Exception e) {
            result.setCode(300);
            result.setMessage(e.toString());
        } finally {
            return result;
        }
    }

    /**
     * 增加pic
     *
     * @param pic
     * @return
     */
    @CrossOrigin
    @PostMapping("/pic/add")
    public ResultDto addPicShare(@RequestBody PicShareEntity pic) {
        ResultDto result = new ResultDto();
        try {
            PageDto page = new PageDto();
            String picShareId = MD5Util.getUUID32();
            pic.setPicShareId(picShareId);
            page.setCount(picService.addPicShare(pic));
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
     * 更新 pic
     *
     * @param pic
     * @return
     */
    @CrossOrigin
    @PostMapping("/pic/update")
    public ResultDto updateUser(@RequestBody PicShareEntity pic) {
        ResultDto result = new ResultDto();
        try {
            PageDto page = new PageDto();

            page.setCount(picService.updatePicShare(pic));

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
     * 删除 pic
     *
     * @param picShareId
     * @return
     */
    @CrossOrigin
    @GetMapping("/pic/delete")
    public ResultDto deleteUser(String picShareId) {
        ResultDto result = new ResultDto();
        try {
            if (("").equals(picShareId)) {
                result.setCode(301);
                result.setMessage("picShareId is Empty");
            } else {
                PageDto page = new PageDto();
                page.setCount(picService.deleteShare(picShareId));

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
     * 获取用户的点赞列表
     *
     * @param userId
     * @return
     */
    @CrossOrigin
    @GetMapping("/pic/collectlist")
    public ResultDto getPicCollectedList(String userId) {
        ResultDto result = new ResultDto();
        try {
            if (("").equals(userId)) {
                result.setCode(301);
                result.setMessage("userId is Empty");
            } else {
                PageDto page = new PageDto();
                List<PicShareEntity> list = picService.getPicShareListByCollected(userId);
                page.setList(list);
                page.setCount(list.size());

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
