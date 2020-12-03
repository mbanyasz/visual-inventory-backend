package com.bme.visualinventory.controller;

import com.bme.visualinventory.domain.request.CreateEquipmentsRequest;
import com.bme.visualinventory.domain.request.CreateRoomRequest;
import com.bme.visualinventory.domain.request.ModifyEquipmentRequest;
import com.bme.visualinventory.domain.response.DetailedCategoryResponse;
import com.bme.visualinventory.domain.response.EquipmentResponse;
import com.bme.visualinventory.domain.response.SimpleCategoryResponse;
import com.bme.visualinventory.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/equipments")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping
    public List<EquipmentResponse> getAllCategories() {
        return equipmentService.getAll();
    }

    @GetMapping("/{id}")
    public EquipmentResponse getCategory(@PathVariable("id") Long id) {
        return equipmentService.get(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable("id") Long id) {
        equipmentService.delete(id);
    }

    @PostMapping
    public void saveEquipments(@RequestBody CreateEquipmentsRequest equipmentsRequest) {
        equipmentService.save(equipmentsRequest);
    }

    @PutMapping("/{id}")
    public void updateRoom(@PathVariable("id") Long id, @RequestBody ModifyEquipmentRequest equipmentRequest) {
        equipmentService.update(id, equipmentRequest);
    }

}
