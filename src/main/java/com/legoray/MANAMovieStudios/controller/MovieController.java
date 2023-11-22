package com.legoray.MANAMovieStudios.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.legoray.MANAMovieStudios.entity.Category;
import com.legoray.MANAMovieStudios.entity.MovieWithUsernameDTO;
import com.legoray.MANAMovieStudios.entity.Movies;
import com.legoray.MANAMovieStudios.repository.MovieRepository;
import com.legoray.MANAMovieStudios.service.MovieService;
import com.legoray.MANAMovieStudios.utilities.JsonResponse;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Value("${allowed.origins}")
    private String allowedOrigins;

    @Autowired
    public MovieService service;
    @Autowired
    private MovieRepository movieRepository;

    @CrossOrigin(origins = "${allowed.origins}", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/create")
    public ResponseEntity<JsonResponse> createMovie(@CookieValue(name = "MMS-Session") String cookieValue, @RequestBody Movies movie) {
        if(!cookieValue.isEmpty()) {
            return service.saveMovie(movie, cookieValue);
        } else {
            JsonResponse errorResponse = new JsonResponse("You don't have permissions to perform this action.");
            return ResponseEntity.status(401).body(errorResponse);
        }
    }

    @CrossOrigin(origins = "${allowed.origins}", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/")
    public List<Movies> getMovies(String cookieValue) {
            return service.getMovies();
    }

    @CrossOrigin(origins = "${allowed.origins}", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/{id}")
    public Optional<Movies> getMovieById(@PathVariable int id) {
            return service.getMovieById(id);
    }

    @CrossOrigin(origins = "${allowed.origins}", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/user/{id}")
    public List<Movies> getMovieByUserId(@PathVariable int id) {
        return service.getMovieByUserId(id);
    }

    @CrossOrigin(origins = "${allowed.origins}", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/name/{name}")
    public Optional<Movies> getMovieByName(@PathVariable String name) {
            return service.getMovieByName(name);
    }

    @CrossOrigin(origins = "${allowed.origins}", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/category/{id}")
    public List<Movies> getMovieByCategoryId(@PathVariable int id) {
        return service.getMovieByCategoryId(id);
    }

    @CrossOrigin(origins = "${allowed.origins}", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/top")
    public List<Movies> getTopTenMovies() {
        return service.getTopTenMovies();
    }

    @CrossOrigin(origins = "${allowed.origins}", allowedHeaders = "*", allowCredentials = "true")
    @PutMapping ("/{id}")
    public ResponseEntity<JsonResponse> updateReview(@PathVariable int id, @RequestBody Movies movie, @CookieValue(name = "MMS-Session") String cookieValue) {
        if(!cookieValue.isEmpty()) {
            return service.updateMovie(id, movie, cookieValue);
        } else {
            JsonResponse errorResponse = new JsonResponse("You don't have permissions to perform this action.");
            return ResponseEntity.status(401).body(errorResponse);
        }
    }

    @CrossOrigin(origins = "${allowed.origins}", allowedHeaders = "*", allowCredentials = "true")
    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResponse> deleteMovieById(@CookieValue(name = "MMS-Session") String cookieValue, @PathVariable int id) {
        if(!cookieValue.isEmpty()) {
            return service.deleteMovie(id, cookieValue);
        } else {
            JsonResponse errorResponse = new JsonResponse("You don't have permissions to perform this action.");
            return ResponseEntity.status(401).body(errorResponse);
        }
    }

}
