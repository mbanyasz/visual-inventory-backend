package com.bme.visualinventory.domain.request;

import lombok.Data;

@Data
public class MoveItemRequest {

    private Long equipmentId;
    private Long fromRoomId;
    private Long toRoomId;
    private Long numberOfItems;

}
