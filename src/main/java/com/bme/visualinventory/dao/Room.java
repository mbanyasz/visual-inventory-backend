package com.bme.visualinventory.dao;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long floor;
    private String building;
    private String name;
    @OneToMany(mappedBy = "room")
    private List<Item> items;

    public Room() {}

}


