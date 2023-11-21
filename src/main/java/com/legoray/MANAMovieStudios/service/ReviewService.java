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

        if (review.getReviewTitle().isEmpty() || review.getReviewRating() > 5 || review.getReviewRating() < 1) {
            JsonResponse errorResponse = new JsonResponse("Review Title and/or Review Rating need to be between 1 and 5");
            return ResponseEntity.status(400).body(errorResponse);
        }

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

    public List<Reviews> getReviewByMovieId(int id) {
        return repository.findByMovieId(id);
    }

    public List<Reviews> getReviewByUserId(int id) {
        return repository.findByUserId(id);
    }

    public ResponseEntity<JsonResponse> updateReview(int id, Reviews review, String cookieValue) {

        Reviews existingReview = repository.findById(id).orElse(null);
        int userId = userRepository.findByUsername(cookieValue).get().getId();

        if (existingReview == null) {
            JsonResponse errorResponse = new JsonResponse("Review not found in our records.");
            return ResponseEntity.status(404).body(errorResponse);
        } else if (userId == existingReview.getUserId()) {
            existingReview.setReviewTitle(review.getReviewTitle());
            existingReview.setReviewRating(review.getReviewRating());
            existingReview.setReviewText(review.getReviewText());

            repository.save(existingReview);

            JsonResponse successResponse = new JsonResponse("Review updated successfully!");
            return ResponseEntity.status(200).body(successResponse);
        } else {
            JsonResponse errorResponse = new JsonResponse("You don't have permissions to perform this action.");
            return ResponseEntity.status(401).body(errorResponse);
        }

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
