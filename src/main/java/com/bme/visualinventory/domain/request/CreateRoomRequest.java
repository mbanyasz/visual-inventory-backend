package com.bme.visualinventory.domain.request;

import lombok.Data;

@Data
public class CreateRoomRequest {

    private Long floor;
    private String building;
    private String name;

}
