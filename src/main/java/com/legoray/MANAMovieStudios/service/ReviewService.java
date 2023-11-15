package com.legoray.MANAMovieStudios.service;

import com.legoray.MANAMovieStudios.entity.Reviews;
import com.legoray.MANAMovieStudios.entity.User;
import com.legoray.MANAMovieStudios.repository.MovieRepository;
import com.legoray.MANAMovieStudios.repository.ReviewRepository;
import com.legoray.MANAMovieStudios.repository.UserRepository;
import com.legoray.MANAMovieStudios.utilities.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<JsonResponse> saveReview(Reviews review, String cookieValue) {


        int userId = userRepository.findByUsername(cookieValue).get().getId();

        review.setUserId(userId);

        repository.save(review);
        JsonResponse successResponse = new JsonResponse("Review created successfully");
        return ResponseEntity.status(200).body(successResponse);
    }

    public List<Reviews> getReviews() {
        return repository.findAll();
    }

    public Optional<Reviews> getReviewById(int id) {
        return repository.findById(id);
    }

    public Optional<Reviews> getReviewByMovieId(int id) {
        return repository.findByMovieId(id);
    }

    public ResponseEntity<JsonResponse> deleteReview(int id, String cookieValue) {

        int userId = userRepository.findByUsername(cookieValue).get().getId();
        int userIdReview = repository.findById(id).get().getUserId();

        if (userId == userIdReview) {
            repository.deleteById(id);
            JsonResponse successResponse = new JsonResponse("Review deleted successfully!");
            return ResponseEntity.status(200).body(successResponse);
        } else {
            JsonResponse errorResponse = new JsonResponse("You don't have permissions to perform this action.");
            return ResponseEntity.status(401).body(errorResponse);
        }

    }

}
