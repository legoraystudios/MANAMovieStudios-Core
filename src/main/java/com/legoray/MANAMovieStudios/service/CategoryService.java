package com.legoray.MANAMovieStudios.service;

import com.legoray.MANAMovieStudios.entity.Category;
import com.legoray.MANAMovieStudios.repository.CategoryRepository;
import com.legoray.MANAMovieStudios.utilities.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;
    public ResponseEntity<JsonResponse> saveCategory(Category category) {

        Optional<Category> existingCatName = repository.findByCategoryName(category.getCategoryName());

        if(existingCatName.isPresent()) {
            JsonResponse errorResponse = new JsonResponse("Category name already exist in our records.");
            return ResponseEntity.badRequest().body(errorResponse);
        } else {
            repository.save(category);
            JsonResponse successResponse = new JsonResponse("Category created successfully");
            return ResponseEntity.status(200).body(successResponse);
        }

    }

    public Category getCategoryById(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Category> getCategories() {
        return repository.findAll();
    }

}
