package com.yijie.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yijie.Contents.Contents;
import com.yijie.model.UsersEntity;
import com.yijie.repository.UserRepository;
import com.yijie.vo.ResponseVO;
import org.hibernate.Hibernate;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于处理用户相关数据请求的控制器
 */
@Transactional
@Controller
@RequestMapping("UserController")
public class UserController {

    @Autowired
    UserRepository userRepository;

    /**
     * http://localhost:8080/UserController/login/{uId}/{uPassword}
     * 用户登录
     * @param uId 用户id
     * @param uPassword 密码
     * @return 响应值对象
     */
    @RequestMapping(value = "/login/{uId}/{uPassword}", method = RequestMethod.GET)
    @ResponseBody
    public Object login(@PathVariable Integer uId, @PathVariable String uPassword) {

        UsersEntity usersEntity = userRepository.findOne(uId);
        if (usersEntity != null && usersEntity.getuPassword().equals(uPassword)) { //验证通过，返回实体
            Hibernate.initialize(usersEntity.getOrdersesByUId());
            return new ResponseVO(Contents.SUCCESS, usersEntity);
        }
        return new ResponseVO(Contents.ERROR, null);//验证不通过，放回失败
    }

    /**
     * http://localhost:8080/UserController/add
     * 增加用户
     * @param usersEntity
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    // 此处@ModelAttribute 可以吧POST请求所携带的json字符串自动解析成后面所写的实体类
    // 然后就直接可以通过这个UserEntity取到Android端传过来的数据
    public Object register(@RequestBody UsersEntity usersEntity) {
        userRepository.saveAndFlush(usersEntity);
        UsersEntity user = userRepository.findByUName(usersEntity.getuName());
        if(user != null) {
            Hibernate.initialize(user.getOrdersesByUId());
            return new ResponseVO(Contents.SUCCESS, user);
        }
        return new ResponseVO(Contents.ERROR, null);
    }

    /**
     * http://localhost:8080/UserController/update
     * 修改用户信息
     * @param
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public Object update(@RequestBody UsersEntity usersEntity) {
        userRepository.updateUser(usersEntity.getuPassword(), usersEntity.getuName(), usersEntity.getuIdcard(),
                usersEntity.getuPhone(), usersEntity.getuAddress(), usersEntity.getuId());
        userRepository.flush();
        UsersEntity entity = userRepository.findOne(usersEntity.getuId());
        Hibernate.initialize(entity.getOrdersesByUId());
        return new ResponseVO(Contents.SUCCESS, entity);
    }

    /**
     * http://localhost:8080/UserController/delete/{uId}
     * 删除用户
     * @param uId 需要删除的用户的id
     * @return
     */
    @RequestMapping(value = "/delete/{uId}", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable Integer uId) {
        if(!userRepository.exists(uId)) return new ResponseVO(Contents.ERROR, null);
        userRepository.delete(uId);
        userRepository.flush();
        return new ResponseVO(Contents.SUCCESS, null);
    }

    /**
     * 获取用户列表
     * @return
     */
    @RequestMapping(value = "/users")
    @ResponseBody
    public Object getUsers() {
        List<UsersEntity> usersEntityList = userRepository.findAll();
        if(!usersEntityList.isEmpty()) {
            for(UsersEntity usersEntity : usersEntityList) {
                Hibernate.initialize(usersEntity.getOrdersesByUId());
            }
            return new ResponseVO(Contents.SUCCESS, usersEntityList);
        }
        return new ResponseVO(Contents.ERROR, null);
    }

    /**
     * 获取用户
     * @return
     */
    @RequestMapping(value = "/user/{userId}")
    @ResponseBody
    public Object getUser(@PathVariable("userId") Integer userId) {
        UsersEntity usersEntity = userRepository.findOne(userId);
        if(usersEntity == null) {
            return new ResponseVO(Contents.ERROR, null);
        }
        Hibernate.initialize(usersEntity.getOrdersesByUId());
        return new ResponseVO(Contents.SUCCESS, usersEntity);
    }


}
