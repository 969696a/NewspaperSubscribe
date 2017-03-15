package com.yijie.repository;

import com.yijie.model.DepartmentEntity;
import com.yijie.model.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Created by barbara on 2016/12/23.
 */
@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, Integer>{

    public List<OrdersEntity> findByUId(Integer uId);
    public List<OrdersEntity> findByNId(Integer nId);


    @Transactional
    @Query(value = "from OrdersEntity o where o.uId in (select uId from UsersEntity u where u.dId=:qdId)")
    public List<OrdersEntity> findByUserDId(@Param("qdId") Integer dId);

    @Transactional
    @Query(value = "select sum(oNum) from OrdersEntity o where o.uId=:quId")
    public Integer getCopiesSumByUId(@Param("quId") Integer uId);

    @Transactional
    @Query(value = "select sum(oMonth) from OrdersEntity o where o.uId=:quId")
    public Integer getMonthSumByUId(@Param("quId") Integer uId);

    @Transactional
    @Query(value = "select sum(oNum) from OrdersEntity o where o.nId=:qnId")
    public Integer getCopiesSumByNId(@Param("qnId") Integer nId);

    @Transactional
    @Query(value = "select sum(oMonth) from OrdersEntity o where o.nId=:qnId")
    public Integer getMonthSumByNId(@Param("qnId") Integer nId);

    @Transactional
    @Query(value = "select sum(oNum) from OrdersEntity o where o.uId in " +
            "(select uId from UsersEntity u where u.dId=:qdId)")
    public Integer getCopiesSumByUserDId(@Param("qdId") Integer dId);

    @Transactional
    @Query(value = "select sum(oMonth) from OrdersEntity o where o.uId in " +
            "(select uId from UsersEntity u where u.dId=:qdId)")
    public Integer getMonthsSumByUserDId(@Param("qdId") Integer dId);
}
