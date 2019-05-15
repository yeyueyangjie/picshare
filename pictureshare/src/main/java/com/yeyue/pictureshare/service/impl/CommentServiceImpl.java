package com.yeyue.pictureshare.service.impl;

import com.yeyue.pictureshare.dao.CommentDao;
import com.yeyue.pictureshare.model.CommentEntity;
import com.yeyue.pictureshare.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service(value = "commentServiceImpl")
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDao comdao;
    @Override
    public List<CommentEntity> getCommentList() {
        return comdao.getCommentList();
    }

    @Override
    public Integer addComment(CommentEntity comment) {
        return comdao.addComment(comment);
    }

    @Override
    public Integer deleteComment(String commentId) {
        return comdao.deleteComment(commentId);
    }
}
