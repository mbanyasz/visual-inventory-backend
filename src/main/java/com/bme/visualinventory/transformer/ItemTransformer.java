package com.bme.visualinventory.transformer;

import com.bme.visualinventory.dao.Item;
import com.bme.visualinventory.domain.dto.ItemDto;
import com.bme.visualinventory.domain.dto.ItemDtoKey;
import com.bme.visualinventory.domain.response.SimpleItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemTransformer {

    @Autowired
    private EquipmentTransformer equipmentTransformer;

    @Autowired
    private RoomTransformer roomTransformer;

    public SimpleItemResponse createSimpleItemResponse(Item item) {
        SimpleItemResponse simpleItemResponse = new SimpleItemResponse();
        simpleItemResponse.setNumberOfItems(1);
        simpleItemResponse.setEquipment(equipmentTransformer.createSimpleEquipmentResponse(item.getEquipment()));
        simpleItemResponse.setRoom(roomTransformer.createSimpleRoomResponse(item.getRoom()));
        return simpleItemResponse;
    }

    public ItemDto createItemDetails(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setKey(new ItemDtoKey(item.getEquipment().getId(), item.getRoom().getId()));
        itemDto.setItem(createSimpleItemResponse(item));
        return itemDto;
    }
}
