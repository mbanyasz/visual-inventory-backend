package com.bme.visualinventory.domain.request;

import lombok.Data;

@Data
public class DeleteItemRequest {

    private Long equipmentId;
    private Long roomId;
    private Long numberOfItems;

}
