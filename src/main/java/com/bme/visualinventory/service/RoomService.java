package com.bme.visualinventory.service;

import com.bme.visualinventory.dao.Room;
import com.bme.visualinventory.domain.request.CreateRoomRequest;
import com.bme.visualinventory.domain.response.DetailedRoomResponse;
import com.bme.visualinventory.domain.response.SimpleRoomResponse;
import com.bme.visualinventory.repository.RoomRepository;
import com.bme.visualinventory.transformer.RoomTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomTransformer roomTransformer;

    public List<SimpleRoomResponse> getAll() {
        return roomRepository.findAll().stream()
                .map(roomTransformer::createSimpleRoomResponse)
                .collect(toList());
    }

    public DetailedRoomResponse get(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(""));
        return roomTransformer.createDetailedRoomResponse(room);
    }

    public void save(CreateRoomRequest roomRequest) {
        Room room = roomTransformer.createRoom(roomRequest);
        roomRepository.save(room);
    }

    public void update(Long id, CreateRoomRequest roomRequest) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(""));
        Room updatedRoom = roomTransformer.updateRoom(roomRequest, room);
        roomRepository.save(updatedRoom);
    }

    public void delete(Long id) {
        roomRepository.deleteById(id);
    }

}
