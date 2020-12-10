package com.bme.visualinventory.transformer;

import com.bme.visualinventory.dao.Room;
import com.bme.visualinventory.domain.request.CreateRoomRequest;
import com.bme.visualinventory.domain.response.DetailedRoomResponse;
import com.bme.visualinventory.domain.response.SimpleItemResponse;
import com.bme.visualinventory.domain.response.SimpleRoomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomTransformer {

    @Autowired
    private EquipmentTransformer equipmentTransformer;

    public Room createRoom(CreateRoomRequest roomRequest) {
        return updateRoom(roomRequest, new Room());
    }

    public Room updateRoom(CreateRoomRequest roomRequest, Room room) {
        room.setName(roomRequest.getName());
        room.setFloor(roomRequest.getFloor());
        room.setBuilding(roomRequest.getBuilding());
        return room;
    }

    public SimpleRoomResponse createSimpleRoomResponse(Room room) {
        SimpleRoomResponse response = new SimpleRoomResponse();
        response.setId(room.getId());
        response.setBuilding(room.getBuilding());
        response.setFloor(room.getFloor());
        response.setName(room.getName());
        return response;
    }

    public DetailedRoomResponse createDetailedRoomResponse(Room room, List<SimpleItemResponse> items) {
        DetailedRoomResponse response = new DetailedRoomResponse();
        response.setId(room.getId());
        response.setBuilding(room.getBuilding());
        response.setFloor(room.getFloor());
        response.setName(room.getName());
        response.setItems(items);
        return response;
    }
}
