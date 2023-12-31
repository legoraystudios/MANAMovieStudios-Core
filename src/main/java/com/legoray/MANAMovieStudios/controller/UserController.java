package com.legoray.MANAMovieStudios.controller;

import com.legoray.MANAMovieStudios.entity.LoginDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.legoray.MANAMovieStudios.entity.User;
import com.legoray.MANAMovieStudios.service.UserService;
import com.legoray.MANAMovieStudios.utilities.JsonResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;

	@CrossOrigin(origins = "${allowed.origins}", allowedHeaders = "*", allowCredentials = "true")
	@GetMapping("/")
	public List<User> getUsers(@CookieValue(name = "MMS-Session") String cookieValue) {
		if(!cookieValue.isEmpty()) {
			return service.getUsers();
		} else {
			return null;
		}
	}

	@CrossOrigin(origins = "${allowed.origins}", allowedHeaders = "*", allowCredentials = "true")
	@GetMapping("/{id}")
	public User getUsersById(@PathVariable int id, @CookieValue(name = "MMS-Session") String cookieValue) {
		if(!cookieValue.isEmpty()) {
			return service.getUsersById(id);
		} else {
			return null;
		}
	}

	@CrossOrigin(origins = "${allowed.origins}", allowedHeaders = "*", allowCredentials = "true")
	@GetMapping("/session")
	public Optional<User> getUserByUsername(@CookieValue(name = "MMS-Session") String cookieValue) {
		if(!cookieValue.isEmpty()) {
			return service.getUserByUsername(cookieValue);
		} else {
			return Optional.empty();
		}
	}

}
