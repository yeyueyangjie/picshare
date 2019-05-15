package com.yeyue.pictureshare.service;

import com.yeyue.pictureshare.model.CommentEntity;

import java.util.List;

public interface CommentService {
    public List<CommentEntity> getCommentList();
    public Integer addComment(CommentEntity comment);
    public Integer deleteComment(String commentId);
}
