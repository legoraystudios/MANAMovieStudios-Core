package com.legoray.MANAMovieStudios.controller;

import com.legoray.MANAMovieStudios.entity.Category;
import com.legoray.MANAMovieStudios.service.CategoryService;
import com.legoray.MANAMovieStudios.utilities.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @CrossOrigin(origins = "${allowed.origins}", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/create")
    public ResponseEntity<JsonResponse> createCategory(@CookieValue(name = "MMS-Session") String cookieValue, @RequestBody Category category) {
        if(!cookieValue.isEmpty()) {
            return service.saveCategory(category);
        } else {
            JsonResponse errorResponse = new JsonResponse("You don't have permissions to perform this action.");
            return ResponseEntity.status(401).body(errorResponse);
        }
    }

    @CrossOrigin(origins = "${allowed.origins}", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/")
    public List<Category> getCategories(@CookieValue(name = "MMS-Session") String cookieValue) {
        if(!cookieValue.isEmpty()) {
            return service.getCategories();
        } else {
            return null;
        }
    }

    @CrossOrigin(origins = "${allowed.origins}", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/{id}")
    public Category getCategory(@CookieValue(name = "MMS-Session") String cookieValue, @PathVariable int id) {
        if(!cookieValue.isEmpty()) {
            return service.getCategoryById(id);
        } else {
            return null;
        }
    }

}
