package com.legoray.MANAMovieStudios.service;

import com.legoray.MANAMovieStudios.entity.LoginDto;
import com.legoray.MANAMovieStudios.entity.RegisterDto;
import com.legoray.MANAMovieStudios.entity.User;
import com.legoray.MANAMovieStudios.jwt.JwtService;
import com.legoray.MANAMovieStudios.repository.UserRepository;
import com.legoray.MANAMovieStudios.utilities.JsonResponse;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    public ResponseEntity<JsonResponse> register(RegisterDto register) {

        User existingUser = userRepository.findByUsername(register.getUsername()).orElse(null);

        if(existingUser == null) {

            User user = new User();
            user.setFirstName(register.getFirstName());
            user.setLastName(register.getLastName());
            user.setUsername(register.getUsername());
            user.setPassword(register.getPassword());
            user.setDob(register.getDob());

            userService.saveUser(user);
            JsonResponse successResponse = new JsonResponse("Account created successfully");
            return ResponseEntity.status(200).body(successResponse);
        } else {
            JsonResponse errorResponse = new JsonResponse("Username already exist in our records");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    public ResponseEntity<JsonResponse> login(LoginDto login, HttpServletResponse response) {

        User user = userRepository.findByUsername(login.getUsername()).orElse(null);

        if (user == null) {

            JsonResponse errorResponse = new JsonResponse("Username or password are incorrect.");
            return ResponseEntity.badRequest().body(errorResponse);

        } else if (!Objects.equals(login.getPassword(), user.getPassword())) {

            JsonResponse errorResponse = new JsonResponse("Username or password are incorrect.");
            return ResponseEntity.badRequest().body(errorResponse);

        } else {

            setCookie(login.getUsername(), response);

            JsonResponse successResponse = new JsonResponse("Logged in successfully!");
            return ResponseEntity.ok().body(successResponse);

        }

    }

    public void setCookie(String username, HttpServletResponse response) {

        // Creating a new cookie
        Cookie cookie = new Cookie("MMS-Session", username);

        cookie.setPath("/");
        cookie.setMaxAge(86400);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

    }

    public ResponseEntity<JsonResponse> destroyCookie(HttpServletResponse response) {

        // Creating a new cookie
        Cookie cookie = new Cookie("MMS-Session", "");

        cookie.setMaxAge(0);
        cookie.setPath("/");

        response.addCookie(cookie);

        JsonResponse successResponse = new JsonResponse("Logged out successfully!");
        return ResponseEntity.ok().body(successResponse);

    }


}
