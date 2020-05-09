package com.jianhui.repository;

import com.jianhui.model.Product;
import com.jianhui.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByStore(Store store);
}
