package com.bme.visualinventory.repository;

import com.bme.visualinventory.dao.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
