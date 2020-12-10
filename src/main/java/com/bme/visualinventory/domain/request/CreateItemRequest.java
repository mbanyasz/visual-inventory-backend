package com.bme.visualinventory.domain.request;

import lombok.Data;

@Data
public class CreateItemRequest {

    private Long equipmentId;
    private Long roomId;
    private Long numberOfItems;

}
