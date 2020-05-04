package com.jianhui.controller.secured;

import com.jianhui.model.*;
import com.jianhui.repository.ProductRepository;
import com.jianhui.repository.StoreRepository;
import com.jianhui.repository.TypeRepository;
import com.jianhui.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("secure/product")
public class SecuredProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UnitRepository unitRepository;

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    StoreRepository storeRepository;


    @PostMapping("/add")
    public Product addProduct(@RequestBody Map<String, Object> p) {
        Product product = new Product();
        product.setName((String) p.get("name"));
        product.setQty((Integer) p.get("qty"));
        product.setPrice((Double) p.get("price"));
        product.setState(1);
        product.setUnit(unitRepository.findByName((String) p.get("unit")));
        product.setType(typeRepository.findByName((String) p.get("type")));
        product.setStore(storeRepository.findByStoreName((String) p.get("store")));
        return productRepository.save(product);
    }

    @PutMapping("/update")
    public Product update(@RequestBody Map<String, Object> p) {
        Product product = productRepository.findById((Integer) p.get("pid")).orElse(null);
        if (product != null ){
            product.setName((String) p.get("name"));
            product.setQty((Integer) p.get("qty"));
            product.setPrice((Double) p.get("price"));
            product.setState((Integer) p.get("state"));
            product.setUnit(unitRepository.findByName((String) p.get("unit")));
            product.setType(typeRepository.findByName((String) p.get("type")));
            return productRepository.save(product);
        }
        else
            return null;
    }

    @DeleteMapping("deleteById/{id}")
    public void deleteProductById(@PathVariable("id") Integer id) {
        productRepository.deleteById(id);
    }
}