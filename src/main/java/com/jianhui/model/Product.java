package com.jianhui.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Integer pid;

    private String name;
    private Integer qty;
    private Double price;
    private Integer state;
    private String imageUrl;
    private String description;

    @OneToOne
    private Type type;

    @OneToOne
    private Unit unit;

    @ManyToMany(mappedBy = "products")
    private List<Orders> orders;

    @ManyToOne
    @JsonIgnore
    private Store store;

}
