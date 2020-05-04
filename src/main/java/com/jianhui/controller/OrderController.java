package com.jianhui.controller;

import com.jianhui.model.County;
import com.jianhui.model.Orders;
import com.jianhui.model.Product;
import com.jianhui.model.Store;
import com.jianhui.repository.OrdersRepository;
import com.jianhui.repository.ProductRepository;
import com.jianhui.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    ProductRepository productRepository;

    @RequestMapping("/add")
    public Orders addOrder(@RequestBody Map<String, Object> o){
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
        orders.setStore(storeRepository.findByStoreName((String) o.get("store")));
        return ordersRepository.save(orders);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable("id") Integer id) {
        ordersRepository.deleteById(id);
    }

    @GetMapping("/all")
    public List<Orders> findAll(){
        return ordersRepository.findAll();
    }

    @GetMapping("/findByStore/{store}")
    public List<Orders> findByStore(@PathVariable("store") Store store){
        return ordersRepository.findByStore(store);
    }

}
