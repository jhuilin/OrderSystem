package com.jianhui.controller.admin;

import com.jianhui.model.County;
import com.jianhui.model.Store;
import com.jianhui.repository.CountyRepository;
import com.jianhui.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/admin/store")
public class AdminStoreController {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @PutMapping("/update")
    public Store updateAdmin(@RequestBody Map<String, Object> updatedAdmin){
        Store store = storeRepository.findByUsername((String) updatedAdmin.get("username"));
        store.setPassword(bCryptPasswordEncoder.encode((String)updatedAdmin.get("password")));
        storeRepository.save(store);
        return store;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStoreById(@PathVariable("id") Integer id) {
        storeRepository.deleteById(id);
    }
}
