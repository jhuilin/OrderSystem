package com.jianhui.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

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

    @OneToOne
    private Type type;

    @OneToOne
    private Unit unit;

    @ManyToMany(mappedBy = "products")
    private Set<Bag> bags;

}
