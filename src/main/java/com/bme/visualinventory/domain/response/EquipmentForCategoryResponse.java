package com.bme.visualinventory.domain.response;

import lombok.Data;

@Data
public class EquipmentForCategoryResponse {

    private Long id;
    private boolean isFunctional;
    private String name;
    private String description;
    private Long roomId;
    private String roomBuilding;
    private Long roomFloor;
    private String roomName;

}
