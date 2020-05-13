package com.jianhui.controller.secured;

import com.jianhui.model.Store;
import com.jianhui.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


@RestController
@RequestMapping("secure/store")
public class SecuredStoreController {

    @Autowired
    StoreService storeService;

    @GetMapping("/test")
    public String test(){
        return "test from secure";
    }

    @PutMapping("/update")
    public ResponseEntity<Store> update(@RequestBody Map<String, Object> updatedStore){
        return storeService.update(updatedStore);
    }
    @GetMapping("/validateByUsrEmail")
    public ResponseEntity<Store> validateByUsrEmail(@RequestBody Map<String, String> s){
        return storeService.validateByUsrEmail(s);
    }

    @GetMapping("/validatePassword")
    public ResponseEntity<Store> validateByPassword(@RequestBody Map<String, String> s){
        return storeService.validateByPassword(s);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<Store> changePassword(@RequestBody Map<String, Object> s){
        return storeService.changePassword(s);
    }



}

