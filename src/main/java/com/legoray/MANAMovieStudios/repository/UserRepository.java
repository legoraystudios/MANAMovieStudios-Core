package com.legoray.MANAMovieStudios.repository;

import com.legoray.MANAMovieStudios.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
	Optional<User> findByUsername(String username);
}
