package com.bme.visualinventory.service;

import com.bme.visualinventory.dao.Category;
import com.bme.visualinventory.dao.Equipment;
import com.bme.visualinventory.domain.request.CreateEquipmentsRequest;
import com.bme.visualinventory.domain.response.DetailedEquipmentResponse;
import com.bme.visualinventory.domain.response.SimpleEquipmentResponse;
import com.bme.visualinventory.domain.response.SimpleItemResponse;
import com.bme.visualinventory.repository.CategoryRepository;
import com.bme.visualinventory.repository.EquipmentRepository;
import com.bme.visualinventory.repository.RoomRepository;
import com.bme.visualinventory.transformer.EquipmentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private ItemService itemService;

    public List<SimpleEquipmentResponse> getAll() {
        List<Equipment> equipments = equipmentRepository.findAll();
        return equipments.stream()
                .map(equipmentTransformer::createSimpleEquipmentResponse)
                .collect(toList());
    }

    public DetailedEquipmentResponse get(Long id) {
        Equipment equipment = equipmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(""));
        List<SimpleItemResponse> items = itemService.getItemsByEquipment(equipment);
        return equipmentTransformer.createDetailedEquipmentResponse(equipment, items);
    }

    public void save(CreateEquipmentsRequest equipmentsRequest, MultipartFile image) {
        Category category = categoryRepository.findById(equipmentsRequest.getCategoryId()).orElseThrow(() -> new IllegalArgumentException(""));
        Equipment equipment = equipmentTransformer.createEquipment(equipmentsRequest, category, image);
        equipmentRepository.save(equipment);
    }

    public void delete(Long id) {
        equipmentRepository.deleteById(id);
    }

    public void update(Long id, CreateEquipmentsRequest equipmentRequest, MultipartFile image) {
        Category category = categoryRepository.findById(equipmentRequest.getCategoryId()).orElseThrow(() -> new IllegalArgumentException(""));
        Equipment equipment = equipmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(""));
        Equipment updatedEquipment = equipmentTransformer.updateEquipment(equipment, equipmentRequest, category, image);
        equipmentRepository.save(updatedEquipment);
    }

}
