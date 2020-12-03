package com.bme.visualinventory.repository;

import com.bme.visualinventory.dao.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
