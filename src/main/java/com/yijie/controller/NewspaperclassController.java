package com.yijie.controller;

import com.yijie.Contents.Contents;
import com.yijie.model.NewspaperEntity;
import com.yijie.model.NewspaperclassEntity;
import com.yijie.repository.NewspaperclassRepository;
import com.yijie.vo.ResponseVO;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 处理新闻类别的控制器
 */
@Transactional
@Controller
@RequestMapping("/NewspaperclassController")
public class NewspaperclassController {

    @Autowired
    NewspaperclassRepository newspaperclassRepository;


    /**
     * 获取新闻所有类别
     * @return
     */
    @RequestMapping(value = "/classes")
    @ResponseBody
    public Object getList() {
        List<NewspaperclassEntity> list = newspaperclassRepository.findAll();
        if(!list.isEmpty()) {
            for(NewspaperclassEntity e : list) {
                Hibernate.initialize(e.getNewspapersByCId());
                List<NewspaperEntity> list1 = (List) e.getNewspapersByCId();
                for(NewspaperEntity n : list1) {
                    Hibernate.initialize(n.getOrdersesByNId());
                }
            }
            return new ResponseVO(Contents.SUCCESS, list);
        }
        return new ResponseVO(Contents.ERROR, null);
    }

    /**
     * 通过新闻类别id获取新闻类别
     * @param cId
     * @return
     */
    @RequestMapping(value = "/class/{cId}")
    @ResponseBody
    public Object getClass(@PathVariable int cId) {
        if(!newspaperclassRepository.exists(cId)) return new ResponseVO(Contents.ERROR, null);

        NewspaperclassEntity newspaperclassEntity = newspaperclassRepository.findOne(cId);
        Hibernate.initialize(newspaperclassEntity.getNewspapersByCId());
        return new ResponseVO(Contents.ERROR, newspaperclassEntity);
    }
}
