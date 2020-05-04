package com.jianhui.controller.secured;

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
        List<County> list = new LinkedList<>();
        List<LinkedHashMap<String,String>> temp = (List<LinkedHashMap<String, String>>) updatedStore.get("counties");
        for (LinkedHashMap<String, String> t: temp){
            for (String name: t.values()){
                list.add(countyRepository.findByName(name));
            }
        }
        store.setCounties(list);
        store.setPassword(bCryptPasswordEncoder.encode((String)updatedStore.get("password")));
        storeRepository.save(store);
        return store;
    }
}
