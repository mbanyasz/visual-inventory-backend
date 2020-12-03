package com.bme.visualinventory.dao;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isFunctional;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Equipment() {}
}
