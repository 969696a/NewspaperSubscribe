package com.yijie.controller;

import com.yijie.Contents.Contents;
import com.yijie.model.OrdersEntity;
import com.yijie.repository.OrdersRepository;
import com.yijie.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by barbara on 2016/12/23.
 */
@Controller
@Transactional
@RequestMapping("/OrdersController")
public class OrdersController {

    @Autowired
    OrdersRepository ordersRepository;

    /**
     * 创建订单
     * @param ordersEntity
     * @return
     */
    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    @ResponseBody
    public Object addOrder(@RequestBody OrdersEntity ordersEntity) {
        ordersRepository.saveAndFlush(ordersEntity);
        return new ResponseVO(Contents.SUCCESS, null);
    }

    /**
     * 通过用户id获取订单列表
     * @param uId
     * @return
     */
    @RequestMapping(value = "/ordersByUser/{uId}")
    @ResponseBody
    public Object getOrdersByUser(@PathVariable Integer uId) {
        List<OrdersEntity> ordersEntityList = ordersRepository.findByUId(uId);
        if(!ordersEntityList.isEmpty()) return new ResponseVO(Contents.SUCCESS, ordersEntityList);

        return new ResponseVO(Contents.ERROR, null);
    }

    /**
     * 通过报刊id获取订单列表
     * @param nId
     * @return
     */
    @RequestMapping(value = "/ordersByNewspaper/{nId}")
    @ResponseBody
    public Object getOrdersByNewspaper(@PathVariable Integer nId) {
        List<OrdersEntity> ordersEntityList = ordersRepository.findByNId(nId);
        if(!ordersEntityList.isEmpty()) return new ResponseVO(Contents.SUCCESS, ordersEntityList);

        return new ResponseVO(Contents.ERROR, null);
    }

    /**
     * 通过用户的部门号获取相关订单
     * @param dId
     * @return
     */
    @RequestMapping(value = "/ordersByUserDId/{dId}")
    @ResponseBody
    public Object getOrdersByUserDId(@PathVariable Integer dId) {
        List<OrdersEntity> ordersEntityList = ordersRepository.findByUserDId(dId);
        if(!ordersEntityList.isEmpty()) return new ResponseVO(Contents.SUCCESS, ordersEntityList);

        return new ResponseVO(Contents.ERROR, null);
    }

    /**
     * 通过用户id获取份数总数和月数总数
     * @param uId
     * @return
     */
    @RequestMapping(value = "/copiesAndMonthSumByUId/{uId}")
    @ResponseBody
    public Object getSumByUser(@PathVariable Integer uId) {
        Integer copies = ordersRepository.getCopiesSumByUId(uId);
        Integer months = ordersRepository.getMonthSumByUId(uId);
        if(copies != null && months!=null) {
            Map<String, Integer> map = new HashMap<>();
            map.put("copies", copies);
            map.put("months", months);
            return new ResponseVO(Contents.SUCCESS, map);
        }
        return new ResponseVO(Contents.ERROR, null);
    }

    @RequestMapping(value = "/copiesAndMonthSumByNId/{nId}")
    @ResponseBody
    public Object getSumByNews(@PathVariable Integer nId) {
        Integer copies = ordersRepository.getCopiesSumByNId(nId);
        Integer months = ordersRepository.getMonthSumByNId(nId);
        if(copies != null && months!=null) {
            Map<String, Integer> map = new HashMap<>();
            map.put("copies", copies);
            map.put("months", months);
            return new ResponseVO(Contents.SUCCESS, map);
        }
        return new ResponseVO(Contents.ERROR, null);
    }

    @RequestMapping(value = "/copiesAndMonthSumByDId/{dId}")
    @ResponseBody
    public Object getSumByUserDId(@PathVariable Integer dId) {
        Integer copies = ordersRepository.getCopiesSumByUserDId(dId);
        Integer months = ordersRepository.getMonthsSumByUserDId(dId);
        if(copies != null && months!=null) {
            Map<String, Integer> map = new HashMap<>();
            map.put("copies", copies);
            map.put("months", months);
            return new ResponseVO(Contents.SUCCESS, map);
        }
        return new ResponseVO(Contents.ERROR, null);
    }


}
