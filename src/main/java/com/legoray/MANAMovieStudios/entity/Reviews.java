package com.legoray.MANAMovieStudios.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "mana-reviews")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Column(name = "review_title", nullable = false)
    private String reviewTitle;
    @Column(name = "review_text", nullable = false)
    private String reviewText;
    @Column(name = "review_rating", nullable = false)
    private int reviewRating;
    @Column(name = "movie_id", nullable = false)
    private int movieId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", insertable=false, updatable=false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Movies movies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;


}
