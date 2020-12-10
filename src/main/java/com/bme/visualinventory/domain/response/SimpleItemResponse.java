package com.bme.visualinventory.domain.response;

import lombok.Data;

@Data
public class SimpleItemResponse {

    private int numberOfItems;
    private SimpleRoomResponse room;
    private SimpleEquipmentResponse equipment;

}
