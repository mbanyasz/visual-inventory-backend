package com.bme.visualinventory.domain.response;

import lombok.Data;

@Data
public class SimpleRoomResponse {

    private Long id;
    private Long floor;
    private String building;
    private String name;

}
