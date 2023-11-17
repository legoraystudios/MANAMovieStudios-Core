package com.legoray.MANAMovieStudios.repository;

import com.legoray.MANAMovieStudios.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Reviews, Integer> {
    List<Reviews> findByMovieId(int movieId);
}
