package com.bme.visualinventory.controller;

import com.bme.visualinventory.domain.request.CreateItemRequest;
import com.bme.visualinventory.domain.request.DeleteItemRequest;
import com.bme.visualinventory.domain.request.MoveItemRequest;
import com.bme.visualinventory.domain.response.SimpleItemResponse;
import com.bme.visualinventory.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<SimpleItemResponse> getAllItems() {
        return itemService.getAll();
    }

    @PostMapping("/delete")
    public void deleteItems(@RequestBody DeleteItemRequest deleteItemRequest) {
        itemService.delete(deleteItemRequest);
    }

    @PostMapping("/create")
    public void saveItems(@RequestBody CreateItemRequest createItemRequest) {
        itemService.save(createItemRequest);
    }

    @PostMapping("/move")
    public void moveItems(@RequestBody MoveItemRequest moveItemRequest) {
        itemService.move(moveItemRequest);
    }

}
