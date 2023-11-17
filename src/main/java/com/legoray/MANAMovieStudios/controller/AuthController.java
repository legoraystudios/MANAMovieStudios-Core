package com.legoray.MANAMovieStudios.controller;

import com.legoray.MANAMovieStudios.entity.LoginDto;
import com.legoray.MANAMovieStudios.entity.User;
import com.legoray.MANAMovieStudios.service.AuthService;
import com.legoray.MANAMovieStudios.service.UserService;
import com.legoray.MANAMovieStudios.utilities.JsonResponse;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    public UserService userService;

    @Autowired
    public AuthService authService;

    @CrossOrigin(origins = "${allowed.origins}", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/register")
    public ResponseEntity<JsonResponse> addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @CrossOrigin(origins = "${allowed.origins}", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/login")
    public ResponseEntity<JsonResponse> login(@RequestBody LoginDto login, HttpServletResponse response) {
        return authService.login(login, response);
    }

    @CrossOrigin(origins = "${allowed.origins}", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/logout")
    public ResponseEntity<JsonResponse> logout(@CookieValue(name = "MMS-Session") String cookieValue, HttpServletResponse response) {

        if(cookieValue.isEmpty()) {
            JsonResponse errorResponse = new JsonResponse("No user to logout.");
            return ResponseEntity.badRequest().body(errorResponse);
        } else {
            return authService.destroyCookie(response);
        }

    }

}
