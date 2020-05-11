package com.jianhui.service;

import com.jianhui.model.County;
import com.jianhui.model.Store;
import com.jianhui.repository.CountyRepository;
import com.jianhui.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StoreService {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    CountyRepository countyRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public List<Store> findAll(){
        return allStores();
    }

    public List<Store> findStoreByCounty(String county){
        String name = countyRepository.findByName(county).getName();
        List<Store> stores = allStores();
        List<Store> result = new LinkedList<>();
        for (Store store: stores){
            if (store.getCounties() == null)
                continue;
            List<County> counties = store.getCounties();
            for (County c : counties){
                if (c.getName().equalsIgnoreCase(name)) {
                    result.add(store);
                    break;
                }
            }
        }
        return result;
    }

    public Store findByUsername(String username){
        return storeRepository.findByUsername(username);
    }

    public Store findByStoreName(String storeName){
        return storeRepository.findByStoreName(storeName);
    }

    public Optional<Store> findByStoreId(Integer id){
        return storeRepository.findById(id);
    }

    public Store updateAdmin(Map<String, Object> updatedAdmin){
        Store store = storeRepository.findByUsername((String) updatedAdmin.get("username"));
        store.setPassword(bCryptPasswordEncoder.encode((String)updatedAdmin.get("password")));
        storeRepository.save(store);
        return store;
    }

    public void deleteStoreById(Integer id) {
        storeRepository.deleteById(id);
    }

    public boolean validateByUsrEmail(Map<String, String> s){
        Store s1 = storeRepository.findByUsername(s.get("username"));
        Store s2 = storeRepository.findByEmail(s.get("email"));
        if (s1 == null || s2 == null || s1.getSid() != s2.getSid())
            return false;
        return true;
    }

    public boolean validateByPassword(Map<String, String> s){
        Store s1 = storeRepository.findByUsername(s.get("username"));
        String oldPassword = s.get("password");
        if (bCryptPasswordEncoder.matches(oldPassword,s1.getPassword()))
            return true;
        return false;
    }

    public ResponseEntity<Store> changePassword(Map<String, String> s){
        Store store = storeRepository.findByUsername(s.get("username"));
        store.setPassword(bCryptPasswordEncoder.encode(s.get("password")));
        return new ResponseEntity<>(storeRepository.save(store), HttpStatus.OK);
    }

    public Store update(Map<String, Object> updatedStore){
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

    private List<Store> allStores(){
        List<Store> storeList = storeRepository.findAll();
        List<Store> list = new LinkedList<>();
        for (Store store: storeList){
            if (store.getRole().equalsIgnoreCase("ROLE_STORE"))
                list.add(store);
        }
        return list;
    }

}
