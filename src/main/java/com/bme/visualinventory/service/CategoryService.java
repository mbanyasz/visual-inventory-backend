package com.bme.visualinventory.service;

import com.bme.visualinventory.dao.Category;
import com.bme.visualinventory.domain.request.CreateCategoryRequest;
import com.bme.visualinventory.domain.response.DetailedCategoryResponse;
import com.bme.visualinventory.domain.response.SimpleCategoryResponse;
import com.bme.visualinventory.repository.CategoryRepository;
import com.bme.visualinventory.transformer.CategoryTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryTransformer categoryTransformer;

    public List<SimpleCategoryResponse> getAll() {
        return categoryRepository.findAll().stream()
                .map(categoryTransformer::createSimpleCategoryResponse)
                .collect(toList());
    }

    public DetailedCategoryResponse get(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(""));
        return categoryTransformer.createDetailedCategoryResponse(category);
    }

    public void save(CreateCategoryRequest categoryRequest, MultipartFile image) {
        Category updatedCategory = categoryTransformer.createCategory(categoryRequest, image);
        categoryRepository.save(updatedCategory);
    }

    public void update(Long id, CreateCategoryRequest categoryRequest, MultipartFile image) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(""));
        Category updatedCategory = categoryTransformer.updateCategory(categoryRequest, image, category);
        categoryRepository.save(updatedCategory);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

}
