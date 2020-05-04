package com.jianhui.controller;

import com.jianhui.model.County;
import com.jianhui.model.Store;
import com.jianhui.repository.CountyRepository;
import com.jianhui.repository.StoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Store registerStore(@RequestBody Map<String, Object> registerStore){
        Store store = new Store();
        store.setStoreName((String)registerStore.get("storeName"));
        store.setUsername((String)registerStore.get("username"));
        store.setEmail((String)registerStore.get("email"));
        store.setPhone((String)registerStore.get("phone"));
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
        return storeRepository.save(store);

    }

    @PostMapping("manager/register")
    public Store registerAdmin(@RequestBody Map<String, Object> registerAdmin){
        Store store = new Store();
        store.setUsername((String)registerAdmin.get("username"));
        store.setPassword(bCryptPasswordEncoder.encode((String)registerAdmin.get("password")));
        store.setRole("ROLE_ADMIN");
        return storeRepository.save(store);
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