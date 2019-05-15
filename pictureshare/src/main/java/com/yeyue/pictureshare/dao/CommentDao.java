package com.yeyue.pictureshare.dao;

import com.yeyue.pictureshare.model.CommentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CommentDao {
    public List<CommentEntity> getCommentList();
    public Integer addComment(@Param("comment") CommentEntity comment);
    public Integer deleteComment(@Param("commentId") String commentId);
}
