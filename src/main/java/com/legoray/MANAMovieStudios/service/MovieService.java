package com.legoray.MANAMovieStudios.service;

import com.legoray.MANAMovieStudios.entity.*;
import com.legoray.MANAMovieStudios.repository.CategoryRepository;
import com.legoray.MANAMovieStudios.repository.MovieRepository;
import com.legoray.MANAMovieStudios.repository.ReviewRepository;
import com.legoray.MANAMovieStudios.repository.UserRepository;
import com.legoray.MANAMovieStudios.utilities.JsonResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    public ResponseEntity<JsonResponse> saveMovie(Movies movie, String cookieValue) {

        Optional<Movies> existingMovieName = repository.findByMovieName(movie.getMovieName());
        Optional<Category> existingCatId = categoryRepository.findById(movie.getCategoryId());

        if(existingMovieName.isPresent()) {
            JsonResponse errorResponse = new JsonResponse("Movie name already exist in our records.");
            return ResponseEntity.badRequest().body(errorResponse);
        } else if(!existingCatId.isPresent()) {
            JsonResponse errorResponse = new JsonResponse("Category ID doesn't exist in our records.");
            return ResponseEntity.badRequest().body(errorResponse);
        } else {

            int userId = userRepository.findByUsername(cookieValue)
                    .map(User::getId)
                    .orElse(-1);

            movie.setUserId(userId);

            repository.save(movie);
            JsonResponse successResponse = new JsonResponse("Movie created successfully");
            return ResponseEntity.status(200).body(successResponse);
        }

    }

    public List<Movies> getMovies() {
        return repository.findAll();
    }

    public Optional<Movies> getMovieById(int id) {
        return repository.findById(id);
    }

    public Optional<Movies> getMovieByName(String name) {
        return repository.findByMovieName(name);
    }

    public ResponseEntity<JsonResponse> deleteMovie(int id, String cookieValue) {

        int userId = userRepository.findByUsername(cookieValue).get().getId();
        int userIdMovies = repository.findById(id).get().getUserId();

        if(userId == userIdMovies) {
            repository.deleteById(id);
            JsonResponse successResponse = new JsonResponse("Movie deleted successfully!");
            return ResponseEntity.status(200).body(successResponse);
        } else {
            JsonResponse errorResponse = new JsonResponse("You don't have permissions to perform this action.");
            return ResponseEntity.status(401).body(errorResponse);
        }

    }

}
