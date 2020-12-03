package com.bme.visualinventory.domain.response;

import lombok.Data;

import java.util.List;

@Data
public class DetailedRoomResponse {

    private Long id;
    private String building;
    private Long floor;
    private String name;
    private List<EquipmentForRoomResponse> equipments;

}
