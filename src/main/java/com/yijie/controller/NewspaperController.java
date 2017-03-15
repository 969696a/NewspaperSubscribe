package com.yijie.controller;

import com.yijie.Contents.Contents;
import com.yijie.model.NewspaperEntity;
import com.yijie.repository.NewspaperRepository;
import com.yijie.repository.NewspaperclassRepository;
import com.yijie.vo.ResponseVO;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by barbara on 2016/12/23.
 */
@Controller
@Transactional
@RequestMapping(value = "/NewspaperController")
public class NewspaperController {

    @Autowired
    NewspaperRepository newspaperRepository;

    /**
     * 获取报刊信息列表
     * @return
     */
    @RequestMapping(value = "/newspapers")
    @ResponseBody
    public Object getNewspapers() {
        List<NewspaperEntity> newspaperEntityList = newspaperRepository.findAll();

        if(!newspaperEntityList.isEmpty()) {
            for(NewspaperEntity newspaperEntity : newspaperEntityList) {
                Hibernate.initialize(newspaperEntity.getOrdersesByNId());
            }
            return new ResponseVO(Contents.SUCCESS, newspaperEntityList);
        }
        return new ResponseVO(Contents.ERROR, null);
    }

    /**
     * 添加报刊信息
     * @param newspaperEntity
     * @return
     */
    @RequestMapping(value = "/addNewspaper", method = RequestMethod.POST)
    @ResponseBody
    public Object addNewspaper(@RequestBody NewspaperEntity newspaperEntity) {
        newspaperRepository.saveAndFlush(newspaperEntity);
        return new ResponseVO(Contents.SUCCESS, null);
    }

    /**
     * 通过id获取报刊信息
     * @return
     */
    @RequestMapping(value = "/newspaper/{nId}")
    @ResponseBody
    public Object getNewspaper(@PathVariable("nId") Integer nId) {
        NewspaperEntity newspaperEntity = newspaperRepository.findOne(nId);

        if(newspaperEntity == null) {
            return new ResponseVO(Contents.ERROR, null);
        }
        Hibernate.initialize(newspaperEntity.getOrdersesByNId());
        return new ResponseVO(Contents.SUCCESS, newspaperEntity);
    }

}
