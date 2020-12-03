package com.bme.visualinventory.domain.response;

import lombok.Data;

@Data
public class EquipmentResponse {

    private Long id;
    private boolean isFunctional;
    private String name;
    private String description;
    private Long roomId;
    private String roomBuilding;
    private Long roomFloor;
    private String roomName;
    private Long categoryId;
    private String categoryName;
    private String categoryDescription;
    private byte[] categoryImage;

}
