package com.jianhui.service;

import com.jianhui.model.Orders;
import com.jianhui.model.Product;
import com.jianhui.model.Store;
import com.jianhui.repository.OrdersRepository;
import com.jianhui.repository.ProductRepository;
import com.jianhui.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    ProductRepository productRepository;

    public Orders addOrder(Map<String, Object> o){
        Orders orders = new Orders();
        orders.setCreateDate(new Date());
        orders.setEmail((String) o.get("email"));
        orders.setTotalPrice((Double)o.get("totalPrice"));
        orders.setState(0);
        orders.setCustomer((String) o.get("customer"));
        orders.setAddress1((String) o.get("address1"));
        orders.setCity((String) o.get("city"));
        orders.setZip((Integer) o.get("zip"));
        List<Product> list = new LinkedList<>();
        List<LinkedHashMap<String,Integer>> temp = (List<LinkedHashMap<String, Integer>>) o.get("products");
        for (LinkedHashMap<String, Integer> t: temp){
            for (Integer id: t.values()){
                Product p = productRepository.findById(id).orElse(null);
                if (p != null)
                    list.add(p);
            }
        }
        orders.setProducts(list);
        orders.setStore(storeRepository.findById((Integer) o.get("store")).orElse(null));
        return ordersRepository.save(orders);
    }

    public void deleteOrder(Integer id) {
        ordersRepository.deleteById(id);
    }

    public List<Orders> findAll(){
        return ordersRepository.findAll();
    }

    public List<Orders> findByStore(Store store){
        return ordersRepository.findByStore(store);
    }
}
