package com.legoray.MANAMovieStudios.controller;

import com.legoray.MANAMovieStudios.entity.Reviews;
import com.legoray.MANAMovieStudios.service.ReviewService;
import com.legoray.MANAMovieStudios.utilities.JsonResponse;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService service;

    @PostMapping("/create")
    public ResponseEntity<JsonResponse> createReview(@CookieValue(name = "MMS-Session") String cookieValue, @RequestBody Reviews review) {
        if(!cookieValue.isEmpty()) {
            return service.saveReview(review, cookieValue);
        } else {
            JsonResponse errorResponse = new JsonResponse("You don't have permissions to perform this action.");
            return ResponseEntity.status(401).body(errorResponse);
        }
    }

    @GetMapping("/")
    public List<Reviews> getReviews(String cookieValue) {
            return service.getReviews();
    }

    @GetMapping("/movie/{id}")
    public Optional<Reviews> getByMovieId(String cookieValue, @PathVariable int id) {
             return service.getReviewByMovieId(id);
    }

    @GetMapping("/{id}")
    public Optional<Reviews> getReviewById(String cookieValue, @PathVariable int id) {
            return service.getReviewById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResponse> deleteReview(@CookieValue(name = "MMS-Session") String cookieValue, @PathVariable int id) {
        if (!cookieValue.isEmpty()) {
            return service.deleteReview(id, cookieValue);
        } else {
            JsonResponse errorResponse = new JsonResponse("You don't have permissions to perform this action.");
            return ResponseEntity.status(401).body(errorResponse);
        }
    }
}
