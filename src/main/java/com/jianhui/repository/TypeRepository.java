package com.jianhui.repository;

import com.jianhui.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TypeRepository extends JpaRepository<Type,Integer> {
    Type findByName(String name);

    @Transactional
    void deleteByName(String name);
}
