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
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"storeName", "username"})
})
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
    private String imageUrl;
    private String address1;
    private String city;
    private Integer zip;

    private String role;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name="store_county", joinColumns = @JoinColumn(name="store_id"), inverseJoinColumns = @JoinColumn(name="county_id"))
    private List<County> counties;

//    @OneToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
//    @JoinColumn(name = "sid", referencedColumnName = "sid")
    @OneToMany(targetEntity = Product.class, mappedBy = "store", cascade = CascadeType.ALL)
    private List<Product> products;

//    @OneToMany(targetEntity = Orders.class)
//    @JoinColumn(name = "sid", referencedColumnName = "sid")
//    @OneToMany(targetEntity = Orders.class, mappedBy = "store", cascade = CascadeType.ALL)
//    private List<Orders> orders;
}
