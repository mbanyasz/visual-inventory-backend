package com.bme.visualinventory.domain.request;

import lombok.Data;

@Data
public class CreateCategoryRequest {

    private String name;
    private String description;

}
