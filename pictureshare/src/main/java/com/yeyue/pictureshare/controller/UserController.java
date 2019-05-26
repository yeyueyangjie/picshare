package com.yeyue.pictureshare.controller;

import com.yeyue.pictureshare.config.WXConfiguration;
import com.yeyue.pictureshare.config.WxMappingJackson2HttpMessageConverter;
import com.yeyue.pictureshare.dto.PageDto;
import com.yeyue.pictureshare.dto.ResultDto;
import com.yeyue.pictureshare.dto.WXLoginDto;
import com.yeyue.pictureshare.model.UserEntity;
import com.yeyue.pictureshare.service.UserService;
import com.yeyue.pictureshare.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Resource(name = "userServiceImpl")
    private UserService userService;
    @Autowired
    private WXConfiguration wxconfig;

    /**
     * 小程序登录回调
     *
     * @param code
     * @return
     */
    @CrossOrigin
    @GetMapping("/user/wxlogin")
    public ResultDto WXLogin(String code) {
        ResultDto result = new ResultDto();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
        //请求的路径
        String url = wxconfig.getUrl() + "?appid=" + wxconfig.getAppid() + "&secret=" + wxconfig.getAppsecret() + "&js_code=" + code + "&grant_type=" + wxconfig.getGranttype();
        try {
            WXLoginDto login = restTemplate.getForObject(url, WXLoginDto.class);
            System.out.println(login.toString());
            if (!("").equals(login) && login.getErrcode() == 0) {
                result.setCode(200);
                PageDto page = new PageDto();
                List<WXLoginDto> wxlogin = new ArrayList<>();
                wxlogin.add(login);
                page.setList(wxlogin);
                page.setCount(1);
                result.setData(page);
            } else {
                result.setCode(login.getErrcode());
                result.setMessage(login.getErrmsg());
            }
        } catch (Exception e) {
            result.setCode(300);
            System.out.println(e.toString());
            result.setMessage(e.toString());
        } finally {
            return result;
        }


    }

    /**
     * 获取用户列表
     *
     * @return
     */
    @CrossOrigin
    @GetMapping("/user/list")
    public ResultDto getUserList() {
        ResultDto result = new ResultDto();
        try {
            PageDto page = new PageDto();
            List<UserEntity> list = userService.getUserList();
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
     * 获取用户详情
     *
     * @param userId
     * @return
     */
    @CrossOrigin
    @GetMapping("/user/detail")
    public ResultDto getUser(String userId) {
        ResultDto result = new ResultDto();
        try {
            PageDto page = new PageDto();
            if (("").equals(userId)) {
                result.setCode(301);
                result.setMessage("UserId is Empty");
            } else {
                UserEntity user = userService.getUser(userId);
                if (user != null) {
                    List<UserEntity> userlist = new ArrayList<>();
                    userlist.add(user);
                    page.setList(userlist);
                    page.setCount(1);
                    result.setCode(200);
                    result.setData(page);
                } else {
                    result.setCode(302);
                    result.setMessage("User is Empty");
                }
            }
        } catch (Exception e) {
            result.setCode(300);
            result.setMessage(e.toString());
        } finally {
            return result;
        }
    }

    @CrossOrigin
    @PostMapping("/user/add")
    public ResultDto addUser(@RequestBody UserEntity user) {
        ResultDto result = new ResultDto();
        try {
            PageDto page = new PageDto();
            String userId = user.getUserId();
            page.setCount(userService.addUser(user));
            UserEntity user2 = userService.getUser(userId);
            List<UserEntity> userlist = new ArrayList<>();
            userlist.add(user2);
            page.setList(userlist);
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
     * 更新用户
     *
     * @param user
     * @return
     */
    @CrossOrigin
    @PostMapping("/user/update")
    public ResultDto updateUser(@RequestBody UserEntity user) {
        ResultDto result = new ResultDto();
        try {
            PageDto page = new PageDto();

            page.setCount(userService.updateUser(user));

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
     * 删除用户
     *
     * @param userId
     * @return
     */
    @CrossOrigin
    @GetMapping("/user/delete")
    public ResultDto deleteUser(String userId) {
        ResultDto result = new ResultDto();
        try {
            if (("").equals(userId)) {
                result.setCode(301);
                result.setMessage("UserId is Empty");
            } else {
                PageDto page = new PageDto();
                page.setCount(userService.deleteUser(userId));

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
