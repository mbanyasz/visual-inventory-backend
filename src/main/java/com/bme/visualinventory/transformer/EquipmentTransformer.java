package com.bme.visualinventory.transformer;

import com.bme.visualinventory.dao.Category;
import com.bme.visualinventory.dao.Equipment;
import com.bme.visualinventory.domain.request.CreateEquipmentsRequest;
import com.bme.visualinventory.domain.response.DetailedEquipmentResponse;
import com.bme.visualinventory.domain.response.SimpleEquipmentResponse;
import com.bme.visualinventory.domain.response.SimpleItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.stream.Collectors.toList;

@Component
public class EquipmentTransformer {

    @Autowired
    private CategoryTransformer categoryTransformer;

    public List<SimpleEquipmentResponse> createSimpleEquipmentResponses(List<Equipment> equipments) {
        return equipments.stream()
                .map(this::createSimpleEquipmentResponse)
                .collect(toList());
    }

    public SimpleEquipmentResponse createSimpleEquipmentResponse(Equipment equipment) {
        SimpleEquipmentResponse response = new SimpleEquipmentResponse();
        response.setId(equipment.getId());
        response.setName(equipment.getName());
        response.setDescription(equipment.getDescription());
        response.setCategory(categoryTransformer.createSimpleCategoryResponse(equipment.getCategory()));
        response.setImageBytes(equipment.getImage());
        return response;
    }

    public DetailedEquipmentResponse createDetailedEquipmentResponse(Equipment equipment, List<SimpleItemResponse> items) {
        DetailedEquipmentResponse response = new DetailedEquipmentResponse();
        response.setId(equipment.getId());
        response.setName(equipment.getName());
        response.setDescription(equipment.getDescription());
        response.setCategory(categoryTransformer.createSimpleCategoryResponse(equipment.getCategory()));
        response.setImageBytes(equipment.getImage());
        response.setItems(items);
        return response;
    }

    public Equipment createEquipment(CreateEquipmentsRequest equipmentsRequest, Category category, MultipartFile image) {
        Equipment equipment = new Equipment();
        return updateEquipment(equipment, equipmentsRequest, category, image);
    }

    public Equipment updateEquipment(Equipment equipment, CreateEquipmentsRequest equipmentsRequest, Category category, MultipartFile image) {
        equipment.setName(equipmentsRequest.getName());
        equipment.setDescription(equipmentsRequest.getDescription());
        equipment.setCategory(category);
        if (image != null && !image.isEmpty()) {
            try {
                equipment.setImage(image.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("IOException while saving category image");
            }
        }
        return equipment;
    }
}
