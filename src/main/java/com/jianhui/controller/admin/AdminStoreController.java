package com.jianhui.controller.admin;

import com.jianhui.model.Store;
import com.jianhui.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/admin/store")
public class AdminStoreController {

    @Autowired
    StoreService storeService;

    @GetMapping("/test")
    public String test(){
        return "test from admin";
    }

    @PutMapping("/update")
    public ResponseEntity<Store> updateAdmin(@RequestBody Map<String, Object> updatedAdmin){
        return storeService.updateAdmin(updatedAdmin);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStoreById(@PathVariable("id") Integer id) {
        storeService.deleteStoreById(id);
    }


}
