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
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    public MovieService service;
    @Autowired
    private MovieRepository movieRepository;

    @PostMapping("/create")
    public ResponseEntity<JsonResponse> createMovie(@CookieValue(name = "MMS-Session") String cookieValue, @RequestBody Movies movie) {
        if(!cookieValue.isEmpty()) {
            return service.saveMovie(movie, cookieValue);
        } else {
            JsonResponse errorResponse = new JsonResponse("You don't have permissions to perform this action.");
            return ResponseEntity.status(401).body(errorResponse);
        }
    }

    @GetMapping("/")
    public List<Movies> getMovies(@CookieValue(name = "MMS-Session") String cookieValue) {
        if(!cookieValue.isEmpty()) {
            return service.getMovies();
        } else {
            return null;
        }
    }

    @GetMapping("/{id}")
    public Optional<Movies> getMovieById(@CookieValue(name = "MMS-Session") String cookieValue, @PathVariable int id) {
        if(!cookieValue.isEmpty()) {
            return service.getMovieById(id);
        } else {
            return null;
        }
    }

    @GetMapping("/name/{name}")
    public Optional<Movies> getMovieByName(@CookieValue(name = "MMS-Session") String cookieValue, @PathVariable String name) {
        if(!cookieValue.isEmpty()) {
            return service.getMovieByName(name);
        } else {
            return null;
        }
    }

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
