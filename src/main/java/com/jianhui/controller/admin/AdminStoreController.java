package com.jianhui.controller.admin;

import com.jianhui.model.County;
import com.jianhui.model.Store;
import com.jianhui.repository.CountyRepository;
import com.jianhui.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/validateByUsrEmail")
    public boolean validateByUsrEmail(@RequestBody Map<String, String> s){
        Store s1 = storeRepository.findByUsername(s.get("username"));
        Store s2 = storeRepository.findByEmail(s.get("email"));
        if (s1 == null || s2 == null || s1.getSid() != s2.getSid())
            return false;
        return true;
    }

    @GetMapping("/validatePassword")
    public boolean validateByPassword(@RequestBody Map<String, String> s){
        Store s1 = storeRepository.findByUsername(s.get("username"));
        String oldPassword = s.get("password");
        if (bCryptPasswordEncoder.matches(oldPassword,s1.getPassword()))
            return true;
        return false;
    }

    @PutMapping("/changePassword")
    public ResponseEntity<Store> changePassword(@RequestBody Map<String, String> s){
        Store store = storeRepository.findByUsername(s.get("username"));
        store.setPassword(bCryptPasswordEncoder.encode(s.get("password")));
        return new ResponseEntity<>(storeRepository.save(store), HttpStatus.OK);
    }
}
