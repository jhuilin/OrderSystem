package com.jianhui.controller;

import com.jianhui.model.Store;

import com.jianhui.service.auth.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> loginStore){
        return authService.login(loginStore);
    }

    @PostMapping("/register")
    public ResponseEntity<Store> registerStore(@RequestBody Map<String, Object> registerStore){
        return authService.registerStore(registerStore);
    }

    @PostMapping("manager/register")
    public ResponseEntity<Store> registerAdmin(@RequestBody Map<String, Object> registerAdmin){
        return authService.registerAdmin(registerAdmin);
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