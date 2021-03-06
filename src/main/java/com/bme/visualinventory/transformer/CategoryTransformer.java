package com.bme.visualinventory.transformer;

import com.bme.visualinventory.dao.Category;
import com.bme.visualinventory.domain.request.CreateCategoryRequest;
import com.bme.visualinventory.domain.response.DetailedCategoryResponse;
import com.bme.visualinventory.domain.response.SimpleCategoryResponse;
import com.bme.visualinventory.domain.response.SimpleItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public class CategoryTransformer {

    @Autowired
    private EquipmentTransformer equipmentTransformer;

    public SimpleCategoryResponse createSimpleCategoryResponse(Category category) {
        SimpleCategoryResponse simpleCategoryResponse = new SimpleCategoryResponse();
        simpleCategoryResponse.setId(category.getId());
        simpleCategoryResponse.setName(category.getName());
        simpleCategoryResponse.setDescription(category.getDescription());
        simpleCategoryResponse.setImageBytes(category.getImage());
        return simpleCategoryResponse;
    }

    public DetailedCategoryResponse createDetailedCategoryResponse(Category category, List<SimpleItemResponse> items) {
        DetailedCategoryResponse detailedCategoryResponse = new DetailedCategoryResponse();
        detailedCategoryResponse.setId(category.getId());
        detailedCategoryResponse.setName(category.getName());
        detailedCategoryResponse.setDescription(category.getDescription());
        detailedCategoryResponse.setImageBytes(category.getImage());
        detailedCategoryResponse.setItems(items);
        detailedCategoryResponse.setEquipments(equipmentTransformer.createSimpleEquipmentResponses(category.getEquipments()));
        return detailedCategoryResponse;
    }

    public Category createCategory(CreateCategoryRequest categoryRequest, MultipartFile image) {
        Category category = new Category();
        return updateCategory(categoryRequest, image, category);
    }

    public Category updateCategory(CreateCategoryRequest categoryRequest, MultipartFile image, Category category) {
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());
        if (image != null && !image.isEmpty()) {
            try {
                category.setImage(image.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("IOException while saving category image");
            }
        }
        return category;
    }
}
