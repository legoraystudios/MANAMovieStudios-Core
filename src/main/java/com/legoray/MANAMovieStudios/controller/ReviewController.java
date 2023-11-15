package com.legoray.MANAMovieStudios.controller;

import com.legoray.MANAMovieStudios.entity.Reviews;
import com.legoray.MANAMovieStudios.service.ReviewService;
import com.legoray.MANAMovieStudios.utilities.JsonResponse;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Reviews> getReviews(@CookieValue(name = "MMS-Session") String cookieValue) {
        if(!cookieValue.isEmpty()) {
            return service.getReviews();
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResponse> deleteReview(@CookieValue(name = "MMS-Session") String cookieValue, @PathVariable int id) {
        if(!cookieValue.isEmpty()) {
            return service.deleteReview(id, cookieValue);
        } else {
            JsonResponse errorResponse = new JsonResponse("You don't have permissions to perform this action.");
            return ResponseEntity.status(401).body(errorResponse);
        }
    }
}
