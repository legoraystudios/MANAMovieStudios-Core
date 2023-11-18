package com.legoray.MANAMovieStudios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.legoray.MANAMovieStudios.entity.User;
import com.legoray.MANAMovieStudios.repository.UserRepository;
import com.legoray.MANAMovieStudios.utilities.JsonResponse;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public ResponseEntity<JsonResponse> saveUser(User user) {

		Optional<User> existingUser = repository.findByUsername(user.getUsername());
		
		if(existingUser.isPresent()) {
			JsonResponse errorResponse = new JsonResponse("Username already exist in our records");
			return ResponseEntity.badRequest().body(errorResponse);
		} else {
			repository.save(user);
			JsonResponse successResponse = new JsonResponse("Account created successfully");
			return ResponseEntity.status(200).body(successResponse);
		}
		
	}
	
	public List<User> saveUsers(List<User> users) {
		return repository.saveAll(users);
	}

	public Optional<User> getUserByUsername(String username) {
		return repository.findByUsername(username);
	}
	
	public List<User> getUsers() {
		return repository.findAll();
	}
	
	public User getUsersById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public String deleteUser(int id) {
		repository.deleteById(id);
		return "Account Removed (ID: " + id + ")";
	}
	
	public User updateUser(User user) {
		User existingUser = repository.findById(user.getId()).orElse(null);
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setUsername(user.getUsername());
		existingUser.setDob(user.getDob());
		return repository.save(existingUser);
	}
}
