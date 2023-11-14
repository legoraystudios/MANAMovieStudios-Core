package com.legoray.MANAMovieStudios.repository;

import com.legoray.MANAMovieStudios.entity.MovieWithUsernameDTO;
import com.legoray.MANAMovieStudios.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movies, Integer> {
    Optional<Movies> findByMovieName(String movieName);

    @Query("SELECT new com.legoray.MANAMovieStudios.entity.MovieWithUsernameDTO(m.id, m.movieName, m.moviePlot, m.movieDirector, m.movieActors, m.categoryId, m.userId, u.username) FROM Movies m JOIN User u ON u.id = m.userId")
    List<MovieWithUsernameDTO> getAllMoviesWithUsernames();

}
