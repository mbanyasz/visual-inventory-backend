package com.bme.visualinventory.domain.request;

import lombok.Data;

@Data
public class ModifyEquipmentRequest {

    private String name;
    private String description;
    private Long roomId;
    private Long categoryId;
    private boolean isFunctional;

}
