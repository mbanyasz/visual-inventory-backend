package com.bme.visualinventory.domain.response;

import lombok.Data;

@Data
public class EquipmentForRoomResponse {

    private Long id;
    private boolean isFunctional;
    private String name;
    private String description;
    private Long categoryId;
    private String categoryName;

}
