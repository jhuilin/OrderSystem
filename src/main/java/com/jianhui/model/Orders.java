package com.jianhui.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer oid;

    private Date createDate;
    private Double totalPrice;
    private Integer state;
    private String email;
    private String customer;
    private String address1;
    private String city;
    private Integer zip;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name="order_products", joinColumns = @JoinColumn(name="order_id"), inverseJoinColumns = @JoinColumn(name="product_id"))
    private List<Product> products;

//    @ManyToOne
//    @JsonIgnore
//    private Store store;
    @OneToOne
    private Store store;

}
