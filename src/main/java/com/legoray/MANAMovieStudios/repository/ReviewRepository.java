package com.legoray.MANAMovieStudios.repository;

import com.legoray.MANAMovieStudios.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Reviews, Integer> {
}
