package com.jianhui.controller;

import com.jianhui.model.County;
import com.jianhui.model.Store;
import com.jianhui.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    StoreService storeService;

    @GetMapping("/test")
    public String test(){
        return "test from public";
    }


    @GetMapping("/allStores")
    public List<Store> findAll(){
        return storeService.findAll();
    }

    @GetMapping("searchByCounty/{county}")
    public List<Store> findStoreByCounty(@PathVariable("county") String county){
        return storeService.findStoreByCounty(county);
    }

    @GetMapping("/searchByUsr/{username}")
    public Store findByUsername(@PathVariable("username") String username){
        return storeService.findByUsername(username);
    }

    @GetMapping("/searchByName/{storeName}")
    public Store findByStoreName(@PathVariable("storeName") String storeName){
        return storeService.findByStoreName(storeName);
    }

    @GetMapping("/searchById/{id}")
    public Optional<Store> findByStoreId(@PathVariable("id") Integer id){
        return storeService.findByStoreId(id);
    }

}





//        drop table county;
//        drop table order_products;
//        drop table orders;
//        drop table product;
//        drop table store;
//        drop table store_county;
//        drop table type;
//        drop table unit;
