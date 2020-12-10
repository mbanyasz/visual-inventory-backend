package com.bme.visualinventory.domain.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class SimpleEquipmentResponse {

    private Long id;
    private String name;
    private String description;
    private byte[] imageBytes;
    private SimpleCategoryResponse category;

}
