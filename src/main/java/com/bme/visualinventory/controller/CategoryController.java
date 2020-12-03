package com.bme.visualinventory.controller;

import com.bme.visualinventory.domain.request.CreateCategoryRequest;
import com.bme.visualinventory.domain.response.DetailedCategoryResponse;
import com.bme.visualinventory.domain.response.SimpleCategoryResponse;
import com.bme.visualinventory.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<SimpleCategoryResponse> getAllCategories() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public DetailedCategoryResponse getCategory(@PathVariable("id") Long id) {
        return categoryService.get(id);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public void saveCategory(@RequestPart("data") CreateCategoryRequest categoryRequest,
                             @RequestPart("file") MultipartFile image) {
        categoryService.save(categoryRequest, image);
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public void updateCategory(@PathVariable("id") Long id,
                               @RequestPart("data") CreateCategoryRequest categoryRequest,
                               @RequestPart("file") MultipartFile image) {
        categoryService.update(id, categoryRequest, image);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable("id") Long id) {
        categoryService.delete(id);
    }
}
