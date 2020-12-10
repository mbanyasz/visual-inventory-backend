package com.bme.visualinventory.repository;

import com.bme.visualinventory.dao.Equipment;
import com.bme.visualinventory.dao.Item;
import com.bme.visualinventory.dao.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByRoom(Room id);
    List<Item> findByEquipment(Equipment equipment);
    List<Item> findByRoomAndEquipment(Room fromRoom, Equipment equipment);
}
