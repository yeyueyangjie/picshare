package com.yeyue.pictureshare.controller;

import com.yeyue.pictureshare.dto.PageDto;
import com.yeyue.pictureshare.dto.ResultDto;
import com.yeyue.pictureshare.model.CommentEntity;
import com.yeyue.pictureshare.service.CommentService;
import com.yeyue.pictureshare.util.MD5Util;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CommentController {
    @Resource(name = "commentServiceImpl")
    private CommentService commentService;

    /**
     * 获取列表
     *
     * @return
     */
    @CrossOrigin
    @GetMapping("comment/list")
    public ResultDto getCollectionList() {
        ResultDto result = new ResultDto();
        try {
            PageDto page = new PageDto();
            List<CommentEntity> list = commentService.getCommentList();
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
     * 添加收藏
     *
     * @param comment
     * @return
     */
    @CrossOrigin
    @PostMapping("/comment/add")
    public ResultDto addCollection(@RequestBody CommentEntity comment) {
        ResultDto result = new ResultDto();
        try {
            PageDto page = new PageDto();
            String commentId = MD5Util.getUUID32();
            comment.setCommentId(commentId);
            page.setCount(commentService.addComment(comment));
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
     * 删除收藏
     *
     * @param commentId
     * @return
     */
    @CrossOrigin
    @GetMapping("/comment/delete")
    public ResultDto deleteCollection(String commentId) {
        ResultDto result = new ResultDto();
        try {
            if (("").equals(commentId)) {
                result.setCode(301);
                result.setMessage("commentId is Empty");
            } else {
                PageDto page = new PageDto();
                page.setCount(commentService.deleteComment(commentId));

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
