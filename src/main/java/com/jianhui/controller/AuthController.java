package com.jianhui.controller;

import com.jianhui.model.County;
import com.jianhui.model.Store;
import com.jianhui.repository.CountyRepository;
import com.jianhui.repository.StoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private CountyRepository countyRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Store> registerStore(@RequestBody Map<String, Object> registerStore){
        Store store = new Store();
        String storeName = (String)registerStore.get("storeName");
        String username = (String)registerStore.get("username");
        if (storeRepository.findByStoreName(storeName) != null)
            return new ResponseEntity<>(null,HttpStatus.CONFLICT);
        if (storeRepository.findByUsername(username) != null)
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        store.setStoreName(storeName);
        store.setUsername(username);
        store.setEmail((String)registerStore.get("email"));
        store.setPhone((String)registerStore.get("phone"));
        store.setAddress1((String) registerStore.get("address1"));
        store.setCity((String) registerStore.get("city"));
        store.setZip((Integer) registerStore.get("zip"));
        store.setImageUrl((String) registerStore.get("imageUrl"));
        store.setState(1);
        List<County> list = new LinkedList<>();
        List<LinkedHashMap<String,String>> temp = (List<LinkedHashMap<String, String>>) registerStore.get("counties");
        for (LinkedHashMap<String, String> t: temp){
            for (String name: t.values()){
                list.add(countyRepository.findByName(name));
            }
        }
        store.setCounties(list);
        store.setPassword(bCryptPasswordEncoder.encode((String)registerStore.get("password")));
        store.setRole("ROLE_STORE");
        return new ResponseEntity<>(storeRepository.save(store),HttpStatus.OK);

    }

    @PostMapping("manager/register")
    public ResponseEntity<Store> registerAdmin(@RequestBody Map<String, Object> registerAdmin){
        Store store = new Store();
        String username = (String)registerAdmin.get("username");
        if (storeRepository.findByUsername(username) != null)
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        store.setUsername(username);
        store.setPassword(bCryptPasswordEncoder.encode((String)registerAdmin.get("password")));
        store.setRole("ROLE_ADMIN");
        return new ResponseEntity<>(storeRepository.save(store), HttpStatus.OK);
    }

}

//        {
//            "storeName": "store1",
//                "username": "store1",
//                "password": "store1",
//                "email": "store1@email.com",
//                "phone": "9179169111",
//                "counties":
//        		[
//            {
//                "name":"bronx"
//            },
//            {
//                "name":"queens"
//            }
//        		]
//        }