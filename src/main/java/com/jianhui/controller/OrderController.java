package com.jianhui.controller;

import com.jianhui.model.Orders;
import com.jianhui.model.Store;
import com.jianhui.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/add")
    public Orders addOrder(@RequestBody Map<String, Object> o){
        return orderService.addOrder(o);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable("id") Integer id) {
        orderService.deleteOrder(id);
    }

    @GetMapping("/all")
    public List<Orders> findAll(){
        return orderService.findAll();
    }

    @GetMapping("/findByStore/{store}")
    public List<Orders> findByStore(@PathVariable("store") Store store){
        return orderService.findByStore(store);
    }

}
