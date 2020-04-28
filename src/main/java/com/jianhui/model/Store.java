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
public class Store {

    @Id
    @GeneratedValue
    private Integer sid;

    private String storeName;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Integer state;

    private String role;

    @ManyToMany
    @JoinTable(name="store_county", joinColumns = @JoinColumn(name="store_id"), inverseJoinColumns = @JoinColumn(name="county_id"))
    private Set<County> counties;

    @OneToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "sid", referencedColumnName = "sid")
    private Set<Product> products;

    @OneToMany(targetEntity = Bag.class)
    @JoinColumn(name = "sid", referencedColumnName = "sid")
    private Set<Bag> bags;
}
