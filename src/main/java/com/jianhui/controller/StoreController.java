package com.jianhui.controller;

import com.jianhui.model.Store;
import com.jianhui.repository.CountyRepository;
import com.jianhui.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;


@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    CountyRepository countyRepository;


    @GetMapping("/allStores")
    public List<Store> findAll(){
        List<Store> storeList = storeRepository.findAll();
        List<Store> list = new LinkedList<>();
        for (Store store: storeList){
            if (store.getRole().equalsIgnoreCase("ROLE_STORE"))
                list.add(store);
        }
        return list;
    }

    @GetMapping("/searchByUsr/{username}")
    public Store findByUsername(@PathVariable("username") String username){
        return storeRepository.findByUsername(username);
    }

    @GetMapping("/searchByName/{storeName}")
    public Store findByStoreName(@PathVariable("storeName") String storeName){
        return storeRepository.findByStoreName(storeName);
    }

    @GetMapping("/searchById/{id}")
    public Store findByStoreId(@PathVariable("id") Integer id){
        return storeRepository.findById(id).orElse(null);
    }

//    @PutMapping("/secure/update")
//    public Store update(@RequestBody Map<String, Object> updatedStore){
//        Store store = storeRepository.findByUsername((String) updatedStore.get("username"));
//        store.setStoreName((String) updatedStore.get("storeName"));
//        store.setEmail((String) updatedStore.get("email"));
//        store.setPhone((String)updatedStore.get("phone"));
//        store.setState((Integer) updatedStore.get("state"));
//        List<County> list = new LinkedList<>();
//        List<LinkedHashMap<String,String>> temp = (List<LinkedHashMap<String, String>>) updatedStore.get("counties");
//        for (LinkedHashMap<String, String> t: temp){
//            for (String name: t.values()){
//                list.add(countyRepository.findByName(name));
//            }
//        }
//        store.setCounties(list);
//        store.setPassword(bCryptPasswordEncoder.encode((String)updatedStore.get("password")));
//        storeRepository.save(store);
//        return store;
//    }

//    @PutMapping("/admin/update")
//    public Store updateAdmin(@RequestBody Map<String, Object> updatedAdmin){
//        Store store = storeRepository.findByUsername((String) updatedAdmin.get("username"));
//        store.setPassword(bCryptPasswordEncoder.encode((String)updatedAdmin.get("password")));
//        storeRepository.save(store);
//        return store;
//    }
//
//    @DeleteMapping("/admin/delete/{id}")
//    public void deleteStoreById(@PathVariable("id") Integer id) {
//        storeRepository.deleteById(id);
//    }
}





//        drop table county;
//        drop table order_products;
//        drop table orders;
//        drop table product;
//        drop table store;
//        drop table store_county;
//        drop table type;
//        drop table unit;
