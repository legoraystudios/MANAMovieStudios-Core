package com.legoray.MANAMovieStudios.repository;

import com.legoray.MANAMovieStudios.entity.MovieWithUsernameDTO;
import com.legoray.MANAMovieStudios.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface MovieRepository extends JpaRepository<Movies, Integer> {
    Optional<Movies> findByMovieName(String movieName);
    List<Movies> findByUserId(int userId);
    default List<Movies> findTop10ByOrderByOverallRatingDesc() {

        List<Movies> allMovies = findAll();

        allMovies.sort(Comparator.comparingDouble(Movies::getOverallRating).reversed());

        return allMovies.stream().limit(10).collect(Collectors.toList());

    }

}
