package com.bme.visualinventory.dao;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Lob
    private byte[] image;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "equipment")
    private List<Item> items;

    public Equipment() {}
}
