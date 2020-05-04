package com.jianhui.repository;

import com.jianhui.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,Integer> {
    Store findByUsername(String username);
    Store findByStoreName(String storeName);
}
