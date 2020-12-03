package com.bme.visualinventory.domain.response;

import lombok.Data;

import java.util.List;

@Data
public class DetailedCategoryResponse {

    private Long id;
    private String name;
    private String description;
    private byte[] imageBytes;
    private List<EquipmentForCategoryResponse> equipments;

}
