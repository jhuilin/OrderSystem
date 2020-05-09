package com.jianhui.repository;

import com.jianhui.model.Orders;
import com.jianhui.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrdersRepository extends JpaRepository<Orders,Integer> {
    List<Orders> findByStore(Store store);
}
