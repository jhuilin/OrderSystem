package com.jianhui.service;

import com.jianhui.model.Product;
import com.jianhui.model.Store;
import com.jianhui.repository.ProductRepository;
import com.jianhui.repository.StoreRepository;
import com.jianhui.repository.TypeRepository;
import com.jianhui.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UnitRepository unitRepository;

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    StoreRepository storeRepository;

    public List<Product> findProductsByStore(Store store){
        return productRepository.findAllByStore(store);
    }

    public Optional<Product> findById(Integer id){
        return productRepository.findById(id);
    }

    public Product addProduct(Map<String, Object> p) {
        Product product = new Product();
        product.setName((String) p.get("name"));
        product.setQty((Integer) p.get("qty"));
        product.setPrice((Double) p.get("price"));
        product.setState(1);
        product.setImageUrl((String)p.get("imageUrl"));
        product.setDescription((String)p.get("description"));
        product.setUnit(unitRepository.findByName((String) p.get("unit")));
        product.setType(typeRepository.findByName((String) p.get("type")));
        product.setStore(storeRepository.findById((Integer) p.get("store")).orElse(null));
        return productRepository.save(product);
    }



    public ResponseEntity<Product> update(Map<String,Object> p) {
        Product product = productRepository.findById((Integer) p.get("pid")).orElse(null);
        if (product == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        product.setName((String) p.get("name"));
        product.setQty((Integer) p.get("qty"));
        product.setPrice((Double) p.get("price"));
        product.setState((Integer) p.get("state"));
        product.setImageUrl((String)p.get("imageUrl"));
        product.setDescription((String)p.get("description"));
        product.setUnit(unitRepository.findByName((String) p.get("unit")));
        product.setType(typeRepository.findByName((String) p.get("type")));
        return new ResponseEntity<>(productRepository.save(product),HttpStatus.OK);

    }

    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }
}
