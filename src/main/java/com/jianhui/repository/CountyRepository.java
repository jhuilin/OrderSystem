package com.jianhui.repository;

import com.jianhui.model.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CountyRepository extends JpaRepository<County, Integer> {
    County findByName(String name);

    @Transactional
    void deleteByName(String name);
}
