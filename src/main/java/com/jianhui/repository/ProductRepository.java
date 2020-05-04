package com.jianhui.repository;

import com.jianhui.model.Product;
import com.jianhui.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByStore(Store store);
}
