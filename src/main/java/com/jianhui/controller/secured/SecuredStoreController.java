package com.jianhui.controller.secured;

import com.jianhui.model.County;
import com.jianhui.model.Store;
import com.jianhui.repository.CountyRepository;
import com.jianhui.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("secure/store")
public class SecuredStoreController {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    CountyRepository countyRepository;

    @PutMapping("/update")
    public Store update(@RequestBody Map<String, Object> updatedStore){
        Store store = storeRepository.findByUsername((String) updatedStore.get("username"));
        store.setStoreName((String) updatedStore.get("storeName"));
        store.setEmail((String) updatedStore.get("email"));
        store.setPhone((String)updatedStore.get("phone"));
        store.setState((Integer) updatedStore.get("state"));
        String image = (String)updatedStore.get("imageUrl");
        if (image != null)
            store.setImageUrl(image);
        store.setAddress1((String) updatedStore.get("address1"));
        store.setCity((String) updatedStore.get("city"));
        store.setZip((Integer) updatedStore.get("zip"));
        List<County> list = new LinkedList<>();
        List<LinkedHashMap<String,String>> temp = (List<LinkedHashMap<String, String>>) updatedStore.get("counties");
        for (LinkedHashMap<String, String> t: temp){
            for (String name: t.values()){
                list.add(countyRepository.findByName(name));
            }
        }
        store.setCounties(list);
        storeRepository.save(store);
        return store;
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

