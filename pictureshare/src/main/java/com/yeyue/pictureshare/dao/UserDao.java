package com.yeyue.pictureshare.dao;

import com.yeyue.pictureshare.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    public List<UserEntity> getUserList();
    public UserEntity getUser(@Param("userId") String userId);
    public Integer addUser(@Param("user") UserEntity user);
    public Integer updateUser(@Param("user") UserEntity user);
    public Integer deleteUser(@Param("userId") String userId);
}
