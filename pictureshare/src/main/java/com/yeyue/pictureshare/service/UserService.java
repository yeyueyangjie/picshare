package com.yeyue.pictureshare.service;

import com.yeyue.pictureshare.model.UserEntity;

import java.util.List;

public interface UserService {
    public List<UserEntity> getUserList();
    public UserEntity getUser(String userId);
    public Integer addUser(UserEntity user);
    public Integer updateUser(UserEntity user);
    public Integer deleteUser(String userId);
}
