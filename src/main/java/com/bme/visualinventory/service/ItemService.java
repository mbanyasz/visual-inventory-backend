package com.bme.visualinventory.service;

import com.bme.visualinventory.dao.Category;
import com.bme.visualinventory.dao.Equipment;
import com.bme.visualinventory.dao.Item;
import com.bme.visualinventory.dao.Room;
import com.bme.visualinventory.domain.dto.ItemDto;
import com.bme.visualinventory.domain.dto.ItemDtoKey;
import com.bme.visualinventory.domain.request.CreateItemRequest;
import com.bme.visualinventory.domain.request.DeleteItemRequest;
import com.bme.visualinventory.domain.request.MoveItemRequest;
import com.bme.visualinventory.domain.response.SimpleItemResponse;
import com.bme.visualinventory.repository.EquipmentRepository;
import com.bme.visualinventory.repository.ItemRepository;
import com.bme.visualinventory.repository.RoomRepository;
import com.bme.visualinventory.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private ItemTransformer itemTransformer;

    public List<SimpleItemResponse> getAll() {
        List<ItemDto> itemDtoList = itemRepository.findAll().stream()
                .map(item -> itemTransformer.createItemDetails(item))
                .collect(toList());
        return aggregateItemDtoValues(itemDtoList);
    }

    public List<SimpleItemResponse> getItemsByRoom(Room id) {
        List<ItemDto> itemDtoList = itemRepository.findByRoom(id).stream()
                .map(item -> itemTransformer.createItemDetails(item))
                .collect(toList());
        return aggregateItemDtoValues(itemDtoList);
    }

    public List<SimpleItemResponse> getItemsByCategory(Category category) {
        return category.getEquipments().stream()
                .map(this::getItemsByEquipment)
                .flatMap(Collection::stream)
                .collect(toList());
    }

    public List<SimpleItemResponse> getItemsByEquipment(Equipment equipment) {
        List<ItemDto> itemDtoList = itemRepository.findByEquipment(equipment).stream()
                .map(item -> itemTransformer.createItemDetails(item))
                .collect(toList());
        return aggregateItemDtoValues(itemDtoList);
    }

    private List<SimpleItemResponse> aggregateItemDtoValues(List<ItemDto> itemDtoList) {
        Map<ItemDtoKey, SimpleItemResponse> collect = itemDtoList.stream().collect(toMap(
                ItemDto::getKey,
                ItemDto::getItem,
                this::mergeItemsWithSameKey));
        return new ArrayList<>(collect.values());
    }
    private SimpleItemResponse mergeItemsWithSameKey(SimpleItemResponse oldValue, SimpleItemResponse newValue) {
        int numberOfItems = oldValue.getNumberOfItems() + newValue.getNumberOfItems();
        oldValue.setNumberOfItems(numberOfItems);
        return oldValue;
    }

    public void save(CreateItemRequest itemRequest) {
        Equipment equipment = equipmentRepository.findById(itemRequest.getEquipmentId()).orElseThrow(() -> new IllegalArgumentException(""));;
        Room room = roomRepository.findById(itemRequest.getRoomId()).orElseThrow(() -> new IllegalArgumentException(""));;
        for (int i = 0; i < itemRequest.getNumberOfItems(); i++) {
            Item item = new Item();
            item.setRoom(room);
            item.setEquipment(equipment);
            itemRepository.save(item);
        }
    }

    public void move(MoveItemRequest itemRequest) {
        Equipment equipment = equipmentRepository.findById(itemRequest.getEquipmentId()).orElseThrow(() -> new IllegalArgumentException(""));;
        Room fromRoom = roomRepository.findById(itemRequest.getFromRoomId()).orElseThrow(() -> new IllegalArgumentException(""));;
        Room toRoom = roomRepository.findById(itemRequest.getToRoomId()).orElseThrow(() -> new IllegalArgumentException(""));;
        List<Item> items = itemRepository.findByRoomAndEquipment(fromRoom, equipment);

        if(itemRequest.getNumberOfItems() <= items.size()) {
            for (int i = 0; i < itemRequest.getNumberOfItems(); i++) {
                Item item = items.get(i);
                item.setRoom(toRoom);
                itemRepository.save(item);
            }
        }
        else {
            throw new IllegalArgumentException("");
        }
    }

    public void delete(DeleteItemRequest itemRequest) {
        Equipment equipment = equipmentRepository.findById(itemRequest.getEquipmentId()).orElseThrow(() -> new IllegalArgumentException(""));;
        Room room = roomRepository.findById(itemRequest.getRoomId()).orElseThrow(() -> new IllegalArgumentException(""));;
        List<Item> items = itemRepository.findByRoomAndEquipment(room, equipment);

        if(itemRequest.getNumberOfItems() <= items.size()) {
            for (int i = 0; i < itemRequest.getNumberOfItems(); i++) {
                Item item = items.get(i);
                itemRepository.delete(item);
            }
        }
        else {
            throw new IllegalArgumentException("");
        }
    }
}
