package com.bme.visualinventory.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ItemDtoKey {

    private Long equipmentId;
    private Long roomId;

    public ItemDtoKey(Long equipmentId, Long roomId) {
        this.equipmentId = equipmentId;
        this.roomId = roomId;
    }
}
