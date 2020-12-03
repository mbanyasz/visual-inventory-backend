package com.bme.visualinventory.domain.response;

import lombok.Data;

@Data
public class SimpleCategoryResponse {

    private Long id;
    private String name;
    private String description;
    private byte[] imageBytes;

}
