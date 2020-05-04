package com.jianhui.controller;

import com.jianhui.model.*;
import com.jianhui.repository.ProductRepository;
import com.jianhui.repository.StoreRepository;
import com.jianhui.repository.TypeRepository;
import com.jianhui.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UnitRepository unitRepository;

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    StoreRepository storeRepository;

    @GetMapping("/list/{store}")
    public List<Product> findProductsByStore(@PathVariable("store") Store store){
        return productRepository.findAllByStore(store);
    }

    @GetMapping("search/{id}")
    public Optional<Product> findById(@PathVariable("id") Integer id){
        return productRepository.findById(id);
    }

//    @PostMapping("/secure/add")
//    public Product addProduct(@RequestBody Map<String, Object> p){
//        Product product = new Product();
//        product.setName((String) p.get("name"));
//        product.setQty((Integer) p.get("qty"));
//        product.setPrice((Double)p.get("price"));
//        product.setState(1);
//        product.setUnit(unitRepository.findByName((String) p.get("unit")));
//        product.setType(typeRepository.findByName((String) p.get("type")));
//        product.setStore(storeRepository.findByStoreName((String) p.get("store")));
//        return productRepository.save(product);
//    }

//    @PutMapping("/secure/update")
//    public Product update(@RequestBody Map<String, Object> p){
//        Product product = new Product();
//        product.setName((String) p.get("name"));
//        product.setQty((Integer) p.get("qty"));
//        product.setPrice((Double)p.get("price"));
//        product.setState((Integer)p.get("state"));
//        product.setUnit(unitRepository.findByName((String) p.get("unit")));
//        product.setType(typeRepository.findByName((String) p.get("type")));
//        return productRepository.save(product);
//    }
//
//    @DeleteMapping("/secure/deleteById/{id}")
//    public void deleteProductById(@PathVariable("id") Integer id) {
//        productRepository.deleteById(id);
//    }

}
