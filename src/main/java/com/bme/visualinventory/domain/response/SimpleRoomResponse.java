package com.bme.visualinventory.domain.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class SimpleRoomResponse {

    private Long id;
    private Long floor;
    private String building;
    private String name;

}
