package com.yijie.controller;

import com.yijie.Contents.Contents;
import com.yijie.model.DepartmentEntity;
import com.yijie.model.UsersEntity;
import com.yijie.repository.DepartmentRepository;
import com.yijie.vo.ResponseVO;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by barbara on 2016/12/14.
 */
@Controller
@Transactional
@RequestMapping("/DepartmentController")
public class DepartmentController {

    @Autowired
    DepartmentRepository departmentRepository;

    /**
     * 获取部门所有类别列表
     * http://localhost:8080/DepartmentController/getList_get.json
     * @return
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public Object getList() {
        List<DepartmentEntity> list = departmentRepository.findAll();
        if(!list.isEmpty()) {
            for(DepartmentEntity e : list) {
                Hibernate.initialize(e.getUsersesByDId());
                List<UsersEntity> list1 = (List) e.getUsersesByDId();
                for (UsersEntity u : list1) {
                    Hibernate.initialize(u.getOrdersesByUId());
                }
            }
            return new ResponseVO(Contents.SUCCESS, list);
        }
        return new ResponseVO(Contents.ERROR, null);
    }

    /**
     * 增加部门
     * @param departmentEntity
     * @return
     */
    @RequestMapping(value = "/addDepartment", method = RequestMethod.POST)
    @ResponseBody
    public Object addDepartment(@RequestBody DepartmentEntity departmentEntity) {
        departmentRepository.saveAndFlush(departmentEntity);
        return new ResponseVO(Contents.SUCCESS, null);
    }

    /**
     * 删除部门
     * @param dId
     * @return
     */
    @RequestMapping(value = "/deleteDepartment/{dId}")
    @ResponseBody
    public Object deleteDepartment(@PathVariable Integer dId) {
        if(!departmentRepository.exists(dId)) return new ResponseVO(Contents.ERROR, null);

        departmentRepository.delete(dId);
        departmentRepository.flush();
        return new ResponseVO(Contents.SUCCESS, null);
    }

    /**
     * 通过部门id获取部门
     * @param dId
     * @return
     */
    @RequestMapping(value = "/department/{dId}")
    @ResponseBody
    public Object getDepartment(@PathVariable Integer dId) {
        if(!departmentRepository.exists(dId)) return new ResponseVO(Contents.ERROR, null);

        DepartmentEntity departmentEntity = departmentRepository.findOne(dId);
        Hibernate.initialize(departmentEntity.getUsersesByDId());
        List<UsersEntity> usersEntityList = (List)departmentEntity.getUsersesByDId();
        for(UsersEntity usersEntity : usersEntityList) {
            Hibernate.initialize(usersEntity.getOrdersesByUId());
        }
        return new ResponseVO(Contents.SUCCESS, departmentEntity);
    }
}
