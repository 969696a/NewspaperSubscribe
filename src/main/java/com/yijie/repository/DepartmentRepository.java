package com.yijie.repository;

import com.yijie.model.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by barbara on 2016/12/5.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {

}
