package com.bme.visualinventory.domain.dto;

import com.bme.visualinventory.domain.response.SimpleItemResponse;
import lombok.Data;

@Data
public class ItemDto {

    private ItemDtoKey key;
    private SimpleItemResponse item;

}
