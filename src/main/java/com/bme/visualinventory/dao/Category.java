package com.bme.visualinventory.dao;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Lob
    private byte[] image;
    @OneToMany(mappedBy = "category")
    private List<Equipment> equipments;

    public Category() {}

}
