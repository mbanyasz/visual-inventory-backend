package com.bme.visualinventory.controller;

import com.bme.visualinventory.domain.request.CreateEquipmentsRequest;
import com.bme.visualinventory.domain.response.DetailedEquipmentResponse;
import com.bme.visualinventory.domain.response.SimpleEquipmentResponse;
import com.bme.visualinventory.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/equipments")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping
    public List<SimpleEquipmentResponse> getAllEquipments() {
        return equipmentService.getAll();
    }

    @GetMapping("/{id}")
    public DetailedEquipmentResponse getEquipment(@PathVariable("id") Long id) {
        return equipmentService.get(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEquipment(@PathVariable("id") Long id) {
        equipmentService.delete(id);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public void saveCategory(@RequestPart("data") CreateEquipmentsRequest equipmentsRequest,
                             @RequestPart("file") MultipartFile image) {
        equipmentService.save(equipmentsRequest, image);
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public void updateCategory(@PathVariable("id") Long id,
                               @RequestPart("data") CreateEquipmentsRequest equipmentRequest,
                               @RequestPart("file") MultipartFile image) {
        equipmentService.update(id, equipmentRequest, image);
    }

}
