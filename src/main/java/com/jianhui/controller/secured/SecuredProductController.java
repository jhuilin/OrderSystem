package com.jianhui.controller.secured;

import com.jianhui.model.*;
import com.jianhui.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("secure/product")
public class SecuredProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public Product addProduct(@RequestBody Map<String, Object> p) {
        return productService.addProduct(p);
    }

    @PutMapping("/update")
    public ResponseEntity<Product> update(@RequestBody Map<String, Object> p) {
        return productService.update(p);
    }

    @DeleteMapping("deleteById/{id}")
    public void deleteProductById(@PathVariable("id") Integer id) {
        productService.deleteProductById(id);
    }
}