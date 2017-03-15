package com.yijie.repository;

import com.yijie.model.NewspaperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by barbara on 2016/12/23.
 */
@Repository
public interface NewspaperRepository extends JpaRepository<NewspaperEntity, Integer>{
}
