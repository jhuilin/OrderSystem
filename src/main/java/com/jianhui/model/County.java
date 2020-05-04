package com.jianhui.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class County {

    @Id
    @GeneratedValue
    private Integer cid;

    private String name;

    @ManyToMany(mappedBy = "counties")
    private List<Store> stores;
}
