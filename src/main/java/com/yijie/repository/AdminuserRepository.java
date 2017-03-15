package com.yijie.repository;

import com.yijie.model.AdminuserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by barbara on 2016/12/5.
 */
@Repository
public interface AdminuserRepository extends JpaRepository<AdminuserEntity, String> {

//    @Modifying
//    @Transactional
//    @Query("update AdminuserEntity ad set ad.aPassword =: qaPassword where ad.aName =: qaName")
//    public void updateAdmin(@Param("qaName") String aName, @Param("qaPassword") String aPassword);
}
