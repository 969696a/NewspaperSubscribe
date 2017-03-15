package com.yijie.repository;

import com.yijie.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by barbara on 2016/11/30.
 */
@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Integer> {

    @Modifying      // 说明该方法是修改操作
    @Transactional  // 说明该方法是事务性操作
    // 定义查询
    // @Param注解用于提取参数
    @Query("update UsersEntity us set us.uPassword=:quPassword, us.uName=:quName, us.uIdcard=:quIdcard, us.uPhone=:quPhone, us.uAddress=:quAddress where us.uId=:quId")
    public void updateUser(@Param("quPassword") String uPassword, @Param("quName") String uName, @Param("quIdcard") String uIdcard,
                           @Param("quPhone") String uPhone, @Param("quAddress") String uAddress, @Param("quId") Integer uId);

    public UsersEntity findByUName(String uName);
}
