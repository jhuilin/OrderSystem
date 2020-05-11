package com.jianhui.repository;

import com.jianhui.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UnitRepository extends JpaRepository<Unit,Integer> {
    Unit findByName(String name);

    @Transactional
    void deleteByName(String name);
}
