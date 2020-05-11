package com.jianhui.service;

import com.jianhui.model.Product;
import com.jianhui.model.Store;
import com.jianhui.repository.ProductRepository;
import com.jianhui.repository.StoreRepository;
import com.jianhui.repository.TypeRepository;
import com.jianhui.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        String image = (String)p.get("imageUrl");
        if (image != null)
            product.setImageUrl(image);
        String description = (String)p.get("description");
        if (description != null)
            product.setDescription(description);
        product.setState(1);
        product.setUnit(unitRepository.findByName((String) p.get("unit")));
        product.setType(typeRepository.findByName((String) p.get("type")));
        product.setStore(storeRepository.findByStoreName((String) p.get("store")));
        return productRepository.save(product);
    }



    public Product update(Map<String, Object> p) {
        Product product = productRepository.findById((Integer) p.get("pid")).orElse(null);
        if (product != null ){
            product.setName((String) p.get("name"));
            product.setQty((Integer) p.get("qty"));
            product.setPrice((Double) p.get("price"));
            product.setState((Integer) p.get("state"));
            String image = (String)p.get("imageUrl");
            if (image != null)
                product.setImageUrl(image);
            String description = (String)p.get("description");
            if (description != null)
                product.setDescription(description);
            product.setUnit(unitRepository.findByName((String) p.get("unit")));
            product.setType(typeRepository.findByName((String) p.get("type")));
            return productRepository.save(product);
        }
        else
            return null;
    }

    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }
}
