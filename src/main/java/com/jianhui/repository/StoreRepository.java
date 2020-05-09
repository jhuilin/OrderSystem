package com.jianhui.repository;

import com.jianhui.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface StoreRepository extends JpaRepository<Store,Integer> {
    Store findByUsername(String username);
    Store findByStoreName(String storeName);
    Store findByEmail(String email);
}
