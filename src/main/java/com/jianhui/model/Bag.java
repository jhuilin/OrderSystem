package com.jianhui.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Bag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bid;

    private Date createDate;
    private Double totalPrice;
    private Integer state;
    private String email;

    @ManyToMany
    @JoinTable(name="bag_products", joinColumns = @JoinColumn(name="bag_id"), inverseJoinColumns = @JoinColumn(name="product_id"))
    private Set<Product> products;

}
