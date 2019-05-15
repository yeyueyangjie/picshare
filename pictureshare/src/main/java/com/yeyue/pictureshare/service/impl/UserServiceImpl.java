package com.yeyue.pictureshare.service.impl;

import com.yeyue.pictureshare.dao.UserDao;
import com.yeyue.pictureshare.model.UserEntity;
import com.yeyue.pictureshare.service.UserService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "userServiceImpl")
public class UserServiceImpl implements UserService {
   @Resource
    private UserDao userDao;

    @Override
    public List<UserEntity> getUserList() {
        return userDao.getUserList();
    }

    @Override
    public UserEntity getUser(String userId) {
        return userDao.getUser(userId);
    }

    @Override
    public Integer addUser(UserEntity user) {
        return userDao.addUser(user);
    }

    @Override
    public Integer updateUser(UserEntity user) {
        return userDao.updateUser(user);
    }

    @Override
    public Integer deleteUser(String userId){
        return userDao.deleteUser(userId);
    }
}
