package com.jianhui.repository;

import com.jianhui.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UnitRepository extends JpaRepository<Unit,Integer> {
    Unit findByName(String name);

    @Transactional
    void deleteByName(String name);
}
