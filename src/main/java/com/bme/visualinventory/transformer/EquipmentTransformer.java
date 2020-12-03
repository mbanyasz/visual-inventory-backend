package com.bme.visualinventory.transformer;

import com.bme.visualinventory.dao.Category;
import com.bme.visualinventory.dao.Equipment;
import com.bme.visualinventory.dao.Room;
import com.bme.visualinventory.domain.request.CreateEquipmentsRequest;
import com.bme.visualinventory.domain.request.ModifyEquipmentRequest;
import com.bme.visualinventory.domain.response.EquipmentForCategoryResponse;
import com.bme.visualinventory.domain.response.EquipmentForRoomResponse;
import com.bme.visualinventory.domain.response.EquipmentResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EquipmentTransformer {

    public EquipmentForCategoryResponse createEquipmentForCategoryResponse(Equipment equipment) {
        EquipmentForCategoryResponse response = new EquipmentForCategoryResponse();
        response.setId(equipment.getId());
        response.setName(equipment.getName());
        response.setDescription(equipment.getDescription());
        response.setFunctional(equipment.isFunctional());
        response.setRoomId(equipment.getRoom().getId());
        response.setRoomBuilding(equipment.getRoom().getBuilding());
        response.setRoomFloor(equipment.getRoom().getFloor());
        response.setRoomName(equipment.getRoom().getName());
        return response;
    }

    public EquipmentForRoomResponse createEquipmentForRoomResponse(Equipment equipment) {
        EquipmentForRoomResponse response = new EquipmentForRoomResponse();
        response.setId(equipment.getId());
        response.setName(equipment.getName());
        response.setDescription(equipment.getDescription());
        response.setFunctional(equipment.isFunctional());
        response.setCategoryId(equipment.getCategory().getId());
        response.setCategoryName(equipment.getCategory().getName());
        return response;
    }


    public List<EquipmentForCategoryResponse> createEquipmentForCategoryResponses(List<Equipment> equipments) {
        return equipments.stream()
                .map(this::createEquipmentForCategoryResponse)
                .collect(Collectors.toList());
    }

    public List<EquipmentForRoomResponse> createEquipmentForRoomResponses(List<Equipment> equipments) {
        return equipments.stream()
                .map(this::createEquipmentForRoomResponse)
                .collect(Collectors.toList());
    }

    public Equipment createEquipment(CreateEquipmentsRequest equipmentsRequest, Room room, Category category) {
        Equipment equipment = new Equipment();
        equipment.setName(equipmentsRequest.getName());
        equipment.setDescription(equipmentsRequest.getDescription());
        equipment.setFunctional(true);
        equipment.setRoom(room);
        equipment.setCategory(category);
        return equipment;
    }

    public Equipment updateEquipment(ModifyEquipmentRequest equipmentsRequest, Equipment equipment, Room room, Category category) {
        equipment.setName(equipmentsRequest.getName());
        equipment.setDescription(equipmentsRequest.getDescription());
        equipment.setFunctional(equipmentsRequest.isFunctional());
        equipment.setRoom(room);
        equipment.setCategory(category);
        return equipment;
    }

    public EquipmentResponse createEquipmentResponse(Equipment equipment) {
        EquipmentResponse response = new EquipmentResponse();
        response.setId(equipment.getId());
        response.setName(equipment.getName());
        response.setFunctional(equipment.isFunctional());
        response.setDescription(equipment.getDescription());
        response.setRoomId(equipment.getRoom().getId());
        response.setRoomBuilding(equipment.getRoom().getBuilding());
        response.setRoomFloor(equipment.getRoom().getFloor());
        response.setRoomName(equipment.getRoom().getName());
        response.setCategoryId(equipment.getCategory().getId());
        response.setCategoryName(equipment.getCategory().getName());
        response.setCategoryImage(equipment.getCategory().getImage());
        response.setCategoryDescription(equipment.getCategory().getDescription());
        return response;
    }
}
