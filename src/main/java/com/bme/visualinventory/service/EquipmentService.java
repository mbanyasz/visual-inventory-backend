package com.bme.visualinventory.service;

import com.bme.visualinventory.dao.Category;
import com.bme.visualinventory.dao.Equipment;
import com.bme.visualinventory.dao.Room;
import com.bme.visualinventory.domain.request.CreateEquipmentsRequest;
import com.bme.visualinventory.domain.request.ModifyEquipmentRequest;
import com.bme.visualinventory.domain.response.EquipmentResponse;
import com.bme.visualinventory.repository.CategoryRepository;
import com.bme.visualinventory.repository.EquipmentRepository;
import com.bme.visualinventory.repository.RoomRepository;
import com.bme.visualinventory.transformer.EquipmentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentTransformer equipmentTransformer;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<EquipmentResponse> getAll() {
        List<Equipment> equipments = equipmentRepository.findAll();
        return equipments.stream()
                .map(equipmentTransformer::createEquipmentResponse)
                .collect(toList());
    }

    public EquipmentResponse get(Long id) {
        Equipment equipment = equipmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(""));
        return equipmentTransformer.createEquipmentResponse(equipment);
    }

    public void save(CreateEquipmentsRequest equipmentsRequest) {
        Category category = categoryRepository.findById(equipmentsRequest.getCategoryId()).orElseThrow(() -> new IllegalArgumentException(""));
        Room room = roomRepository.findById(equipmentsRequest.getRoomId()).orElseThrow(() -> new IllegalArgumentException(""));
        for (int i = 0; i < equipmentsRequest.getNumberOfEquipment(); i++) {
            Equipment equipment = equipmentTransformer.createEquipment(equipmentsRequest, room, category);
            equipmentRepository.save(equipment);
        }
    }

    public void delete(Long id) {
        equipmentRepository.deleteById(id);
    }

    public void update(Long id, ModifyEquipmentRequest equipmentRequest) {
        Category category = categoryRepository.findById(equipmentRequest.getCategoryId()).orElseThrow(() -> new IllegalArgumentException(""));
        Room room = roomRepository.findById(equipmentRequest.getRoomId()).orElseThrow(() -> new IllegalArgumentException(""));
        Equipment equipment = equipmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(""));
        Equipment updatedEquipment = equipmentTransformer.updateEquipment(equipmentRequest, equipment, room, category);
        equipmentRepository.save(updatedEquipment);
    }

}
