package com.bme.visualinventory.repository;

import com.bme.visualinventory.dao.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
