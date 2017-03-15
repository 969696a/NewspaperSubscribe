package com.yijie.controller;

import com.yijie.Contents.Contents;
import com.yijie.model.AdminuserEntity;
import com.yijie.repository.AdminuserRepository;
import com.yijie.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Created by barbara on 2016/12/5.
 */
@Transactional
@Controller
@RequestMapping("/AdminuserController")
public class AdminuserController {

    @Autowired
    AdminuserRepository adminuserRepository;

    /**
     * 管理员登录
     * @param aName 管理员名
     * @param aPassword 密码
     * @return
     */
    @RequestMapping(value = "/login/{aName}/{aPassword}")
    @ResponseBody
    public Object login(@PathVariable String aName, @PathVariable String aPassword) {

        AdminuserEntity adminuserEntity = adminuserRepository.findOne(aName);
        if(adminuserEntity != null && adminuserEntity.getaPassword().equals(aPassword)) {
            return new ResponseVO(Contents.SUCCESS, adminuserEntity);
        }
        return new ResponseVO(Contents.ERROR, null);
    }

    /**
     * 管理员注册
     * @param adminuserEntity
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Object register(@RequestBody AdminuserEntity adminuserEntity) {
        adminuserRepository.saveAndFlush(adminuserEntity);
        return new ResponseVO(Contents.SUCCESS, adminuserEntity);
    }




}
