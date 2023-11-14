package com.legoray.MANAMovieStudios.repository;

import com.legoray.MANAMovieStudios.entity.Category;
import com.legoray.MANAMovieStudios.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByCategoryName(String categoryName);
}
