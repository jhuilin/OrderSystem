package com.jianhui.controller;

import com.jianhui.model.*;
import com.jianhui.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/list/{store}")
    public List<Product> findProductsByStore(@PathVariable("store") Store store){
        return productService.findProductsByStore(store);
    }

    @GetMapping("search/{id}")
    public Optional<Product> findById(@PathVariable("id") Integer id){
        return productService.findById(id);
    }


}
