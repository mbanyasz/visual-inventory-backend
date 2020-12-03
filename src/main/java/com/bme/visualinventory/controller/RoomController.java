package com.bme.visualinventory.controller;

import com.bme.visualinventory.domain.request.CreateRoomRequest;
import com.bme.visualinventory.domain.response.DetailedRoomResponse;
import com.bme.visualinventory.domain.response.SimpleRoomResponse;
import com.bme.visualinventory.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public List<SimpleRoomResponse> getAllRooms() {
        return roomService.getAll();
    }

    @GetMapping("/{id}")
    public DetailedRoomResponse getRoom(@PathVariable("id") Long id) {
        return roomService.get(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable("id") Long id) {
        roomService.delete(id);
    }

    @PutMapping("/{id}")
    public void updateRoom(@PathVariable("id") Long id, @RequestBody CreateRoomRequest roomRequest) {
        roomService.update(id, roomRequest);
    }

    @PostMapping
    public void saveRoom(@RequestBody CreateRoomRequest roomRequest) {
        roomService.save(roomRequest);
    }

}
