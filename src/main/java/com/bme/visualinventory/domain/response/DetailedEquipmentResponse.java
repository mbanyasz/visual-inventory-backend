package com.bme.visualinventory.domain.response;

import lombok.Data;

import java.util.List;

@Data
public class DetailedEquipmentResponse {

    private Long id;
    private String name;
    private String description;
    private byte[] imageBytes;
    private SimpleCategoryResponse category;
    private List<SimpleItemResponse> items;

}
