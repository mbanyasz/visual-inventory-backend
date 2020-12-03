package com.bme.visualinventory.domain.request;

import lombok.Data;

@Data
public class CreateEquipmentsRequest {

    private String name;
    private String description;
    private Long roomId;
    private Long categoryId;
    private Long numberOfEquipment;

}
