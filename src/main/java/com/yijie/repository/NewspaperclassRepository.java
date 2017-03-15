package com.yijie.repository;

import com.yijie.model.NewspaperclassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by barbara on 2016/12/16.
 */
@Repository
public interface NewspaperclassRepository extends JpaRepository<NewspaperclassEntity, Integer> {
}
